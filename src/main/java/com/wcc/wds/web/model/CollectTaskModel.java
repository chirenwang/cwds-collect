package com.wcc.wds.web.model;


import lombok.Data;

import java.sql.Timestamp;

/**
 * 采集任务表Dao
 */
@Data
public class CollectTaskModel {
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
     * 任务状态
     */
    private String taskStatus;
    /**
     * 任务类型
     */
    private String taskType;
    /**
     * 线程数
     */
    private int threadNum;
    /**
     * 任务创建时间
     */
    private Timestamp createTime;
    /**
     * 域名id
     */
    private String domainId;
    /**
     * 域名url
     */
    private String domainUrl;

}
