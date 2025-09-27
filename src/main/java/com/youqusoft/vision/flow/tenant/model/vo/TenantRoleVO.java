package com.youqusoft.vision.flow.tenant.model.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 租户角色视图对象
 *
 * @author youqulife
 * @since 2025-09-27 08:47
 */
@Getter
@Setter
@Schema( description = "租户角色视图对象")
public class TenantRoleVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "角色ID")
    private Long id;
    @Schema(description = "所属租户ID")
    private Long tenantId;
    @Schema(description = "角色名称")
    private String name;
    @Schema(description = "角色编码")
    private String code;
    @Schema(description = "角色描述")
    private String description;
    @Schema(description = "是否系统角色")
    private Integer isSystem;
    @Schema(description = "是否激活")
    private Integer isActive;
    @Schema(description = "创建时间")
    private ${fieldConfig.fieldType} createdAt;
    @Schema(description = "更新时间")
    private ${fieldConfig.fieldType} updatedAt;
}
