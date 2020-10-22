package com.wcc.wds.web.schedule;

import com.wcc.wds.web.dao.CollectInstanceDao;
import com.wcc.wds.web.dao.CollectTaskDao;
import com.wcc.wds.web.model.CollectInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.*;
import static com.wcc.wds.web.data.PublicData.*;

/**
 * 采集任务调度类
 */
@Component
public class CollectTaskSchedule {

    private final static Logger logger = LoggerFactory.getLogger(CollectTaskSchedule.class);

    @Autowired
    private CollectTaskDao collectTaskDao;
    @Autowired
    private CollectInstanceDao collectInstanceDao;

    /**
     * 每5分钟扫一遍任务表创建实例
     */
    @Scheduled(cron = "0/1 * * * * *")
    private void createCollectInstance(){
        try {
            //获取今天零点的时间戳
            long today = dayTimeInMillis();
            //找到所有running状态的任务
            List<String> taskIds = collectTaskDao.selectIdByStatus(RUNNING);
            //找到开始时间最大的实例
            List<CollectInstance> collectInstances = collectInstanceDao.selectLatest();
            HashMap<String, Long> instanceMap = new HashMap<>(100);
            //将最新的实例写进map
            for (CollectInstance collectInstance: collectInstances){
                instanceMap.put(collectInstance.getTaskId(), collectInstance.getStartTime().getTime());
            }
            for (String taskId : taskIds){
                //如果最新实例的任务id中包含运行任务的任务id，即说明是老任务，判断开始时间是否大于今天
                if (instanceMap.containsKey(taskId)){
                    if (instanceMap.get(taskId) < today){
                        CollectInstance newInstance = createNewInstance(taskId);
                        collectInstanceDao.insert(newInstance);
                        logger.info("创建实例：" + newInstance.getInstanceId() + "成功, 时间为：" + newInstance.getStartTime().toString() );
                    }
                    //如果最新实例的任务id中不包含运行任务的任务id，即说明是新任务直接创建新实例
                }else {
                    CollectInstance newInstance = createNewInstance(taskId);
                    collectInstanceDao.insert(newInstance);
                    logger.info("创建实例：" + newInstance.getInstanceId() + "成功, 时间为：" + newInstance.getStartTime().toString() );
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("创建实例错误：" + e.getMessage());
        }

    }

    /**
     * 获取每天0点的时间戳
     */
    private Long dayTimeInMillis() {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 创建新的实例
     * @param taskId
     * @return
     */
    private CollectInstance createNewInstance(String taskId){
        CollectInstance newInstance = new CollectInstance();
        newInstance.setInstanceStatus(CREATED);
        newInstance.setInstanceId(UUID.randomUUID().toString());
        newInstance.setStartTime(new Date(System.currentTimeMillis()));
        newInstance.setTaskId(taskId);
        return newInstance;
    }


}
