package com.wcc.wds.web.service;

import com.wcc.wds.web.model.CollectTask;
import com.wcc.wds.web.entity.CollectTaskReq;
import com.wcc.wds.web.entity.CollectTaskResp;
import com.wcc.wds.web.dao.CollectTaskDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import static com.wcc.wds.web.data.PublicData.*;

/**
 * 采集任务服务
 */
@Service
public class CollectTaskService {
    private static final Logger logger = LoggerFactory.getLogger(CollectTaskService.class);

    @Autowired
    private CollectTaskDao collectTaskDao;


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
                case SELECT:
                    selectAllTask(collectTaskResp);
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

    /**
     * 创建任务
     * @param collectTaskReq 请求参数
     * @param collectTaskResp 返回参数
     */
    private void createTask(CollectTaskReq collectTaskReq, CollectTaskResp collectTaskResp){
        CollectTask collectTask = new CollectTask();
        //生成任务id
        UUID uuid = UUID.randomUUID();
        //生成创建时间
        long createTime = System.currentTimeMillis();
        //设置model对象
        collectTask.setId(uuid.toString());
        collectTask.setCreateTime(createTime);
        collectTask.setCollectPath(collectTaskReq.getCollectPath());
        collectTask.setCollectTime(collectTaskReq.getCollectTime());
        collectTask.setRegex(collectTaskReq.getRegex());
        //如果没传周期默认为一天一次
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
            logger.error("创建任务错误：" + e.getMessage());
            collectTaskResp.setRetCode(-1);
            collectTaskResp.setMessage("创建任务错误：" + e.getMessage());
        }
    }

    /**
     * 更改任务
     * @param collectTaskReq
     * @param collectTaskResp
     */
    private void modifyTask(CollectTaskReq collectTaskReq, CollectTaskResp collectTaskResp){
        CollectTask collectTask = new CollectTask();
        collectTask.setRegex(collectTaskReq.getRegex());
        collectTask.setCollectPath(collectTask.getCollectPath());
        collectTask.setThreadNum(collectTask.getThreadNum());
        collectTask.setTaskName(collectTaskReq.getTaskName());
        try {
            collectTaskDao.updateNotNullByName(collectTask);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("更改任务错误: " + e.getMessage());
            collectTaskResp.setRetCode(-1);
            collectTaskResp.setMessage("更改任务错误: " + e.getMessage());
        }

    }

    /**
     * 暂停任务
     * @param collectTaskReq
     * @param collectTaskResp
     */
    private void pauseTask(CollectTaskReq collectTaskReq, CollectTaskResp collectTaskResp){
        CollectTask collectTask = new CollectTask();
        collectTask.setTaskStatus(PAUSE);
        collectTask.setTaskName(collectTaskReq.getTaskName());
        try {
            collectTaskDao.updateNotNullByName(collectTask);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("暂停任务错误：" + e.getMessage());
            collectTaskResp.setRetCode(-1);
            collectTaskResp.setMessage("暂停任务错误：" + e.getMessage());
        }
    }

    /**
     * 删除任务
     * @param collectTaskReq
     * @param collectTaskResp
     */
    private void deleteTask(CollectTaskReq collectTaskReq, CollectTaskResp collectTaskResp){
        try {
            collectTaskDao.deleteByName(collectTaskReq.getTaskName());
        }catch (Exception e){
            e.printStackTrace();
            logger.error("删除任务错误：" + e.getMessage());
            collectTaskResp.setRetCode(-1);
            collectTaskResp.setMessage("删除任务错误：" + e.getMessage());
        }
    }

    private void selectAllTask(CollectTaskResp collectTaskResp){
        try {
            List<CollectTask> collectTaskList = collectTaskDao.selectAll();
            collectTaskResp.setCollectTask(collectTaskList);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("查询所有任务错误：" + e.getMessage());
            collectTaskResp.setRetCode(-1);
            collectTaskResp.setMessage("查询所有任务错误：" + e.getMessage());
        }
    }



}
