package com.youqusoft.vision.flow.tenant.model.query;

import com.youqusoft.vision.flow.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 租户角色分页查询对象
 *
 * @author youqulife
 * @since 2025-09-27 08:47
 */
@Schema(description ="租户角色查询对象")
@Getter
@Setter
public class TenantRoleQuery extends BasePageQuery {

}
