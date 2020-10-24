/**  
 * WithdrawContributionDaoTest.java
 * com.wcc.wds.dao
 * 
 * @author pengguang
 * @date 2020年10月21日 下午9:20:46
 * 版权所有
 */
package com.wcc.wds.web.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wcc.wds.web.model.WithdrawContribution;

public class WithdrawContributionDaoTest extends BaseDaoTest {

    @Autowired
    public WithdrawContributionDao withdrawContributionDao;

    @Test
    public void test() {
        WithdrawContribution wcb = new WithdrawContribution();

        wcb.setId("3");
        wcb.setWithdraw_file_path("asd");
        wcb.setWithdraw_type("asd");
        wcb.setFile_path("asdf");
        withdrawContributionDao.insert(wcb);
        System.out.println(withdrawContributionDao.selectAll());
    }
}
