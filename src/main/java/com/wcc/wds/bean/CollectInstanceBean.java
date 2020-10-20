package com.wcc.wds.bean;


import java.util.Date;

/**
 * 采集任务实例表Dao
 */
public class CollectInstanceBean {
    /**
     * 实例id
     */
    private String instance_id;
    /**
     * 任务id
     */
    private String task_id;
    /**
     * 实例运行状态
     */
    private String instance_status;
    /**
     * 实例创建时间
     */
    private Date create_time;
    /**
     * 实例开始时间
     */
    private Date start_time;
    /**
     * 实例结束时间
     */
    private Date end_time;

    public String getInstance_id() {
        return instance_id;
    }

    public void setInstance_id(String instance_id) {
        this.instance_id = instance_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getInstance_status() {
        return instance_status;
    }

    public void setInstance_status(String instance_status) {
        this.instance_status = instance_status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }
}
