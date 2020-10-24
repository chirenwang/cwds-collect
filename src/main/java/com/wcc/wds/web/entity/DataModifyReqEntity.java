package com.wcc.wds.web.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

/**
 * 数据撤稿与恢复接口请求参数
 */
@Data
public class DataModifyReqEntity {

    /**
     * 操作
     */
    @NotNull(message = "操作类型不能为空")
    private String operate;
    /**
     * 稿件id
     */
    @NotNull(message = "稿件id不能为空")
    private ArrayList<String> ids;


}
