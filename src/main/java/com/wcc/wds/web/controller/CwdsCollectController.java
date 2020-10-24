package com.wcc.wds.web.controller;

import com.wcc.wds.web.entity.CollectTaskReqEntity;
import com.wcc.wds.web.entity.CollectTaskRespEntity;
import com.wcc.wds.web.entity.DataModifyRespEntity;
import com.wcc.wds.web.entity.SearchRespEntity;
import com.wcc.wds.web.service.CollectTaskService;
import com.wcc.wds.web.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * 接口类
 */
@Controller
public class CwdsCollectController {

    @Autowired
    private CollectTaskService collectTaskService;

    @Autowired
    private SearchService searchService;

    /**
     * 采集任务接口
     */
    @RequestMapping(value = "/collectTask", method = RequestMethod.POST)
    public CollectTaskRespEntity collectTask(@Valid @RequestBody CollectTaskReqEntity collectTaskReqEntity) {
        return collectTaskService.collectTask(collectTaskReqEntity);
    }

    /**
     * 数据撤稿及恢复接口
     */
    @RequestMapping(value = "/dataModify", method = RequestMethod.POST)
    public DataModifyRespEntity dataModify() {

        return new DataModifyRespEntity();
    }

    /**
     * 查询接口
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public SearchRespEntity search() {

        return new SearchRespEntity();
    }

}
