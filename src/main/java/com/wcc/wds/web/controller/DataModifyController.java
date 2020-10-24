/**  
 * DataModifyController.java
 * com.wcc.wds.web.controller
 * 
 * @author pengguang
 * @date 2020年10月23日 下午9:49:29
 * 版权所有
 */
package com.wcc.wds.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wcc.wds.web.entity.DataModifyEntity;
import com.wcc.wds.web.response.Response;

/**
 * 撤稿接口
 *
 * @author pengguang
 * @date 2020年10月23日 下午9:49:29
 */
@RequestMapping("/withdrawContribution")
@Controller
@ResponseBody
public class DataModifyController extends BaseController {

    @RequestMapping(value = "/revoke", method = RequestMethod.PUT)
    public Response<String> revoke(@Valid @RequestBody DataModifyEntity withdrawContributionEntity) {
        System.out.println(withdrawContributionEntity);
        return Response.success();
    }
}
