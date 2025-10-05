package com.youqusoft.vision.flow.modules.product.model.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 商品分类视图对象
 *
 * @author youqusoft
 * @since 2025-09-27 12:28
 */
@Getter
@Setter
@Schema( description = "商品分类视图对象")
public class CategoryVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;
    @Schema(description = "分类名称")
    private String name;
    @Schema(description = "父级分类ID：用于支持多级分类，NULL表示顶级分类")
    private Long parentId;
    @Schema(description = "排序号：数字越小排序越前")
    private Integer sortOrder;
    @Schema(description = "分类描述")
    private String description;
    @Schema(description = "是否启用：0-禁用, 1-启用")
    private Integer isActive;
    @Schema(description = "软删除标记：0-未删除, 1-已删除")
    private Integer isDeleted;
    @Schema(description = "记录创建时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime createTime;
    @Schema(description = "记录最后更新时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime updateTime;
    
    @Schema(description = "子分类列表")
    private List<CategoryVO> children;
}