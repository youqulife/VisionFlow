package com.youqusoft.vision.flow.modules.customer.model.entity;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youqusoft.vision.flow.common.base.BaseEntity;

/**
 * 顾客信息实体对象
 *
 * @author youqusoft
 * @since 2025-09-27 15:49
 */
@Getter
@Setter
@TableName("customer")
public class Customer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 顾客姓名
     */
    private String name;
    /**
     * 手机号码（可作为唯一标识）
     */
    private String phone;
    /**
     * 性别：male-男, female-女, unknown-未知
     */
    private String gender;
    /**
     * 生日（用于客户关怀和营销）
     */
    private LocalDate birthday;
    /**
     * 顾客来源：如"大众点评"、"朋友推荐"、"线下自然流量"等
     */
    private String source;
    /**
     * 顾客标签：JSON数组格式，如["高价值客户", "儿童", "价格敏感"]
     */
    private String tags;
    /**
     * 会员等级：如"普通","银卡","金卡","钻石"
     */
    private String memberLevel;
    /**
     * 账户余额（储值金额）
     */
    private BigDecimal balance;
    /**
     * 备注信息：顾客偏好、特殊需求、病史等
     */
    private String notes;
    /**
     * 软删除标记：0-未删除, 1-已删除
     */
    private Integer isDeleted;
}
