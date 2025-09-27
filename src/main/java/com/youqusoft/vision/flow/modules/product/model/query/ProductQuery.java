package com.youqusoft.vision.flow.modules.product.model.query;

import com.youqusoft.vision.flow.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品查询对象
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Data
@Schema(description = "商品查询对象")
public class ProductQuery extends BasePageQuery {

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
     * 是否上架
     */
    @Schema(description = "是否上架")
    private Integer isActive;
}