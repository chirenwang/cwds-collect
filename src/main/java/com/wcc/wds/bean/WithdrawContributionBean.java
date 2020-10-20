package com.wcc.wds.bean;

/**
 * 撤稿状态表Dao
 */
public class WithdrawContributionBean {
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWithdraw_type() {
        return withdraw_type;
    }

    public void setWithdraw_type(String withdraw_type) {
        this.withdraw_type = withdraw_type;
    }

    public String getWithdraw_file_path() {
        return withdraw_file_path;
    }

    public void setWithdraw_file_path(String withdraw_file_path) {
        this.withdraw_file_path = withdraw_file_path;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
