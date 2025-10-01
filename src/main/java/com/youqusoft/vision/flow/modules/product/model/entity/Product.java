package com.youqusoft.vision.flow.modules.product.model.entity;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youqusoft.vision.flow.common.base.BaseEntity;

/**
 * 商品信息实体对象
 *
 * @author youqusoft
 * @since 2025-09-27 11:59
 */
@Getter
@Setter
@TableName("products")
public class Product extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 商品名称：展示给客户的名称
     */
    private String name;
    /**
     * 商品SKU编码：唯一库存单位编码
     */
    private String sku;
    /**
     * 商品分类ID：关联categories表
     */
    private Long categoryId;
    /**
     * 品牌名称：如"依视路", "蔡司", "雷朋"等
     */
    private String brand;
    /**
     * 型号：商品具体型号
     */
    private String model;
    /**
     * 折射率：如1.56, 1.60, 1.67, 1.74等
     */
    private BigDecimal refractiveIndex;
    /**
     * 镜片功能：如"防蓝光", "变色", "渐进多焦点"等
     */
    private String lensFunction;
    /**
     * 进货价格
     */
    private BigDecimal purchasePrice;
    /**
     * 销售价格
     */
    private BigDecimal salePrice;
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
    private Integer isDeleted;
}
