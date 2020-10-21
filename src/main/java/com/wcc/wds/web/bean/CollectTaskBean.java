package com.wcc.wds.web.bean;


/**
 * 采集任务表Dao
 */
public class CollectTaskBean {
    /**
     * 任务名
     */
    private String taskName;
    /**
     * 任务id
     */
    private String id;
    /**
     * 采集路径
     */
    private String collectPath;
    /**
     * 文件名正则
     */
    private String regex;
    /**
     * 采集时间
     */
    private String collectTime;
    /**
     * 任务状态
     */
    private String taskStatus;
    /**
     * 周期
     */
    private int revolution;
    /**
     * 线程数
     */
    private int threadNum;
    /**
     * 任务创建时间
     */
    private long createTime;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollectPath() {
        return collectPath;
    }

    public void setCollectPath(String collectPath) {
        this.collectPath = collectPath;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getRevolution() {
        return revolution;
    }

    public void setRevolution(int revolution) {
        this.revolution = revolution;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
