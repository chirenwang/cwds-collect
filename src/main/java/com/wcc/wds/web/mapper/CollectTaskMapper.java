package com.wcc.wds.web.mapper;

import com.wcc.wds.web.model.CollectTaskModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectTaskMapper {

     List<CollectTaskModel> selectAll();

     CollectTaskModel selectById(String id);

     List<String> selectIdByStatus(String taskStatus);

     void insert(CollectTaskModel collectTaskModel);

     void deleteByName(String taskName);

     void updateNotNullByName(CollectTaskModel collectTaskModel);

}
