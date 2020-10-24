package com.wcc.wds.web.dao;

import com.wcc.wds.web.model.CollectTask;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectTaskDao {

     List<CollectTask> selectAll();

     CollectTask selectById(String id);

     List<String> selectIdByStatus(String taskStatus);

     void insert(CollectTask collectTask);

     void deleteByName(String taskName);

     void updateNotNullByName(CollectTask collectTask);

}
