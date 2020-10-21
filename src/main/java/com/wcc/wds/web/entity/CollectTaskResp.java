package com.wcc.wds.web.entity;

/**
 * 采集任务接口返回参数
 */
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }
}
