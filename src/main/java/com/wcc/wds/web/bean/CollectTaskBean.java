package com.wcc.wds.web.bean;


/**
 * 采集任务表Dao
 */
public class CollectTaskBean {
    /**
     * 任务名
     */
    private String task_name;
    /**
     * 任务id
     */
    private String id;
    /**
     * 采集路径
     */
    private String collect_path;
    /**
     * 文件名正则
     */
    private String regex;
    /**
     * 采集时间
     */
    private String collect_time;
    /**
     * 任务类型
     */
    private String task_type;
    /**
     * 任务状态
     */
    private String task_status;
    /**
     * 周期
     */
    private int revolution;
    /**
     * 线程数
     */
    private int thread_num;

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollect_path() {
        return collect_path;
    }

    public void setCollect_path(String collect_path) {
        this.collect_path = collect_path;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getCollect_time() {
        return collect_time;
    }

    public void setCollect_time(String collect_time) {
        this.collect_time = collect_time;
    }

    public String getTask_type() {
        return task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public int getRevolution() {
        return revolution;
    }

    public void setRevolution(int revolution) {
        this.revolution = revolution;
    }

    public int getThread_num() {
        return thread_num;
    }

    public void setThread_num(int thread_num) {
        this.thread_num = thread_num;
    }
}
