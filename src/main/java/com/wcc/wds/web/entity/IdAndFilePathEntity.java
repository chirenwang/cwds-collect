package com.wcc.wds.web.entity;

import lombok.Data;

@Data
public class IdAndFilePathEntity extends BaseEntity {
    /**
     * id
     */
    private String id;
    /**
     * 文件路径
      */
    private String filePath;
}
