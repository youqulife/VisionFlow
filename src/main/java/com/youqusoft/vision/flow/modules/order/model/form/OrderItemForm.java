package com.youqusoft.vision.flow.modules.order.model.form;

import java.io.Serial;
import java.io.Serializable;

import com.youqusoft.vision.flow.modules.product.model.form.ProductSKUAttribute;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import jakarta.validation.constraints.*;

/**
 * 订单明细表单对象
 *
 * @author youqusoft
 * @since 2025-10-06 21:14
 */
@Getter
@Setter
@Schema(description = "订单明细表单对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单明细ID
     */
    @Schema(description = "订单明细ID")
    private Long id;

    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    private Long productId;
    /**
     * 商品SKU ID
     */
    @Schema(description = "商品SKU ID")
    private Long productSkuId;
    /**
     * 商品名称快照
     */
    @Schema(description = "商品名称快照")
    private String productName;
    /**
     * 品牌ID
     */
    @Schema(description = "品牌ID")
    private Long brandId;
    /**
     * 品牌名称快照
     */
    @Schema(description = "品牌名称快照")
    private String brandName;
    /**
     * 分类ID
     */
    @Schema(description = "分类ID")
    private Long categoryId;
    /**
     * 分类名称快照
     */
    @Schema(description = "分类名称快照")
    private String categoryName;
    /**
     * SKU编码快照
     */
    @Schema(description = "SKU编码快照")
    private String skuCode;
    /**
     * SKU属性快照
     */
    @Schema(description = "SKU属性快照")
    private ProductSKUAttribute skuAttributes;
    /**
     * 销售单价快照
     */
    @Schema(description = "销售单价快照")
    private BigDecimal unitPrice;
    /**
     * 成本单价快照
     */
    @Schema(description = "成本单价快照")
    private BigDecimal costPrice;
    /**
     * 购买数量
     */
    @Schema(description = "购买数量")
    private Integer quantity;
    /**
     * 商品总价
     */
    @Schema(description = "商品总价")
    private BigDecimal subTotal;

    @Schema(description = "折扣率")
    private BigDecimal discountRate;

    @Schema(description = "折扣金额")
    private BigDecimal discountAmount;

    @Schema(description = "商品总金额")
    private BigDecimal itemTotal;

    /**
     * 适用眼睛
     */
    @Schema(description = "适用眼睛")
    private String eyeSide;
    /**
     * 镜片处方数据
     */
    @Schema(description = "镜片处方数据")
    private String lensPrescription;
}