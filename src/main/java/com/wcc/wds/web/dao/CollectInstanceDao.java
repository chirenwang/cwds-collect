package com.wcc.wds.web.dao;

import com.wcc.wds.web.model.CollectInstance;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CollectInstanceDao {
    /**
     * 创建采集任务实例
     * @param collectInstance
     */
    void insert(CollectInstance collectInstance);

    /**
     * 删除实例
     * @param instanceId
     */
    void deleteById(String instanceId);

    /**
     * 查询所有实例
     * @return
     */
    List<CollectInstance> selectAll();

    /**
     * 根据状态查实例
     * @return
     */
    List<CollectInstance> selectByStatus(String[] instanceStatus);


    /**
     * 查询失败实例
     * @return
     */
    List<CollectInstance> selectFail();

    /**
     * 更新重试次数
     * @param instanceId
     * @param retryTime
     */
    void updateRetryTimeById(String instanceId, int retryTime);

    /**
     * 根据实例id更新实例状态
     * @param instanceId
     * @param instanceStatus
     */
    void updateStatusById(String instanceId, String instanceStatus);


    /**
     * 根据实例id更新结束时间
     * @param instanceId
     * @param endTime
     */
    void updateEndTimeById(String instanceId, Date endTime);


    /**
     * 查找最新的实例
     * @return
     */
    List<CollectInstance> selectLatest();



}
