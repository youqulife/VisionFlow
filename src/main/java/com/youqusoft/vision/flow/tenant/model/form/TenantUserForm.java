package com.youqusoft.vision.flow.tenant.model.form;

import java.io.Serial;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

/**
 * 租户用户（多租户）表单对象
 *
 * @author youqulife
 * @since 2025-09-27 08:53
 */
@Getter
@Setter
@Schema(description = "租户用户（多租户）表单对象")
public class TenantUserForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long id;

    @Schema(description = "所属租户ID")
    @NotNull(message = "所属租户ID不能为空")
    private Long tenantId;

    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    @Size(max=50, message="用户名长度不能超过50个字符")
    private String username;

    @Schema(description = "邮箱")
    @Size(max=100, message="邮箱长度不能超过100个字符")
    private String email;

    @Schema(description = "手机号")
    @Size(max=20, message="手机号长度不能超过20个字符")
    private String phone;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    @Size(max=255, message="密码长度不能超过255个字符")
    private String password;

    @Schema(description = "真实姓名")
    @NotBlank(message = "真实姓名不能为空")
    @Size(max=50, message="真实姓名长度不能超过50个字符")
    private String realName;

    @Schema(description = "头像URL")
    @Size(max=255, message="头像URL长度不能超过255个字符")
    private String avatarUrl;

    @Schema(description = "用户角色")
    @NotNull(message = "用户角色不能为空")
    @Size(max=12, message="用户角色长度不能超过12个字符")
    private ${fieldConfig.fieldType} role;

    @Schema(description = "部门")
    @Size(max=50, message="部门长度不能超过50个字符")
    private String department;

    @Schema(description = "是否激活")
    @NotNull(message = "是否激活不能为空")
    private Integer isActive;


}
