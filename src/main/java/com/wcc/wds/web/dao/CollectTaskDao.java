package com.wcc.wds.web.dao;

/**
 * 采集任务表Dao
 */
public class CollectTaskDao {
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
     * 发布规则
     */
    private String releaseRule;
    /**
     * 采集时间
     */
    private String time;
    /**
     * 任务类型
     */
    private String taskType;
    /**
     * 任务状态
     */
    private String taskStatus;
    /**
     * 周期
     */
    private String revolution;
    /**
     * 线程数
     */
    private int threadNum;


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

    public String getReleaseRule() {
        return releaseRule;
    }

    public void setReleaseRule(String releaseRule) {
        this.releaseRule = releaseRule;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getRevolution() {
        return revolution;
    }

    public void setRevolution(String revolution) {
        this.revolution = revolution;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }
}
