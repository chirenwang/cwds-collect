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
    public WithdrawContributionMapper withdrawContributionDao;

    @Test
    public void test() {
        WithdrawContributionModel wcb = new WithdrawContributionModel();

        wcb.setId("3");
        wcb.setWithdrawFilePath("asd");
        wcb.setWithdrawType("asd");
        wcb.setFilePath("asdf");
        withdrawContributionDao.insert(wcb);
        System.out.println(withdrawContributionDao.selectAll());
    }

    @Test
    public void test2(){
        System.out.println(withdrawContributionDao.selectById("1"));
    }
}
