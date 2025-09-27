package com.youqusoft.vision.flow.modules.order.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 订单表单对象
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Data
@Schema(description = "订单表单对象")
public class OrderForm {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 租户ID
     */
    @Schema(description = "租户ID")
    private Long tenantId;

    /**
     * 订单编号
     */
    @Schema(description = "订单编号")
    private String orderNumber;

    /**
     * 关联的顾客ID
     */
    @Schema(description = "关联的顾客ID")
    private Long customerId;

    /**
     * 关联的验光记录ID
     */
    @Schema(description = "关联的验光记录ID")
    private Long eyeExamId;

    /**
     * 订单总金额
     */
    @Schema(description = "订单总金额")
    private Double totalAmount;

    /**
     * 优惠金额
     */
    @Schema(description = "优惠金额")
    private Double discountAmount;

    /**
     * 实付金额
     */
    @Schema(description = "实付金额")
    private Double finalAmount;

    /**
     * 订单状态
     */
    @Schema(description = "订单状态")
    private String status;

    /**
     * 支付方式
     */
    @Schema(description = "支付方式")
    private String paymentMethod;

    /**
     * 订单备注
     */
    @Schema(description = "订单备注")
    private String notes;
}