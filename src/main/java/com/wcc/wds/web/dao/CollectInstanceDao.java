package com.wcc.wds.web.dao;

import com.wcc.wds.web.model.CollectInstance;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectInstanceDao {
    /**
     * 创建采集任务实例
     * @param collectInstance
     */
    void insert(CollectInstance collectInstance);

    /**
     * 删除实例
     * @param collectInstance
     */
    void deleteById(CollectInstance collectInstance);

    /**
     * 查询所有实例
     * @return
     */
    CollectInstance selectAll();

    /**
     * 查询失败实例
     * @return
     */
    CollectInstance selectFail();

    /**
     * 更新非空字段
     * @param collectInstance
     */
    void updateNotNullById(CollectInstance collectInstance);


}
