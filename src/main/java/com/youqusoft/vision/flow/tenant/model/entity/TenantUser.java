package com.youqusoft.vision.flow.tenant.model.entity;

import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youqusoft.vision.flow.common.base.BaseEntity;

/**
 * 租户用户（多租户）实体对象
 *
 * @author youqulife
 * @since 2025-09-27 08:53
 */
@Getter
@Setter
@TableName("saas_tenant_user")
public class TenantUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 所属租户ID
     */
    private Long tenantId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 头像URL
     */
    private String avatarUrl;
    /**
     * 用户角色
     */
    private ${fieldConfig.fieldType} role;
    /**
     * 部门
     */
    private String department;
    /**
     * 是否激活
     */
    private Integer isActive;
    /**
     * 最后登录时间
     */
    private ${fieldConfig.fieldType} lastLoginAt;
    /**
     * 创建时间
     */
    private ${fieldConfig.fieldType} createdAt;
    /**
     * 更新时间
     */
    private ${fieldConfig.fieldType} updatedAt;
}
