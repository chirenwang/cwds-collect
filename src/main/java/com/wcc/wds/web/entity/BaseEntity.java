/**  
 * BaseEntity.java
 * com.wcc.wds.web.entity
 * 
 * @author pengguang
 * @date 2020年10月23日 下午11:49:09
 * 版权所有
 */
package com.wcc.wds.web.entity;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * 实体基类
 *
 * @author pengguang
 * @date 2020年10月23日 下午11:49:09
 */
public class BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
