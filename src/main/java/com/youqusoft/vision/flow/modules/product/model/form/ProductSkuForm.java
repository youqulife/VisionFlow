package com.youqusoft.vision.flow.modules.product.model.form;

import java.io.Serial;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import jakarta.validation.constraints.*;

/**
 * 商品SKU表单对象
 *
 * @author youqusoft
 * @since 2025-10-05 09:55
 */
@Getter
@Setter
@Schema(description = "商品SKU表单对象")
public class ProductSkuForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "SKU ID")
    private Long id;

    @Schema(description = "SKU编码")
    @NotBlank(message = "SKU编码不能为空")
    @Size(max=50, message="SKU编码长度不能超过50个字符")
    private String skuCode;

    @Schema(description = "SKU属性")
    @NotBlank(message = "SKU属性不能为空")
    private ProductSKUAttribute skuAttributes;

    @Schema(description = "库存数量")
    private Integer stockQuantity;

    @Schema(description = "进货价")
    @NotNull(message = "进货价不能为空")
    private BigDecimal purchasePrice;

    @Schema(description = "销售价")
    @NotNull(message = "销售价不能为空")
    private BigDecimal salePrice;

    @Schema(description = "库存预警")
    private Integer minStockAlert;

    private Integer isActive;

}
