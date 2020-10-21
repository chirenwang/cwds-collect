package com.wcc.wds.web.bean;


/**
 * 采集任务接口请求参数
 */
public class CollectTaskRequestBean {
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
    private String collectTime;
    /**
     * 文件名正则
     */
    private String regex;
    /**
     * 周期
     */
    private int revolution;
    /**
     * 任务名
     */
    private String taskName;
    /**
     * 线程数
     */
    private int threadNum;

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

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public int getRevolution() {
        return revolution;
    }

    public void setRevolution(int revolution) {
        this.revolution = revolution;
    }
}
