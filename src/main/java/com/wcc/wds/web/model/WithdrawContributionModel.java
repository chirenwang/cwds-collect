package com.wcc.wds.web.model;

import lombok.Data;

/**
 * 撤稿状态表Dao
 */
@Data
public class WithdrawContributionModel {
    /**
     * 稿件id
     */
    private String id;
    /**
     * 撤稿方式
     */
    private String withdrawType;
    /**
     * 撤稿后文件路径
     */
    private String withdrawFilePath;
    /**
     * 源文件路径
     */
    private String filePath;

}
