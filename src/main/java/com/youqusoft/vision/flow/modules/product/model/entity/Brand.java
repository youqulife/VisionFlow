package com.youqusoft.vision.flow.modules.product.model.entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youqusoft.vision.flow.common.base.BaseEntity;

/**
 * 品牌实体对象
 *
 * @author youqusoft
 * @since 2025-10-04 20:49
 */
@Getter
@Setter
@TableName("brand")
public class Brand extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌名称
     */
    private String name;
    
    /**
     * 英文名
     */
    private String englishName;
    
    /**
     * 原产国
     */
    private String originCountry;
    
    /**
     * 品牌类别
     */
    private String category;
    
    /**
     * 价格定位
     */
    private String priceLevel;
    
    /**
     * 品牌描述
     */
    private String description;
    
    /**
     * 品牌Logo
     */
    private String logoUrl;
    
    /**
     * 是否热门品牌
     */
    private Integer isPopular;
    
    /**
     * 是否启用
     */
    private Integer isActive;
    
    /**
     * 状态：0-禁用，1-启用
     */
    private Integer status;
    
    /**
     * 排序值，越大越靠前
     */
    private Integer sortOrder;
}