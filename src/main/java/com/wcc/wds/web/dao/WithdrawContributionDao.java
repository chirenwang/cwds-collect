package com.wcc.wds.web.dao;

import com.wcc.wds.web.bean.WithdrawContributionBean;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawContributionDao {

    WithdrawContributionBean selectWithdraw();

    WithdrawContributionBean selectWithdrawById(WithdrawContributionBean withdrawContributionBean);

    void insertWithdraw(WithdrawContributionBean withdrawContributionBean);

    void deleteWithdraw(WithdrawContributionBean withdrawContributionBean);


}
