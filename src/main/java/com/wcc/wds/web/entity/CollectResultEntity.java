package com.wcc.wds.web.entity;

import lombok.Data;

@Data
public class CollectResultEntity {

    private int ret;

    private String message;

    public CollectResultEntity(int ret, String message) {
        this.ret = ret;
        this.message = message;
    }
}
