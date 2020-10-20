package com.wcc.wds.web.mapper;

import com.wcc.wds.bean.CollectTaskBean;

public interface CollectTaskMapper {

     CollectTaskBean selectCollectTask();

     void insertCollectTask(CollectTaskBean collectTaskBean);

     void deleteCollectTask(CollectTaskBean collectTaskBean);

     void updateTaskStatus(CollectTaskBean collectTaskBean);

     void updateReleaseRule(CollectTaskBean collectTaskBean);

     void updateThreadNum(CollectTaskBean collectTaskBean);

     void updateCollectPath(CollectTaskBean collectTaskBean);



}
