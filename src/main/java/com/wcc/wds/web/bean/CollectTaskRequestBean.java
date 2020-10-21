package com.wcc.wds.web.bean;


/**
 * 采集任务接口请求参数
 */
public class CollectTaskRequestBean {
    /**
     * 任务类型
     */
    private String taskType;
    /**
     * 操作类型
     */
    private String operate;
    /**
     * 采集路径
     */
    private String collectPath;
    /**
     * 采集时间
     */
    private String time;
    /**
     * 任务名
     */
    private String taskName;
    /**
     * 线程数
     */
    private int threadNum;


    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getCollectPath() {
        return collectPath;
    }

    public void setCollectPath(String collectPath) {
        this.collectPath = collectPath;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }
}
