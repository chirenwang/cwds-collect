package com.wcc.wds.web.mapper;

import com.wcc.wds.web.model.WithdrawContributionModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawContributionMapper {

    WithdrawContributionModel selectAll();

    WithdrawContributionModel selectById(String id);

    List<String> selectAllFilePath();

    void insert(WithdrawContributionModel withdrawContributionModel);

    void deleteById(String id);


}
