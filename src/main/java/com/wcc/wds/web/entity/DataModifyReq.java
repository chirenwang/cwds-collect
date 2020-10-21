package com.wcc.wds.web.entity;

import lombok.Data;

import java.util.ArrayList;

/**
 * 数据撤稿与恢复接口请求参数
 */
@Data
public class DataModifyReq {

    /**
     * 操作
     */
    private String operate;
    /**
     * 稿件id
     */
    private ArrayList<String> ids;


}
