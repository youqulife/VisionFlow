package com.youqusoft.vision.flow.modules.product.model.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.youqusoft.vision.flow.modules.product.model.form.ProductSKUAttribute;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * 商品SKU视图对象
 *
 * @author youqusoft
 * @since 2025-10-05 09:55
 */
@Getter
@Setter
@Schema( description = "商品SKU视图对象")
public class ProductSkuVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "SKU ID")
    private Long id;
    @Schema(description = "租户ID")
    private Long tenantId;
    @Schema(description = "商品ID")
    private Long productId;
    @Schema(description = "SKU编码")
    private String skuCode;
    @Schema(description = "SKU属性")
    private ProductSKUAttribute skuAttributes;
    @Schema(description = "库存数量")
    private Integer stockQuantity;
    @Schema(description = "进货价")
    private BigDecimal purchasePrice;
    @Schema(description = "销售价")
    private BigDecimal salePrice;
    @Schema(description = "库存预警")
    private Integer minStockAlert;
    private Integer isActive;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
