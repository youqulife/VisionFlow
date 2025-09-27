package com.youqusoft.vision.flow.tenant.model.entity;

import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youqusoft.vision.flow.common.base.BaseEntity;

import java.time.LocalDate;
import java.util.Date;

/**
 * 租户信息实体对象
 *
 * @author youqulife
 * @since 2025-09-26 22:24
 */
@Getter
@Setter
@TableName("saas_tenant")
public class Tenant extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租户编码：唯一标识，可用于子域名
     */
    private String tenantCode;
    /**
     * 租户名称（店铺名称）
     */
    private String name;
    /**
     * 联系人姓名
     */
    private String contactPerson;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 联系邮箱
     */
    private String contactEmail;
    /**
     * 店铺地址
     */
    private String address;
    /**
     * 行业类型：optical-眼镜店, hair-美发店等
     */
    private String industryType;
    /**
     * 订阅计划
     */
    private String subscriptionPlan;
    /**
     * 订阅状态
     */
    private String subscriptionStatus;
    /**
     * 订阅到期日期
     */
    private LocalDate expiresAt;
    /**
     * 最大用户数
     */
    private Integer maxUsers;
    /**
     * 是否激活
     */
    private Integer isActive;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;
}
