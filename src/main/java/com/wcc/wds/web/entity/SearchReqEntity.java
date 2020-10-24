package com.wcc.wds.web.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SearchReqEntity {

    @NotNull(message = "查询参数不能为空")
    private String searchParam;

}
