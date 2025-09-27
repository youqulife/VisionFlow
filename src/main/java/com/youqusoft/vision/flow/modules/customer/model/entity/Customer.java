package com.youqusoft.vision.flow.modules.customer.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.youqusoft.vision.flow.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 顾客信息实体类
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("customer")
public class Customer extends BaseEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 顾客姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 性别：male-男, female-女, unknown-未知
     */
    private String gender;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 顾客来源
     */
    private String source;

    /**
     * 顾客标签
     */
    private String tags;

    /**
     * 会员等级
     */
    private String memberLevel;

    /**
     * 账户余额
     */
    private Double balance;

    /**
     * 备注信息
     */
    private String notes;

    /**
     * 软删除标记：0-未删除, 1-已删除
     */
    @TableLogic
    private Integer isDeleted;
}