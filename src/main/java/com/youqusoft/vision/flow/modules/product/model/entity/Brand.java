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
     * 品牌Logo URL
     */
    private String logoUrl;
    /**
     * 品牌描述
     */
    private String description;
    /**
     * 官方网站
     */
    private String website;
    /**
     * 状态：0-禁用，1-启用
     */
    private Integer status;
    /**
     * 排序值，越大越靠前
     */
    private Integer sortOrder;
}
