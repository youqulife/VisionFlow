package com.youqusoft.vision.flow.modules.product.model.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * 品牌视图对象
 *
 * @author youqusoft
 * @since 2025-10-04 20:49
 */
@Getter
@Setter
@Schema( description = "品牌视图对象")
public class BrandVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "品牌ID")
    private Long id;
    
    @Schema(description = "品牌名称")
    private String name;
    
    @Schema(description = "英文名")
    private String englishName;
    
    @Schema(description = "原产国")
    private String originCountry;
    
    @Schema(description = "品牌类别")
    private String category;
    
    @Schema(description = "价格定位")
    private String priceLevel;
    
    @Schema(description = "品牌Logo URL")
    private String logoUrl;
    
    @Schema(description = "是否热门品牌")
    private Integer isPopular;
    
    @Schema(description = "是否启用")
    private Integer isActive;
    
    @Schema(description = "品牌描述")
    private String description;
    
    @Schema(description = "状态：0-禁用，1-启用")
    private Integer status;
    
    @Schema(description = "排序值，越大越靠前")
    private Integer sortOrder;
    
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime createTime;
    
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime updateTime;
}