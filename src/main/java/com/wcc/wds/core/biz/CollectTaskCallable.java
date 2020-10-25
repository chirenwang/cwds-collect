package com.wcc.wds.core.biz;

import com.wcc.wds.core.biz.elasticsearch.dao.ElasticsearchDao;
import com.wcc.wds.web.entity.CollectResultEntity;
import com.wcc.wds.web.model.CollectTaskModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 实例线程
 */
public class CollectTaskCallable implements Callable<CollectResultEntity> {

    private static final Logger logger = LoggerFactory.getLogger(CollectTaskCallable.class);

    private final CollectTaskModel collectTaskModel;

    private final ConcurrentLinkedQueue<String> fileQueue = new ConcurrentLinkedQueue<>();

    private final List<String> withdrawFiles;

    private final ElasticsearchDao elasticsearchDao;

    private final Timestamp latestDate;

    public CollectTaskCallable(CollectTaskModel collectTaskModel, List<String> withdrawFiles, ElasticsearchDao elasticsearchDao,  Timestamp latestDate) {
        this.collectTaskModel = collectTaskModel;
        this.withdrawFiles = withdrawFiles;
        this.elasticsearchDao = elasticsearchDao;
        this.latestDate = latestDate;
    }


    @Override
    public CollectResultEntity call(){
        logger.info("run：" + collectTaskModel.getId() + " instance");
        //实例运行结果
        CollectResultEntity collectResultEntity = new CollectResultEntity(1, "success");
        //线程数
        int threadNum = collectTaskModel.getThreadNum();
        //list任务线程池
        ExecutorService listPool = Executors.newFixedThreadPool(threadNum);
        //读文件任务线程池
        ExecutorService readPool = Executors.newFixedThreadPool(threadNum);
        try {
            //获取采集路径
            String[] collectPaths = collectTaskModel.getCollectPath().split(",");
            //获取文件名正则
            String regex = collectTaskModel.getRegex();
            //域名id
            String domainId = collectTaskModel.getDomainId();
            //域名url
            String domainUrl = collectTaskModel.getDomainUrl();

            //判断list任务是否运行完成
            AtomicInteger atomicInteger = new AtomicInteger();
            //list任务大小
            int listSize = collectPaths.length;
            for (String collectPath : collectPaths){
                listPool.submit(new ListRunnable(fileQueue, collectPath, regex, atomicInteger, withdrawFiles, latestDate));
            }
            //读文件任务
            for(int i = 0; i < threadNum; i++){
                readPool.submit(new ReadRunnable(fileQueue, atomicInteger, listSize, collectResultEntity, domainId, domainUrl, elasticsearchDao));
            }
        }catch (Exception e){
            String message = e.getMessage();
            logger.error("collect callable failed " + message);
            collectResultEntity.setRet(-1);
            collectResultEntity.setMessage(message);
        }finally {
            //关闭线程池
            listPool.shutdown();
            readPool.shutdown();
        }
        while (true){
            if (readPool.isShutdown() && listPool.isShutdown()){
                logger.info("collect Instance complete");
                return collectResultEntity;
            }
        }
    }



}
