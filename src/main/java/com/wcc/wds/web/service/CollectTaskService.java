package com.wcc.wds.web.service;

import com.wcc.wds.web.model.CollectTask;
import com.wcc.wds.web.entity.CollectTaskReq;
import com.wcc.wds.web.entity.CollectTaskResp;
import com.wcc.wds.web.dao.CollectInstanceDao;
import com.wcc.wds.web.dao.CollectTaskDao;
import com.wcc.wds.web.dao.WithdrawContributionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 采集任务服务
 */
@Service
public class CollectTaskService {
    private static final Logger logger = LoggerFactory.getLogger(CollectTaskService.class);

    /**
     * 创建任务
     */
    private static final String CREATE = "create";
    /**
     * 修改任务
     */
    private static final String MODIFY = "modify";
    /**
     * 暂停任务
     */
    private static final String PAUSE = "pause";
    /**
     * 删除任务
     */
    private static final String DELETE = "delete";
    /**
     * 运行状态
     */
    private static final String RUNNING = "running";
    /**
     * 每天的分钟数
     */
    private static final int DAY_MINUTE = 1440;


    @Autowired
    private CollectTaskDao collectTaskDao;
    @Autowired
    private CollectInstanceDao collectInstanceDao;
    @Autowired
    private WithdrawContributionDao withdrawContributionDao;


    public CollectTaskResp collectTask(CollectTaskReq collectTaskReq){
        //返回参数
        CollectTaskResp collectTaskResp = new CollectTaskResp(1, "success");
        try {
            //获取任务操作
            String operate = collectTaskReq.getOperate();
            switch (operate){
                case CREATE:
                    createTask(collectTaskReq, collectTaskResp);
                    break;
                case MODIFY:
                    modifyTask(collectTaskReq, collectTaskResp);
                    break;
                case PAUSE:
                    pauseTask(collectTaskReq, collectTaskResp);
                    break;
                case DELETE:
                    deleteTask(collectTaskReq, collectTaskResp);
                    break;
                default:
                    //输入了未知的操作类型
                    logger.error("no such operate");
                    collectTaskResp.setMessage("no such operate");
                    collectTaskResp.setRetCode(-1);
            }
        }catch (Exception e){
            //发生异常
            e.printStackTrace();
            collectTaskResp.setMessage(e.getMessage());
            collectTaskResp.setRetCode(-1);
            logger.error(e.getMessage());
        }
        return collectTaskResp;

    }


    private void createTask(CollectTaskReq collectTaskReq, CollectTaskResp collectTaskResp){
        CollectTask collectTask = new CollectTask();
        UUID uuid = UUID.randomUUID();
        long createTime = System.currentTimeMillis();
        collectTask.setId(uuid.toString());
        collectTask.setCreateTime(createTime);
        collectTask.setCollectPath(collectTaskReq.getCollectPath());
        collectTask.setCollectTime(collectTaskReq.getCollectTime());
        collectTask.setRegex(collectTaskReq.getRegex());
        int revolution  = collectTaskReq.getRevolution();
        if (revolution == 0){ revolution = DAY_MINUTE;}
        collectTask.setRevolution(revolution);
        collectTask.setTaskName(collectTaskReq.getTaskName());
        collectTask.setThreadNum(collectTaskReq.getThreadNum());
        collectTask.setTaskStatus(RUNNING);
        try{
            collectTaskDao.insert(collectTask);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            collectTaskResp.setRetCode(-1);
            collectTaskResp.setMessage(e.getMessage());
        }
    }

    private void modifyTask(CollectTaskReq collectTaskReq, CollectTaskResp collectTaskResp){
        CollectTask collectTask = new CollectTask();

    }

    private void pauseTask(CollectTaskReq collectTaskReq, CollectTaskResp collectTaskResp){}

    private void deleteTask(CollectTaskReq collectTaskReq, CollectTaskResp collectTaskResp){}

}
