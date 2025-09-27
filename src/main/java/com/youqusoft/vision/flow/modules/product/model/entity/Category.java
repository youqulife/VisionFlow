package com.youqusoft.vision.flow.modules.product.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.youqusoft.vision.flow.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品分类实体类
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("category")
public class Category extends BaseEntity {

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
     * 分类名称
     */
    private String name;

    /**
     * 父级分类ID
     */
    private Long parentId;

    /**
     * 排序号
     */
    private Integer sortOrder;

    /**
     * 分类描述
     */
    private String description;

    /**
     * 是否启用：0-禁用, 1-启用
     */
    private Integer isActive;

    /**
     * 软删除标记：0-未删除, 1-已删除
     */
    @TableLogic
    private Integer isDeleted;
}