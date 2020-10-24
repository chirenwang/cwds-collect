package com.wcc.wds.web.entity;

import lombok.Data;

@Data
public class NodeNameEntity {

    private int id;

    private String name;

    public NodeNameEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
