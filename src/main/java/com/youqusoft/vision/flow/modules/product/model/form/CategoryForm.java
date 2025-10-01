package com.youqusoft.vision.flow.modules.product.model.form;

import java.io.Serial;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

/**
 * 商品分类表单对象
 *
 * @author youqusoft
 * @since 2025-09-27 12:28
 */
@Getter
@Setter
@Schema(description = "商品分类表单对象")
public class CategoryForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "分类名称")
    @NotBlank(message = "分类名称不能为空")
    @Size(max=50, message="分类名称长度不能超过50个字符")
    private String name;

    @Schema(description = "父级分类ID：用于支持多级分类，NULL表示顶级分类")
    private Long parentId;

    @Schema(description = "排序号：数字越小排序越前")
    @NotNull(message = "排序号：数字越小排序越前不能为空")
    private Integer sortOrder;

    @Schema(description = "分类描述")
    @Size(max=512, message="分类描述长度不能超过512个字符")
    private String description;

    @Schema(description = "是否启用：0-禁用, 1-启用")
    @NotNull(message = "是否启用：0-禁用, 1-启用不能为空")
    private Integer isActive;

    @Schema(description = "软删除标记：0-未删除, 1-已删除")
    @NotNull(message = "软删除标记：0-未删除, 1-已删除不能为空")
    private Integer isDeleted;


}
