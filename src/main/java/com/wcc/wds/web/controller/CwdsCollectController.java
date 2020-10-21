package com.wcc.wds.web.controller;

import com.wcc.wds.web.entity.CollectTaskReq;
import com.wcc.wds.web.entity.CollectTaskResp;
import com.wcc.wds.web.entity.DataModifyResp;
import com.wcc.wds.web.entity.SearchResp;
import com.wcc.wds.web.service.CollectTaskService;
import com.wcc.wds.web.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public CollectTaskResp collectTask(@RequestBody CollectTaskReq collectTaskReq){
        return collectTaskService.collectTask(collectTaskReq);
    }

    /**
     * 数据撤稿及恢复接口
     */
    @RequestMapping(value = "/dataModify", method = RequestMethod.POST)
    public DataModifyResp dataModify(){

        return new DataModifyResp();
    }

    /**
     * 查询接口
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public SearchResp search(){

        return new SearchResp();
    }






}
