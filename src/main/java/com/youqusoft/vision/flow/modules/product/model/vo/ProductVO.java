package com.youqusoft.vision.flow.modules.product.model.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youqusoft.vision.flow.modules.product.model.form.ProductAttribute;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品信息视图对象
 *
 * @author youqusoft
 * @since 2025-09-27 11:59
 */
@Getter
@Setter
@Schema( description = "商品信息视图对象")
public class ProductVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;
    @Schema(description = "租户ID")
    private Long tenantId;
    @Schema(description = "商品SKU编码：唯一库存单位编码")
    private String productCode;
    @Schema(description = "商品名称：展示给客户的名称")
    private String name;
    @Schema(description = "商品分类ID：关联categories表")
    private Long categoryId;
    @Schema(description = "商品分类名称")
    private String categoryName;
    @Schema(description = "品牌名称：如'依视路', '蔡司', '雷朋'等")
    private Long brandId;
    @Schema(description = "品牌名称：如'依视路', '蔡司', '雷朋'等")
    private String brandName;
    @Schema(description = "型号：商品具体型号")
    private String model;
    @Schema(description = "商品属性（JSON格式存储）")
    @JsonIgnore
    private String attributes;
    @Schema(description = "商品属性（JSON格式存储）")
    private ProductAttribute attribute;
    private List<ProductSkuVO> skus;
    private Integer isActive;
    @Schema(description = "软删除标记：0-未删除, 1-已删除")
    private Integer isDeleted;
    @Schema(description = "记录创建时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime createTime;
    @Schema(description = "记录最后更新时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime updateTime;
}