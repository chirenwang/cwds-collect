package com.wcc.wds.web.bean;


import java.util.Date;

/**
 * 采集任务实例表Dao
 */
public class CollectInstanceBean {
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
     * 实例创建时间
     */
    private Date createTime;
    /**
     * 实例开始时间
     */
    private Date startTime;
    /**
     * 实例结束时间
     */
    private Date endTime;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getInstanceStatus() {
        return instanceStatus;
    }

    public void setInstanceStatus(String instanceStatus) {
        this.instanceStatus = instanceStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
