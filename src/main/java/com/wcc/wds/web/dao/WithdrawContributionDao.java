package com.wcc.wds.web.dao;

import com.wcc.wds.web.bean.WithdrawContributionBean;

public interface WithdrawContributionDao {

    WithdrawContributionBean selectWithdraw();

    WithdrawContributionBean selectWithdrawById(WithdrawContributionBean withdrawContributionBean);

    void insertWithdraw(WithdrawContributionBean withdrawContributionBean);

    void deleteWithdraw(WithdrawContributionBean withdrawContributionBean);


}
