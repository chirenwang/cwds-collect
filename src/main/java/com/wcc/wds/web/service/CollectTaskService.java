package com.wcc.wds.web.service;

import com.wcc.wds.web.model.CollectTaskModel;
import com.wcc.wds.web.entity.CollectTaskReqEntity;
import com.wcc.wds.web.mapper.CollectTaskMapper;
import com.wcc.wds.web.response.ResponseEnum;
import com.wcc.wds.web.response.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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


    public List<CollectTaskModel> collectTask(CollectTaskReqEntity collectTaskReqEntity){
        //返回参数
            //获取任务操作
            String operate = collectTaskReqEntity.getOperate();
            switch (operate){
                case CREATE:
                    createTask(collectTaskReqEntity);
                    break;
                case MODIFY:
                    modifyTask(collectTaskReqEntity);
                    break;
                case PAUSE:
                    pauseTask(collectTaskReqEntity);
                    break;
                case DELETE:
                    deleteTask(collectTaskReqEntity);
                    break;
                case SELECT:
                    return selectAllTask();
                default:
                    //输入了未知的操作类型
                    logger.error("no such operate");
                    throw new ServiceException(ResponseEnum.NO_SUCH_OPERATE);
            }
        return new ArrayList<>();
    }

    /**
     * 创建任务
     * @param collectTaskReqEntity 请求参数
     */
    private void createTask(CollectTaskReqEntity collectTaskReqEntity){
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
            logger.error("创建任务错误：" + e.getMessage());
            throw new ServiceException(ResponseEnum.CREATE_TASK_FAILED, e);
        }
    }

    /**
     * 更改任务
     * @param collectTaskReqEntity
     */
    private void modifyTask(CollectTaskReqEntity collectTaskReqEntity){
        CollectTaskModel collectTaskModel = new CollectTaskModel();
        collectTaskModel.setRegex(collectTaskReqEntity.getRegex());
        collectTaskModel.setCollectPath(collectTaskModel.getCollectPath());
        collectTaskModel.setThreadNum(collectTaskModel.getThreadNum());
        collectTaskModel.setTaskName(collectTaskReqEntity.getTaskName());
        try {
            collectTaskMapper.updateNotNullByName(collectTaskModel);
        }catch (Exception e){
            logger.error("更改任务错误: " + e.getMessage());
            throw new ServiceException(ResponseEnum.MODIFY_TASK_FAILED, e);
        }

    }

    /**
     * 暂停任务
     * @param collectTaskReqEntity
     */
    private void pauseTask(CollectTaskReqEntity collectTaskReqEntity){
        CollectTaskModel collectTaskModel = new CollectTaskModel();
        collectTaskModel.setTaskStatus(PAUSE);
        collectTaskModel.setTaskName(collectTaskReqEntity.getTaskName());
        try {
            collectTaskMapper.updateNotNullByName(collectTaskModel);
        }catch (Exception e){
            logger.error("暂停任务错误：" + e.getMessage());
            throw new ServiceException(ResponseEnum.PAUSE_TASK_FAILED, e);
        }
    }

    /**
     * 删除任务
     * @param collectTaskReqEntity
     */
    private void deleteTask(CollectTaskReqEntity collectTaskReqEntity){
        try {
            collectTaskMapper.deleteByName(collectTaskReqEntity.getTaskName());
        }catch (Exception e){
            logger.error("删除任务错误：" + e.getMessage());
            throw new ServiceException(ResponseEnum.DELETE_TASK_FAILED, e);
        }
    }

    private List<CollectTaskModel> selectAllTask(){
        try {
            return collectTaskMapper.selectAll();
        }catch (Exception e){
            logger.error("查询所有任务错误：" + e.getMessage());
            throw new ServiceException(ResponseEnum.SEARCH_TASK_FAILED, e);        }
    }



}
