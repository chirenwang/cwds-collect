package com.wcc.wds.web.controller;

import com.wcc.wds.web.entity.CollectTaskReqEntity;
import com.wcc.wds.web.model.CollectTaskModel;
import com.wcc.wds.web.response.Response;
import com.wcc.wds.web.response.ResponseEnum;
import com.wcc.wds.web.response.ServiceException;
import com.wcc.wds.web.service.CollectTaskService;
import com.wcc.wds.web.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 接口类
 */
@Controller
public class CwdsCollectController extends BaseController {

    @Autowired
    private CollectTaskService collectTaskService;

    @Autowired
    private SearchService searchService;

    /**
     * 采集任务接口
     */
    @RequestMapping(value = "/collectTask", method = RequestMethod.POST)
    public Response<List<CollectTaskModel>> collectTask(@Valid @RequestBody CollectTaskReqEntity collectTaskReqEntity) {
        try {
            return Response.success(collectTaskService.collectTask(collectTaskReqEntity));
        }catch (ServiceException e){
            return Response.fail(e.getResponseEnum());
        }
    }


    /**
     * 查询接口
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Response<ArrayList<String>> search(@RequestBody String queryString) {
        try {
            return Response.success(searchService.search(queryString));
        }catch (ServiceException e){
            return Response.fail(ResponseEnum.ES_SEARCH_FAILED);
        }

    }

}
