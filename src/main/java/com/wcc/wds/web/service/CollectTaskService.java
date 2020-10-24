package com.wcc.wds.web.service;

import com.wcc.wds.web.model.CollectTaskModel;
import com.wcc.wds.web.entity.CollectTaskReqEntity;
import com.wcc.wds.web.entity.CollectTaskRespEntity;
import com.wcc.wds.web.mapper.CollectTaskMapper;
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
    private CollectTaskMapper collectTaskMapper;


    public CollectTaskRespEntity collectTask(CollectTaskReqEntity collectTaskReqEntity){
        //返回参数
        CollectTaskRespEntity collectTaskRespEntity = new CollectTaskRespEntity(1, "success");
        try {
            //获取任务操作
            String operate = collectTaskReqEntity.getOperate();
            switch (operate){
                case CREATE:
                    createTask(collectTaskReqEntity, collectTaskRespEntity);
                    break;
                case MODIFY:
                    modifyTask(collectTaskReqEntity, collectTaskRespEntity);
                    break;
                case PAUSE:
                    pauseTask(collectTaskReqEntity, collectTaskRespEntity);
                    break;
                case DELETE:
                    deleteTask(collectTaskReqEntity, collectTaskRespEntity);
                    break;
                case SELECT:
                    selectAllTask(collectTaskRespEntity);
                default:
                    //输入了未知的操作类型
                    logger.error("no such operate");
                    collectTaskRespEntity.setMessage("no such operate");
                    collectTaskRespEntity.setRetCode(-1);
            }
        }catch (Exception e){
            //发生异常
            e.printStackTrace();
            collectTaskRespEntity.setMessage(e.getMessage());
            collectTaskRespEntity.setRetCode(-1);
            logger.error(e.getMessage());
        }
        return collectTaskRespEntity;

    }

    /**
     * 创建任务
     * @param collectTaskReqEntity 请求参数
     * @param collectTaskRespEntity 返回参数
     */
    private void createTask(CollectTaskReqEntity collectTaskReqEntity, CollectTaskRespEntity collectTaskRespEntity){
        CollectTaskModel collectTaskModel = new CollectTaskModel();
        //生成任务id
        UUID uuid = UUID.randomUUID();
        //生成创建时间
        long createTime = System.currentTimeMillis();
        //设置model对象
        collectTaskModel.setId(uuid.toString());
        collectTaskModel.setCreateTime(createTime);
        collectTaskModel.setCollectPath(collectTaskReqEntity.getCollectPath());
        collectTaskModel.setCollectTime(collectTaskReqEntity.getCollectTime());
        collectTaskModel.setRegex(collectTaskReqEntity.getRegex());
        //如果没传周期默认为一天一次
        int revolution  = collectTaskReqEntity.getRevolution();
        if (revolution == 0){ revolution = DAY_MINUTE;}
        collectTaskModel.setRevolution(revolution);
        collectTaskModel.setTaskName(collectTaskReqEntity.getTaskName());
        collectTaskModel.setThreadNum(collectTaskReqEntity.getThreadNum());
        collectTaskModel.setTaskStatus(RUNNING);
        try{
            collectTaskMapper.insert(collectTaskModel);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("创建任务错误：" + e.getMessage());
            collectTaskRespEntity.setRetCode(-1);
            collectTaskRespEntity.setMessage("创建任务错误：" + e.getMessage());
        }
    }

    /**
     * 更改任务
     * @param collectTaskReqEntity
     * @param collectTaskRespEntity
     */
    private void modifyTask(CollectTaskReqEntity collectTaskReqEntity, CollectTaskRespEntity collectTaskRespEntity){
        CollectTaskModel collectTaskModel = new CollectTaskModel();
        collectTaskModel.setRegex(collectTaskReqEntity.getRegex());
        collectTaskModel.setCollectPath(collectTaskModel.getCollectPath());
        collectTaskModel.setThreadNum(collectTaskModel.getThreadNum());
        collectTaskModel.setTaskName(collectTaskReqEntity.getTaskName());
        try {
            collectTaskMapper.updateNotNullByName(collectTaskModel);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("更改任务错误: " + e.getMessage());
            collectTaskRespEntity.setRetCode(-1);
            collectTaskRespEntity.setMessage("更改任务错误: " + e.getMessage());
        }

    }

    /**
     * 暂停任务
     * @param collectTaskReqEntity
     * @param collectTaskRespEntity
     */
    private void pauseTask(CollectTaskReqEntity collectTaskReqEntity, CollectTaskRespEntity collectTaskRespEntity){
        CollectTaskModel collectTaskModel = new CollectTaskModel();
        collectTaskModel.setTaskStatus(PAUSE);
        collectTaskModel.setTaskName(collectTaskReqEntity.getTaskName());
        try {
            collectTaskMapper.updateNotNullByName(collectTaskModel);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("暂停任务错误：" + e.getMessage());
            collectTaskRespEntity.setRetCode(-1);
            collectTaskRespEntity.setMessage("暂停任务错误：" + e.getMessage());
        }
    }

    /**
     * 删除任务
     * @param collectTaskReqEntity
     * @param collectTaskRespEntity
     */
    private void deleteTask(CollectTaskReqEntity collectTaskReqEntity, CollectTaskRespEntity collectTaskRespEntity){
        try {
            collectTaskMapper.deleteByName(collectTaskReqEntity.getTaskName());
        }catch (Exception e){
            e.printStackTrace();
            logger.error("删除任务错误：" + e.getMessage());
            collectTaskRespEntity.setRetCode(-1);
            collectTaskRespEntity.setMessage("删除任务错误：" + e.getMessage());
        }
    }

    private void selectAllTask(CollectTaskRespEntity collectTaskRespEntity){
        try {
            List<CollectTaskModel> collectTaskModelList = collectTaskMapper.selectAll();
            collectTaskRespEntity.setCollectTaskModel(collectTaskModelList);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("查询所有任务错误：" + e.getMessage());
            collectTaskRespEntity.setRetCode(-1);
            collectTaskRespEntity.setMessage("查询所有任务错误：" + e.getMessage());
        }
    }



}
