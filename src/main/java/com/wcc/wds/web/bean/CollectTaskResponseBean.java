package com.wcc.wds.web.bean;

/**
 * 采集任务接口返回参数
 */
public class CollectTaskResponseBean {


    public CollectTaskResponseBean(int retCode, String message) {
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
