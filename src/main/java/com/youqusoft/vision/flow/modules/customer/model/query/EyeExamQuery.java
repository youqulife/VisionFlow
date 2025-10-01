package com.youqusoft.vision.flow.modules.customer.model.query;

import com.youqusoft.vision.flow.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 验光记录分页查询对象
 *
 * @author youqusoft
 * @since 2025-10-01 11:31
 */
@Schema(description ="验光记录查询对象")
@Getter
@Setter
public class EyeExamQuery extends BasePageQuery {
    private String customerName;
    private String customerPhone;
    private String examDateBegin;
    private String examDateEnd;
}
