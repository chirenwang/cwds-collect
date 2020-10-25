package com.wcc.wds.core.biz;

import com.wcc.wds.core.biz.elasticsearch.dao.ElasticsearchDao;
import com.wcc.wds.web.entity.CollectResultEntity;
import com.wcc.wds.web.entity.NodeNameEntity;
import com.wcc.wds.web.model.ElasticsearchModel;
import lombok.SneakyThrows;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import static com.wcc.wds.web.data.PublicData.*;

/**
 * 采集文件线程
 */
public class ReadRunnable implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ReadRunnable.class);

    private final ConcurrentLinkedQueue<String> fileQueue;
    private final AtomicInteger atomicInteger;
    private final int listSize;
    private final CollectResultEntity collectResultEntity;
    private final String domainUrl;
    private final String domainId;
    private final ElasticsearchDao elasticsearchDao;

    public ReadRunnable(ConcurrentLinkedQueue<String> fileQueue, AtomicInteger atomicInteger, int listSize, CollectResultEntity collectResultEntity, String domainUrl, String domainId, ElasticsearchDao elasticsearchDao) {
        this.fileQueue = fileQueue;
        this.atomicInteger = atomicInteger;
        this.listSize = listSize;
        this.collectResultEntity = collectResultEntity;
        this.domainUrl = domainUrl;
        this.domainId = domainId;
        this.elasticsearchDao = elasticsearchDao;
    }


    @SneakyThrows
    @Override
    public void run() {
        try {
            //构建本地文件系统
            FileSystem fileSystem = FileSystem.get(URI.create(FILE_SCHEMA), new Configuration());
            Date collectTime = new Date(System.currentTimeMillis());
            int failedCount = 0;
            //当文件队列为空或者list任务已经运行完时，结束循环
            while (!fileQueue.isEmpty() || atomicInteger.get() != listSize) {
                String path = fileQueue.poll();
                if (!StringUtils.isEmpty(path)) {
                    try {
                        //读取文件
                        FSDataInputStream in = fileSystem.open(new Path(path));
                        byte[] bytes = new byte[in.available()];
                        in.read(bytes);
                        String content = new String(bytes);
                        //判断文件不为空
                        if (StringUtils.isEmpty(content)|| " ".equals(content)) continue;
                        //使用jsoup解析
                        Document document = Jsoup.parse(content);
                        //如果文件内容是撤稿内容，则跳过
                        if (document.getElementsByTag(BODY).text().equals(DELETED)){ continue;}
                        //稿件id
                        String id = String.valueOf(Math.abs(path.hashCode()));
                        //标题
                        String title = document.getElementsByTag(TITLE).text();
                        //正文
                        String articleMain  = document.getElementsByClass(ARTICLE_MAIN).text();
                        //编辑
                        String editor = document.getElementsByClass(EDITOR).text();
                        //发布时间
                        String publishTime = document.getElementsByClass(PUBLISH_TIME).text();
                        //栏目结构
                        String[] positionInners = document.getElementsByClass(POSITION_INNER).text().split(">");
                        ArrayList<NodeNameEntity> nodeNames = new ArrayList<>(5);
                        int i = 1;
                        for (String nodeName : positionInners){
                            nodeNames.add(new NodeNameEntity(i, nodeName));
                            i++;
                        }
                        //来源
                        String source = document.getElementsByClass(SOURCE).text();
                        //url
                        String url = domainUrl + path;
                        //构建es对象
                        ElasticsearchModel elasticsearchModel = new ElasticsearchModel(title, id, articleMain, collectTime, source, editor, url, path, PUBLISHED, nodeNames, publishTime, domainId);
                        //写入es
                        elasticsearchDao.addDocumentToBulkProcessor(elasticsearchModel);
                    }catch (Exception e){
                        //如果失败的次数超过5，则任务失败
                        if (failedCount > 5) throw e;
                        failedCount++;
                        String message = e.getMessage();
                        logger.error("read file :" + path + "failed :" +e.getMessage());
                        collectResultEntity.setRet(-1);
                        collectResultEntity.setMessage(message);
                    }
                }
            }
        }catch (Exception e){
            logger.error("read file failed：" + e.getMessage());
            throw e;
        }
    }
}
