package com.wcc.wds.web.mapper;

import com.wcc.wds.bean.WithdrawContributionBean;

public interface WithdrawContributionMapper {

    WithdrawContributionBean selectWithdraw();

    WithdrawContributionBean selectWithdrawById(WithdrawContributionBean withdrawContributionBean);

    void insertWithdraw(WithdrawContributionBean withdrawContributionBean);

    void deleteWithdraw(WithdrawContributionBean withdrawContributionBean);


}
