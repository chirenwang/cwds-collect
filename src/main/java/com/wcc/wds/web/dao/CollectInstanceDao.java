package com.wcc.wds.web.dao;

import com.wcc.wds.web.bean.CollectInstanceBean;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectInstanceDao {
    /**
     * 创建采集任务实例
     * @param collectInstanceBean
     */
    void insertCollectInstance(CollectInstanceBean collectInstanceBean);

    /**
     * 更新实例状态
     * @param collectInstanceBean
     */
    void updateInstanceStatus(CollectInstanceBean collectInstanceBean);

    /**
     * 更新实例结束时间
     * @param collectInstanceBean
     */
    void updateEndTime(CollectInstanceBean collectInstanceBean);

    /**
     * 删除实例
     * @param collectInstanceBean
     */
    void deleteInstance(CollectInstanceBean collectInstanceBean);

    /**
     * 查询所有实例
     * @return
     */
    CollectInstanceBean selectCollectInstance();

    /**
     * 查询失败实例
     * @return
     */
    CollectInstanceBean selectFailTask();


}
