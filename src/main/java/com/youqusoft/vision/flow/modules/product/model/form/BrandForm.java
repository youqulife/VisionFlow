package com.youqusoft.vision.flow.modules.product.model.form;

import java.io.Serial;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

/**
 * 品牌表单对象
 *
 * @author youqusoft
 * @since 2025-10-04 20:49
 */
@Getter
@Setter
@Schema(description = "品牌表单对象")
public class BrandForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "品牌ID")
    private Long id;

    @Schema(description = "品牌名称")
    @NotBlank(message = "品牌名称不能为空")
    @Size(max=100, message="品牌名称长度不能超过100个字符")
    private String name;

    @Schema(description = "英文名")
    @Size(max=100, message="英文名长度不能超过100个字符")
    private String englishName;

    @Schema(description = "原产国")
    @Size(max=50, message="原产国长度不能超过50个字符")
    private String originCountry;

    @Schema(description = "品牌类别")
    @Size(max=20, message="品牌类别长度不能超过20个字符")
    private String category;

    @Schema(description = "价格定位")
    @Size(max=20, message="价格定位长度不能超过20个字符")
    private String priceLevel;

    @Schema(description = "品牌Logo URL")
    @Size(max=255, message="品牌Logo URL长度不能超过255个字符")
    private String logoUrl;

    @Schema(description = "是否热门品牌")
    private Integer isPopular;

    @Schema(description = "是否启用")
    private Integer isActive;

    @Schema(description = "品牌描述")
    @Size(max=65535, message="品牌描述长度不能超过65535个字符")
    private String description;

    @Schema(description = "状态：0-禁用，1-启用")
    @NotNull(message = "状态：0-禁用，1-启用不能为空")
    private Integer status;

    @Schema(description = "排序值，越大越靠前")
    @NotNull(message = "排序值，越大越靠前不能为空")
    private Integer sortOrder;
}