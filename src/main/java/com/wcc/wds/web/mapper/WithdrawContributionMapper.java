package com.wcc.wds.web.mapper;

import com.wcc.wds.web.model.WithdrawContributionModel;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawContributionMapper {

    WithdrawContributionModel selectAll();

    WithdrawContributionModel selectById(String id);

    void insert(WithdrawContributionModel withdrawContributionModel);

    void deleteById(String id);


}
