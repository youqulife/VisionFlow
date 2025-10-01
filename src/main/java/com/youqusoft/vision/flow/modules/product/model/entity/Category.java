package com.youqusoft.vision.flow.modules.product.model.entity;

import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youqusoft.vision.flow.common.base.BaseEntity;

import java.util.Date;

/**
 * 商品分类实体对象
 *
 * @author youqusoft
 * @since 2025-09-27 12:28
 */
@Getter
@Setter
@TableName("category")
public class Category extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 父级分类ID：用于支持多级分类，NULL表示顶级分类
     */
    private Long parentId;
    /**
     * 排序号：数字越小排序越前
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
    private Integer isDeleted;
}
