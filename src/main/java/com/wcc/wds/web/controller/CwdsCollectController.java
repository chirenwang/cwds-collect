package com.wcc.wds.web.controller;

import com.wcc.wds.web.bean.CollectTaskRequestBean;
import com.wcc.wds.web.bean.CollectTaskResponseBean;
import com.wcc.wds.web.bean.DataModifyResponseBean;
import com.wcc.wds.web.bean.SearchResponseBean;
import com.wcc.wds.web.service.CollectTaskService;
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

    /**
     * 采集任务接口
     */
    @RequestMapping(value = "/collectTask", method = RequestMethod.POST)
    public CollectTaskResponseBean collectTask(@RequestBody CollectTaskRequestBean collectTaskRequestBean){
        return collectTaskService.collectTask(collectTaskRequestBean);
    }

    /**
     * 数据撤稿及恢复接口
     */
    @RequestMapping(value = "/dataModify", method = RequestMethod.POST)
    public DataModifyResponseBean dataModify(){

        return new DataModifyResponseBean();
    }

    /**
     * 查询接口
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public SearchResponseBean search(){

        return new SearchResponseBean();
    }






}
