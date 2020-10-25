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
import java.util.List;
import java.util.Map;

/**
 * @author pengguang
 * @date 2020年10月23日 下午11:22:44
 */
@Data
public class WithdrawContributionEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "稿件id不能为空")
    private Map<String, String> idAndFilePath;

    @NotNull(message = "稿件类型不能为空")
    private String withdrawType;

}
