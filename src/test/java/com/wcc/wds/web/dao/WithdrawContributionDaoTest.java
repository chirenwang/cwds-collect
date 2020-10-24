/**  
 * WithdrawContributionDaoTest.java
 * com.wcc.wds.dao
 * 
 * @author pengguang
 * @date 2020年10月21日 下午9:20:46
 * 版权所有
 */
package com.wcc.wds.web.dao;

import com.wcc.wds.web.mapper.WithdrawContributionMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wcc.wds.web.model.WithdrawContributionModel;

public class WithdrawContributionDaoTest extends BaseDaoTest {

    @Autowired
    public WithdrawContributionMapper withdrawContributionMapper;

    @Test
    public void test() {
        WithdrawContributionModel wcb = new WithdrawContributionModel();

        wcb.setId("3");
        wcb.setWithdraw_file_path("asd");
        wcb.setWithdraw_type("asd");
        wcb.setFile_path("asdf");
        withdrawContributionMapper.insert(wcb);
        System.out.println(withdrawContributionMapper.selectAll());
    }
}
