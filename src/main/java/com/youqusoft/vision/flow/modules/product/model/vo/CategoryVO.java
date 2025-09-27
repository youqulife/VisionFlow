package com.youqusoft.vision.flow.modules.product.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品分类VO
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Data
@Schema(description = "商品分类")
public class CategoryVO {

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
     * 分类名称
     */
    @Schema(description = "分类名称")
    private String name;

    /**
     * 父级分类ID
     */
    @Schema(description = "父级分类ID")
    private Long parentId;

    /**
     * 排序号
     */
    @Schema(description = "排序号")
    private Integer sortOrder;

    /**
     * 分类描述
     */
    @Schema(description = "分类描述")
    private String description;

    /**
     * 是否启用：0-禁用, 1-启用
     */
    @Schema(description = "是否启用")
    private Integer isActive;

    /**
     * 记录创建时间
     */
    @Schema(description = "记录创建时间")
    private LocalDateTime createdAt;

    /**
     * 记录最后更新时间
     */
    @Schema(description = "记录最后更新时间")
    private LocalDateTime updatedAt;
}