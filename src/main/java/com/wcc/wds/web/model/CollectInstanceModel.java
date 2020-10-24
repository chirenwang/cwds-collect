package com.wcc.wds.web.model;


import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 采集任务实例表Dao
 */
@Data
public class CollectInstanceModel {
    /**
     * 实例id
     */
    private String instanceId;
    /**
     * 任务id
     */
    private String taskId;
    /**
     * 实例运行状态
     */
    private String instanceStatus;
    /**
     * 实例开始时间
     */
    private Timestamp startTime;
    /**
     * 实例结束时间
     */
    private Timestamp endTime;
    /**
     * 重试次数
     */
    private int retryTime;

}
