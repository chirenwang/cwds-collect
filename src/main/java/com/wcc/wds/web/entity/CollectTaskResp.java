package com.wcc.wds.web.entity;

import com.wcc.wds.web.model.CollectTask;
import lombok.Data;

import java.util.List;

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
    /**
     * 所有采集任务
     */
    private List<CollectTask> collectTask;

}
