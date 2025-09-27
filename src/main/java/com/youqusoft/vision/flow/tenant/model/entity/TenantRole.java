package com.youqusoft.vision.flow.tenant.model.entity;

import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youqusoft.vision.flow.common.base.BaseEntity;

/**
 * 租户角色实体对象
 *
 * @author youqulife
 * @since 2025-09-27 08:47
 */
@Getter
@Setter
@TableName("saas_tenant_role")
public class TenantRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 所属租户ID
     */
    private Long tenantId;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 是否系统角色
     */
    private Integer isSystem;
    /**
     * 是否激活
     */
    private Integer isActive;
    /**
     * 创建时间
     */
    private ${fieldConfig.fieldType} createdAt;
    /**
     * 更新时间
     */
    private ${fieldConfig.fieldType} updatedAt;
}
