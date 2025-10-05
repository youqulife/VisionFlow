package com.youqusoft.vision.flow.modules.product.model.form;

import java.io.Serial;
import java.io.Serializable;

import com.youqusoft.vision.flow.modules.product.model.entity.ProductSku;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.*;

/**
 * 商品信息表单对象
 *
 * @author youqusoft
 * @since 2025-09-27 11:59
 */
@Getter
@Setter
@Schema(description = "商品信息表单对象")
public class ProductForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "商品编码：唯一库存单位编码")
    @Size(max=50, message="商品编码：唯一库存单位编码长度不能超过50个字符")
    private String productCode;

    @Schema(description = "商品名称：展示给客户的名称")
    @NotBlank(message = "商品名称：展示给客户的名称不能为空")
    @Size(max=200, message="商品名称：展示给客户的名称长度不能超过200个字符")
    private String name;

    @Schema(description = "品牌名称：如'依视路', '蔡司', '雷朋'等")
    @NotNull(message = "品牌名称：如'依视路', '蔡司', '雷朋'等不能为空")
    private Long brandId;

    @Schema(description = "商品分类ID：关联categories表")
    @NotNull(message = "商品分类ID：关联categories表不能为空")
    private Long categoryId;

    @Schema(description = "型号：商品具体型号")
    @Size(max=100, message="型号：商品具体型号长度不能超过100个字符")
    private String model;

    @Schema(description = "商品属性")
    private ProductAttribute attribute;

    @Schema(description = "商品SKU表单对象")
    @NotEmpty(message = "商品SKU表单对象不能为空")
    private List<ProductSkuForm> skus;

    @Schema(description = "是否上架：0-下架, 1-上架")
    @NotNull(message = "是否上架：0-下架, 1-上架不能为空")
    private Integer isActive;

}