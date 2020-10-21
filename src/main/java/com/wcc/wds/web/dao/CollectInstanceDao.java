package com.wcc.wds.web.dao;

import com.wcc.wds.web.bean.CollectInstanceBean;

public interface CollectInstanceDao {
    /**
     * 创建采集任务实例
     * @param collectInstanceBean
     */
    void insertCollectInstance(CollectInstanceBean collectInstanceBean);

    /**
     *
     * @param collectInstanceBean
     */
    void updateInstanceStatus(CollectInstanceBean collectInstanceBean);
}
