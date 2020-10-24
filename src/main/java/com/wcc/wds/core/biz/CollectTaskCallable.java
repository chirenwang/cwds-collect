package com.wcc.wds.core.biz;

import com.wcc.wds.web.entity.CollectResultEntity;
import com.wcc.wds.web.model.CollectTaskModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public CollectTaskCallable(CollectTaskModel collectTaskModel) {
        this.collectTaskModel = collectTaskModel;
    }


    @Override
    public CollectResultEntity call(){
        //实例运行结果
        CollectResultEntity collectResultEntity = new CollectResultEntity(1, "success");
        try {
            //获取采集路径
            String[] collectPaths = collectTaskModel.getCollectPath().split(",");
            //获取文件名正则
            String regex = collectTaskModel.getRegex();
            //域名id
            String domainId = collectTaskModel.getDomainId();
            //域名url
            String domainUrl = collectTaskModel.getDomainUrl();
            //线程数
            int threadNum = collectTaskModel.getThreadNum();
            //判断list任务是否运行完成
            AtomicInteger atomicInteger = new AtomicInteger();
            //list任务线程池
            ExecutorService listPool = Executors.newFixedThreadPool(threadNum);
            //list任务大小
            int listSize = collectPaths.length;
            for (String collectPath : collectPaths){
                listPool.submit(new ListRunnable(fileQueue, collectPath, regex, atomicInteger));
            }
            //读文件任务线程池
            ExecutorService readPool = Executors.newFixedThreadPool(threadNum);
            //读文件任务
            for(int i = 0; i < threadNum; i++){
                readPool.submit(new ReadRunnable(fileQueue, atomicInteger, listSize, collectResultEntity, domainId, domainUrl));
            }
        }catch (Exception e){
            String message = e.getMessage();
            logger.error("collect callable failed " + message);
            collectResultEntity.setRet(-1);
            collectResultEntity.setMessage(message);
        }
        return collectResultEntity;
    }



}
