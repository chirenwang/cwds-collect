package com.wcc.wds.web.entity;


import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 采集任务接口请求参数
 */
@Data
public class CollectTaskReqEntity extends BaseEntity{
    /**
     * 操作类型
     */
    @NotNull(message = "操作类型不能为空")
    private String operate;
    /**
     * 采集路径
     */
    private String collectPath;
    /**
     * 文件名正则
     */
    private String regex;
    /**
     * 任务类型
     */
    private String taskType;
    /**
     * 任务名
     */
    @NotNull(message = "任务名不能为空")
    private String taskName;
    /**
     * 线程数
     */
    private int threadNum;
    /**
     * 域名Id
     */
    private String domainId;
    /**
     * 域名url
     */
    private String domainUrl;

}
