package com.youqusoft.vision.flow.tenant.model.query;

import com.youqusoft.vision.flow.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 租户信息分页查询对象
 *
 * @author youqulife
 * @since 2025-09-26 22:24
 */
@Schema(description ="租户信息查询对象")
@Getter
@Setter
public class TenantQuery extends BasePageQuery {

}
