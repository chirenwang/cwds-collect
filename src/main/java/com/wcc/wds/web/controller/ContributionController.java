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

import com.wcc.wds.web.entity.RestoreContributionEntity;
import com.wcc.wds.web.service.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wcc.wds.web.entity.WithdrawContributionEntity;
import com.wcc.wds.web.response.Response;

/**
 * 稿件接口
 *
 * @author pengguang
 * @date 2020年10月23日 下午9:49:29
 */
@RequestMapping("/contribution")
@Controller
@ResponseBody
public class ContributionController extends BaseController {

    @Autowired
    private ContributionService contributionService;

    /**
     * 恢复接口
     * @param restoreContributionEntity
     * @return
     */
    @RequestMapping(value = "/restore", method = RequestMethod.PUT)
    public Response<String> restore(@Valid @RequestBody RestoreContributionEntity restoreContributionEntity) {
        contributionService.restore(restoreContributionEntity);
        return Response.success();
    }

    /**
     * 撤稿接口
     * @param withdrawContributionEntity
     * @return
     */
    @RequestMapping(value = "/withdraw", method = RequestMethod.PUT)
    public Response<String> revoke(@Valid @RequestBody WithdrawContributionEntity withdrawContributionEntity) {
        contributionService.revoke(withdrawContributionEntity);
        return Response.success();
    }


}
