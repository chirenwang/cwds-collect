/**
 * DataModifyEntity.java
 * com.wcc.wds.web.entity
 *
 * @author pengguang
 * @date 2020年10月23日 下午11:22:44
 * 版权所有
 */
package com.wcc.wds.web.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author pengguang
 * @date 2020年10月23日 下午11:22:44
 */
@Data
public class WithdrawContributionEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "稿件id不能为空")
    private String id;

    @NotNull(message = "稿件类型不能为空")
    private String withdrawType;

    @NotNull(message = "稿件路径不能为空")
    private String filePath;
}
