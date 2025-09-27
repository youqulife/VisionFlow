package com.youqusoft.vision.flow.modules.order.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 订单明细表单对象
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Data
@Schema(description = "订单明细表单对象")
public class OrderItemForm {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 关联的订单ID
     */
    @Schema(description = "关联的订单ID")
    private Long orderId;

    /**
     * 关联的商品ID
     */
    @Schema(description = "关联的商品ID")
    private Long productId;

    /**
     * 商品分类ID快照
     */
    @Schema(description = "商品分类ID快照")
    private Long categoryId;

    /**
     * 商品类型
     */
    @Schema(description = "商品类型")
    private String productType;

    /**
     * 商品名称快照
     */
    @Schema(description = "商品名称快照")
    private String productName;

    /**
     * 商品单价快照
     */
    @Schema(description = "商品单价快照")
    private Double unitPrice;

    /**
     * 购买数量
     */
    @Schema(description = "购买数量")
    private Integer quantity;

    /**
     * 商品总价
     */
    @Schema(description = "商品总价")
    private Double itemTotal;
}