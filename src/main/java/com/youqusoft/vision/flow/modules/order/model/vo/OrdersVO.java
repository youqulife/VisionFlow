package com.youqusoft.vision.flow.modules.order.model.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单视图对象
 *
 * @author youqusoft
 * @since 2025-10-06 21:13
 */
@Getter
@Setter
@Schema( description = "订单视图对象")
public class OrdersVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "订单ID")
    private Long id;
    @Schema(description = "租户ID")
    private Long tenantId;
    @Schema(description = "顾客ID")
    private Long customerId;
    @Schema(description = "顾客名称")
    private String customerName;
    @Schema(description = "顾客手机")
    private String customerPhone;
    @Schema(description = "关联的验光记录ID")
    private Long eyeExamId;
    @Schema(description = "订单号")
    private String orderNumber;
    @Schema(description = "订单类型")
    private String orderType;
    @Schema(description = "订单小计")
    private BigDecimal subTotal;
    @Schema(description = "优惠金额")
    private BigDecimal discountAmount;
    @Schema(description = "订单总金额")
    private BigDecimal totalAmount;
    @Schema(description = "实付金额")
    private BigDecimal actualAmount;
    private String status;
    @Schema(description = "支付方式")
    private String paymentMethod;
    @Schema(description = "处方数据快照")
    private String prescriptionData;
    @Schema(description = "订单备注")
    private String notes;
    private Integer isDeleted;
    private List<OrderItemVO> items;
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime createTime;
    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime updateTime;
}
