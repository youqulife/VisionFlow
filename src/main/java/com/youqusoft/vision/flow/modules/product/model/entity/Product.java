package com.youqusoft.vision.flow.modules.product.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.youqusoft.vision.flow.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品信息实体类
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("products")
public class Product extends BaseEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 商品SKU编码
     */
    private String sku;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品分类ID
     */
    private Long categoryId;

    /**
     * 品牌名称
     */
    private String brand;

    /**
     * 型号
     */
    private String model;

    /**
     * 折射率
     */
    private Double refractiveIndex;

    /**
     * 镜片功能
     */
    private String lensFunction;

    /**
     * 进货价格
     */
    private Double purchasePrice;

    /**
     * 销售价格
     */
    private Double salePrice;

    /**
     * 库存数量
     */
    private Integer stockQuantity;

    /**
     * 最低库存预警线
     */
    private Integer minStockAlert;

    /**
     * 是否上架：0-下架, 1-上架
     */
    private Integer isActive;

    /**
     * 软删除标记：0-未删除, 1-已删除
     */
    @TableLogic
    private Integer isDeleted;
}