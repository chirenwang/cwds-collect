package com.wcc.wds.web.model;

import lombok.Data;

/**
 * 撤稿状态表Dao
 */
@Data
public class WithdrawContribution {
    /**
     * 稿件id
     */
    private String id;
    /**
     * 撤稿方式
     */
    private String withdraw_type;
    /**
     * 撤稿后文件路径
     */
    private String withdraw_file_path;
    /**
     * 源文件路径
     */
    private String file_path;

}
