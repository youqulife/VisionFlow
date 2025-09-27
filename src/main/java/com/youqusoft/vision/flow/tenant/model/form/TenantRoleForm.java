package com.youqusoft.vision.flow.tenant.model.form;

import java.io.Serial;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

/**
 * 租户角色表单对象
 *
 * @author youqulife
 * @since 2025-09-27 08:47
 */
@Getter
@Setter
@Schema(description = "租户角色表单对象")
public class TenantRoleForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "角色ID")
    private Long id;

    @Schema(description = "所属租户ID")
    @NotNull(message = "所属租户ID不能为空")
    private Long tenantId;

    @Schema(description = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    @Size(max=64, message="角色名称长度不能超过64个字符")
    private String name;

    @Schema(description = "角色编码")
    @NotBlank(message = "角色编码不能为空")
    @Size(max=32, message="角色编码长度不能超过32个字符")
    private String code;

    @Schema(description = "角色描述")
    @Size(max=255, message="角色描述长度不能超过255个字符")
    private String description;

    @Schema(description = "是否系统角色")
    @NotNull(message = "是否系统角色不能为空")
    private Integer isSystem;

    @Schema(description = "是否激活")
    @NotNull(message = "是否激活不能为空")
    private Integer isActive;


}
