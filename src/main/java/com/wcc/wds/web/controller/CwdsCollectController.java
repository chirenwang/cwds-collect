package com.wcc.wds.web.controller;

import com.wcc.wds.bean.CollectTaskResponseBean;
import com.wcc.wds.bean.DataModifyResponseBean;
import com.wcc.wds.bean.SearchResponseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 接口类
 */
@Controller
public class CwdsCollectController {

    /**
     * 采集任务接口
     */
    @RequestMapping(value = "/collectTask", method = RequestMethod.POST)
    public CollectTaskResponseBean collectTask(){

        return new CollectTaskResponseBean();
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
