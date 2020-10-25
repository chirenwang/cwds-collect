package com.wcc.wds.web.dao;

import com.wcc.wds.web.mapper.CollectInstanceMapper;
import com.wcc.wds.web.model.CollectInstanceModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

public class CollectInstanceMapperTest extends BaseDaoTest {


    @Autowired
    public CollectInstanceMapper collectInstanceMapper;

    @Test
    public void test() {
        CollectInstanceModel collectInstanceModel = new CollectInstanceModel();
        collectInstanceModel.setInstanceId("updatestatus");
        collectInstanceModel.setStartTime(new Timestamp(System.currentTimeMillis()));
        collectInstanceModel.setTaskId("abcd");
        collectInstanceModel.setInstanceStatus("running");
        collectInstanceModel.setRetryTime(0);
        collectInstanceMapper.insert(collectInstanceModel);

        System.out.println(collectInstanceMapper.selectAll());





    }
}
