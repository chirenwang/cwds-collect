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

}
