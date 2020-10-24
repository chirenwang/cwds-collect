package com.wcc.wds.web.entity;


import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 采集任务接口请求参数
 */
@Data
public class CollectTaskReq {
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
    @NotNull(message = "任务名不能为空")
    private String taskName;
    /**
     * 线程数
     */
    private int threadNum;

}
