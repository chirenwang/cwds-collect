package com.wcc.wds.web.model;


import lombok.Data;

import java.util.Date;

/**
 * 采集任务实例表Dao
 */
@Data
public class CollectInstance {
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
    private Date startTime;
    /**
     * 实例结束时间
     */
    private Date endTime;
    /**
     * 重试次数
     */
    private int retryTime;

}
