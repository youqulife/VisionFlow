package com.youqusoft.vision.flow.modules.order.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youqusoft.vision.flow.common.base.BaseEntity;

/**
 * 订单实体对象
 *
 * @author youqusoft
 * @since 2025-10-06 21:13
 */
@Getter
@Setter
@TableName("orders")
public class Orders extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 顾客ID
     */
    private Long customerId;
    /**
     * 关联的验光记录ID
     */
    private Long eyeExamId;
    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 订单类型
     */
    private String orderType;
    /**
     * 订单小计金额（不包含优惠金额）
     */
    private BigDecimal subTotal;
    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    /**
     * 实付金额
     */
    private BigDecimal actualAmount;
    /**
     * 订单状态
     */
    private String status;
    /**
     * 支付方式
     */
    private String paymentMethod;
    /**
     * 处方数据快照
     */
    private String prescriptionData;
    /**
     * 订单备注
     */
    private String notes;
    private Integer isDeleted;
}
