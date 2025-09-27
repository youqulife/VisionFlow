package com.youqusoft.vision.flow.modules.product.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品表单对象
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Data
@Schema(description = "商品表单对象")
public class ProductForm {

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
     * 商品SKU编码
     */
    @Schema(description = "商品SKU编码")
    private String sku;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String name;

    /**
     * 商品分类ID
     */
    @Schema(description = "商品分类ID")
    private Long categoryId;

    /**
     * 品牌名称
     */
    @Schema(description = "品牌名称")
    private String brand;

    /**
     * 型号
     */
    @Schema(description = "型号")
    private String model;

    /**
     * 折射率
     */
    @Schema(description = "折射率")
    private Double refractiveIndex;

    /**
     * 镜片功能
     */
    @Schema(description = "镜片功能")
    private String lensFunction;

    /**
     * 进货价格
     */
    @Schema(description = "进货价格")
    private Double purchasePrice;

    /**
     * 销售价格
     */
    @Schema(description = "销售价格")
    private Double salePrice;

    /**
     * 库存数量
     */
    @Schema(description = "库存数量")
    private Integer stockQuantity;

    /**
     * 最低库存预警线
     */
    @Schema(description = "最低库存预警线")
    private Integer minStockAlert;

    /**
     * 是否上架：0-下架, 1-上架
     */
    @Schema(description = "是否上架")
    private Integer isActive;
}