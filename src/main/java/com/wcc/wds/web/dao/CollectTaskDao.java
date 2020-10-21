package com.wcc.wds.web.dao;

import com.wcc.wds.web.model.CollectTask;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectTaskDao {

     CollectTask selectAll();

     void insert(CollectTask collectTask);

     void deleteByName(String taskName);

     void updateNotNullByName(CollectTask collectTask);

}
