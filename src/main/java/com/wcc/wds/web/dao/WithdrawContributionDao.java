package com.wcc.wds.web.dao;

import com.wcc.wds.web.model.WithdrawContribution;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawContributionDao {

    WithdrawContribution selectAll();

    WithdrawContribution selectById(String id);

    void insert(WithdrawContribution withdrawContribution);

    void deleteById(String id);


}
