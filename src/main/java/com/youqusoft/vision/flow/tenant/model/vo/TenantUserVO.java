package com.youqusoft.vision.flow.tenant.model.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 租户用户（多租户）视图对象
 *
 * @author youqulife
 * @since 2025-09-27 08:53
 */
@Getter
@Setter
@Schema( description = "租户用户（多租户）视图对象")
public class TenantUserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    private Long id;
    @Schema(description = "所属租户ID")
    private Long tenantId;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "真实姓名")
    private String realName;
    @Schema(description = "头像URL")
    private String avatarUrl;
    @Schema(description = "用户角色")
    private ${fieldConfig.fieldType} role;
    @Schema(description = "部门")
    private String department;
    @Schema(description = "是否激活")
    private Integer isActive;
    @Schema(description = "最后登录时间")
    private ${fieldConfig.fieldType} lastLoginAt;
    @Schema(description = "创建时间")
    private ${fieldConfig.fieldType} createdAt;
    @Schema(description = "更新时间")
    private ${fieldConfig.fieldType} updatedAt;
}
