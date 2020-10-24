package com.wcc.wds.web.dao;

import com.wcc.wds.web.model.WithdrawContributionModel;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawContributionDao {

    List<WithdrawContributionModel> selectAll();

    WithdrawContributionModel selectById(String id);

    void insert(WithdrawContributionModel withdrawContribution);

    void deleteById(String id);

}
