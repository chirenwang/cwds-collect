package com.wcc.wds.web.dao;

/**
 * 撤稿状态表Dao
 */
public class WithdrawContributionDao {
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
    private String filePath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(String withdrawType) {
        this.withdrawType = withdrawType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
