package com.youqusoft.vision.flow.modules.order.model.form;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.*;

/**
 * 订单表单对象
 *
 * @author youqusoft
 * @since 2025-10-06 21:13
 */
@Getter
@Setter
@Schema(description = "订单表单对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdersForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "订单ID")
    private Long id;

    @Schema(description = "顾客ID")
    @NotNull(message = "顾客ID不能为空")
    private Long customerId;

    @Schema(description = "关联的验光记录ID")
    private Long eyeExamId;

    @Schema(description = "订单号")
    @NotBlank(message = "订单号不能为空")
    @Size(max=50, message="订单号长度不能超过50个字符")
    private String orderNumber;

    @Size(max=12, message="长度不能超过12个字符")
    private String orderType;

    @Schema(description = "订单小计")
    private BigDecimal subTotal;

    @Schema(description = "优惠金额")
    private BigDecimal discountAmount;

    @Schema(description = "订单总金额")
    private BigDecimal totalAmount;

    @Schema(description = "实付金额")
    private BigDecimal actualAmount;

    @Size(max=10, message="长度不能超过10个字符")
    private String status;

    @Schema(description = "支付方式")
    @Size(max=6, message="支付方式长度不能超过6个字符")
    private String paymentMethod;

    @Schema(description = "处方数据快照")
    private String prescriptionData;

    @Schema(description = "订单备注")
    @Size(max=65535, message="订单备注长度不能超过65535个字符")
    private String notes;

    @Schema(description = "订单明细")
    private List<OrderItemForm> items;
}
