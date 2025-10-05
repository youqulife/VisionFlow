package com.youqusoft.vision.flow.modules.product.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("product")
public class Product extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 商品SKU编码：唯一库存单位编码
     */
    private String productCode;
    /**
     * 商品名称：展示给客户的名称
     */
    private String name;
    /**
     * 品牌名称：如"依视路", "蔡司", "雷朋"等
     */
    private Long brandId;
    /**
     * 商品分类ID：关联categories表
     */
    private Long categoryId;
    /**
     * 型号：商品具体型号
     */
    private String model;

    /**
     * 商品属性（JSON格式存储）
     */
    @TableField("attributes")
    private String attribute;

    /**
     * 是否上架：0-下架, 1-上架
     */
    private Integer isActive;
    /**
     * 软删除标记：0-未删除, 1-已删除
     */
    private Integer isDeleted;
}