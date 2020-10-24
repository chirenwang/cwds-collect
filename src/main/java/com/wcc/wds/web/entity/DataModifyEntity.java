/**  
 * DataModifyEntity.java
 * com.wcc.wds.web.entity
 * 
 * @author pengguang
 * @date 2020年10月23日 下午11:22:44
 * 版权所有
 */
package com.wcc.wds.web.entity;

import javax.validation.constraints.NotNull;

/**
 * @author pengguang
 * @date 2020年10月23日 下午11:22:44
 */

public class DataModifyEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "稿件id不能为空")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
