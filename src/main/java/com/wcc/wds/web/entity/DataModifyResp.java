package com.wcc.wds.web.entity;

import lombok.Data;

/**
 * 数据撤稿与恢复接口返回参数
 */
@Data
public class DataModifyResp {
    /**
     * 状态码
     */
    private int retCode;
    /**
     * 接口信息
     */
    private int message;

}
