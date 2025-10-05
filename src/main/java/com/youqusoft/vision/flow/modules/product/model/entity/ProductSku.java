package com.youqusoft.vision.flow.modules.product.model.entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youqusoft.vision.flow.common.base.BaseEntity;

/**
 * 商品SKU实体对象
 *
 * @author youqusoft
 * @since 2025-10-05 09:55
 */
@Getter
@Setter
@TableName("product_sku")
public class ProductSku extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * SKU编码
     */
    private String skuCode;
    /**
     * SKU属性
     */
    private String skuAttributes;
    /**
     * 库存数量
     */
    private Integer stockQuantity;
    /**
     * 进货价
     */
    private BigDecimal purchasePrice;
    /**
     * 销售价
     */
    private BigDecimal salePrice;
    /**
     * 库存预警
     */
    private Integer minStockAlert;
    private Integer isActive;
    private Integer isDeleted;
}