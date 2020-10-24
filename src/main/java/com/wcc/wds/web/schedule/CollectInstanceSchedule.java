package com.wcc.wds.web.schedule;

import com.wcc.wds.web.mapper.CollectInstanceMapper;
import com.wcc.wds.web.mapper.CollectTaskMapper;
import com.wcc.wds.web.entity.CollectResultEntity;
import com.wcc.wds.web.mapper.WithdrawContributionMapper;
import com.wcc.wds.web.model.CollectInstanceModel;
import com.wcc.wds.web.model.CollectTaskModel;
import com.wcc.wds.core.biz.CollectTaskCallable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import static com.wcc.wds.web.data.PublicData.*;

/**
 * 实例调度类
 */
@Component
public class CollectInstanceSchedule {

    private static final Logger logger = LoggerFactory.getLogger(CollectInstanceSchedule.class);
    /**
     * 实例调度池
     */
    private static final ExecutorService instancePool = Executors.newFixedThreadPool(50);
    /**
     * 实例运行结果map
     */
    private static final ConcurrentHashMap<String , Future<CollectResultEntity>> instanceMap = new ConcurrentHashMap<>(50);


    @Autowired
    private CollectInstanceMapper collectInstanceMapper;
    @Autowired
    private CollectTaskMapper collectTaskMapper;
    @Autowired
    private WithdrawContributionMapper withdrawContributionMapper;

    /**
     * 每分钟扫一遍实例表运行实例
     */
    @Scheduled(cron = "0/1 * * * * *")
    private void runInstance(){
        try {
            //查询所有创建及重试的实例
            List<CollectInstanceModel> collectInstances = collectInstanceMapper.selectByStatus(new String[]{CREATED, RETRY});
            for (CollectInstanceModel collectInstance : collectInstances){
                //运行实例
                String instanceId = collectInstance.getInstanceId();
                //确保线程池中没有重复实例
                if (!instanceMap.containsKey(instanceId)){
                    //获取实例对应的采集任务参数
                    CollectTaskModel collectTaskModel = collectTaskMapper.selectById(collectInstance.getTaskId());
                    List<String> withdrawFiles = withdrawContributionMapper.selectAllFilePath();
                    //提交运行实例
                    Future<CollectResultEntity> future = instancePool.submit(new CollectTaskCallable(collectTaskModel, withdrawFiles));
                    //将future放入运行结果map
                    instanceMap.put(instanceId, future);
                    //将实例状态置为运行中
                    collectInstanceMapper.updateStatusById(instanceId, RUNNING);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("运行实例错误" + e.getMessage());
        }

    }

    /**
     * 每分钟检查一次正在运行的实例并更新数据库状态
     */
    @Scheduled(cron = "0/1 * * * * *")
    private void checkInstance(){
        try {
            //查询正在运行的实例的状态
            for (Map.Entry<String, Future<CollectResultEntity>> entry : instanceMap.entrySet()){
                String instanceId = entry.getKey();
                Future<CollectResultEntity> future = entry.getValue();
                try {
                    //实例运行完成
                    if (future.isDone()){
                        //获取实例运行结果
                        CollectResultEntity collectResult = future.get();
                        //将实例状态置为成功
                        collectInstanceMapper.updateStatusById(instanceId, SUCCESS);
                        //从实例结果map中删除实例
                        instanceMap.remove(instanceId);
                        logger.info("实例：" + instanceId + " 运行成功");
                    }
                    //若有异常证明实例运行失败
                }catch (Exception e){
                    logger.error("实例：" + entry.getKey() + "运行失败：" + e.getMessage());
                    e.printStackTrace();
                    //将实例置为失败
                    collectInstanceMapper.updateStatusById(instanceId, FAILED);
                    //从实例结果map中删除实例
                    instanceMap.remove(instanceId);
                }
            }
            //获取失败实例，重试次数大于3次则永久失败
            List<CollectInstanceModel> failedInstance = collectInstanceMapper.selectFail();
            for (CollectInstanceModel collectInstance : failedInstance){
                String instanceId = collectInstance.getInstanceId();
                //将实例状态置为重试
                collectInstanceMapper.updateStatusById(instanceId, RETRY);
                //更新实例重试次数
                collectInstanceMapper.updateRetryTimeById(instanceId, collectInstance.getRetryTime() + 1);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("检查实例错误：" + e.getMessage());
        }

    }
}
