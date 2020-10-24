package com.wcc.wds.web.mapper;

import com.wcc.wds.web.model.CollectInstanceModel;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CollectInstanceMapper {
    /**
     * 创建采集任务实例
     * @param collectInstance
     */
    void insert(CollectInstanceModel collectInstance);

    /**
     * 删除实例
     * @param instanceId
     */
    void deleteById(String instanceId);

    /**
     * 查询所有实例
     * @return
     */
    List<CollectInstanceModel> selectAll();

    /**
     * 根据状态查实例
     * @return
     */
    List<CollectInstanceModel> selectByStatus(String[] instanceStatus);


    /**
     * 查询失败实例
     * @return
     */
    List<CollectInstanceModel> selectFail();

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
    void updateEndTimeById(String instanceId, Timestamp endTime);


    /**
     * 查找最新的实例
     * @return
     */
    List<CollectInstanceModel> selectLatest();

    /**
     * 根据id查找最新实例
     * @return
     */
    Timestamp selectLatestSuccessById(String taskId);



}
