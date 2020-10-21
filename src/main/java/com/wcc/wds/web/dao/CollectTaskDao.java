package com.wcc.wds.web.dao;

import com.wcc.wds.web.bean.CollectTaskBean;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectTaskDao {

     CollectTaskBean selectCollectTask();

     void insertCollectTask(CollectTaskBean collectTaskBean);

     void deleteCollectTask(CollectTaskBean collectTaskBean);

     void updateTaskStatus(CollectTaskBean collectTaskBean);

     void updateRegex(CollectTaskBean collectTaskBean);

     void updateThreadNum(CollectTaskBean collectTaskBean);

     void updateCollectPath(CollectTaskBean collectTaskBean);



}
