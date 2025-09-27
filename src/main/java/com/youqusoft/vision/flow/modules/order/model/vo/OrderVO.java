package com.youqusoft.vision.flow.modules.order.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 订单主表VO
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Data
@Schema(description = "订单主表")
public class OrderVO {

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

    /**
     * 订单创建时间
     */
    @Schema(description = "订单创建时间")
    private LocalDateTime createdAt;

    /**
     * 订单最后更新时间
     */
    @Schema(description = "订单最后更新时间")
    private LocalDateTime updatedAt;
}