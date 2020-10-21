package com.wcc.wds.web.service;

import com.sun.media.sound.RIFFInvalidFormatException;
import com.wcc.wds.web.bean.CollectTaskBean;
import com.wcc.wds.web.bean.CollectTaskRequestBean;
import com.wcc.wds.web.bean.CollectTaskResponseBean;
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


    public CollectTaskResponseBean collectTask(CollectTaskRequestBean collectTaskRequestBean){
        //返回参数
        CollectTaskResponseBean collectTaskResponseBean = new CollectTaskResponseBean(1, "success");
        try {
            //获取任务操作
            String operate = collectTaskRequestBean.getOperate();
            switch (operate){
                case CREATE:
                    createTask(collectTaskRequestBean, collectTaskResponseBean);
                    break;
                case MODIFY:
                    modifyTask(collectTaskRequestBean, collectTaskResponseBean);
                    break;
                case PAUSE:
                    pauseTask(collectTaskRequestBean, collectTaskResponseBean);
                    break;
                case DELETE:
                    deleteTask(collectTaskRequestBean, collectTaskResponseBean);
                    break;
                default:
                    //输入了未知的操作类型
                    logger.error("no such operate");
                    collectTaskResponseBean.setMessage("no such operate");
                    collectTaskResponseBean.setRetCode(-1);
            }
        }catch (Exception e){
            //发生异常
            e.printStackTrace();
            collectTaskResponseBean.setMessage(e.getMessage());
            collectTaskResponseBean.setRetCode(-1);
            logger.error(e.getMessage());
        }
        return collectTaskResponseBean;

    }


    private void createTask(CollectTaskRequestBean collectTaskRequestBean, CollectTaskResponseBean collectTaskResponseBean){
        CollectTaskBean collectTaskBean = new CollectTaskBean();
        UUID uuid = UUID.randomUUID();
        long createTime = System.currentTimeMillis();
        collectTaskBean.setId(uuid.toString());
        collectTaskBean.setCreateTime(createTime);
        collectTaskBean.setCollectPath(collectTaskRequestBean.getCollectPath());
        collectTaskBean.setCollectTime(collectTaskRequestBean.getCollectTime());
        collectTaskBean.setRegex(collectTaskRequestBean.getRegex());
        int revolution  = collectTaskRequestBean.getRevolution();
        if (revolution == 0){ revolution = DAY_MINUTE;}
        collectTaskBean.setRevolution(revolution);
        collectTaskBean.setTaskName(collectTaskRequestBean.getTaskName());
        collectTaskBean.setThreadNum(collectTaskRequestBean.getThreadNum());
        collectTaskBean.setTaskStatus(RUNNING);

        try{
            collectTaskDao.insertCollectTask(collectTaskBean);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            collectTaskResponseBean.setRetCode(-1);
            collectTaskResponseBean.setMessage(e.getMessage());
        }
    }

    private void modifyTask(CollectTaskRequestBean collectTaskRequestBean, CollectTaskResponseBean collectTaskResponseBean){
        CollectTaskBean collectTaskBean = new CollectTaskBean();

    }

    private void pauseTask(CollectTaskRequestBean collectTaskRequestBean, CollectTaskResponseBean collectTaskResponseBean){}

    private void deleteTask(CollectTaskRequestBean collectTaskRequestBean, CollectTaskResponseBean collectTaskResponseBean){}

}
