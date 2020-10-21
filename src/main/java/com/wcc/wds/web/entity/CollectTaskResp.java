package com.wcc.wds.web.entity;

import lombok.Data;

/**
 * 采集任务接口返回参数
 */
@Data
public class CollectTaskResp {


    public CollectTaskResp(int retCode, String message) {
        this.retCode = retCode;
        this.message = message;
    }

    /**
     * 状态码
     */
    private int retCode;
    /**
     * 接口消息
     */
    private String message;

}
