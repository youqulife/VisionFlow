package com.youqusoft.vision.flow.modules.product.model.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

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
    @Schema(description = "商品名称：展示给客户的名称")
    private String name;
    @Schema(description = "商品SKU编码：唯一库存单位编码")
    private String sku;
    @Schema(description = "商品分类ID：关联categories表")
    private Long categoryId;
    @Schema(description = "品牌名称：如'依视路', '蔡司', '雷朋'等")
    private String brand;
    @Schema(description = "型号：商品具体型号")
    private String model;
    @Schema(description = "折射率：如1.56, 1.60, 1.67, 1.74等")
    private BigDecimal refractiveIndex;
    @Schema(description = "镜片功能：如'防蓝光', '变色', '渐进多焦点'等")
    private String lensFunction;
    @Schema(description = "进货价格")
    private BigDecimal purchasePrice;
    @Schema(description = "销售价格")
    private BigDecimal salePrice;
    @Schema(description = "库存数量")
    private Integer stockQuantity;
    @Schema(description = "最低库存预警线")
    private Integer minStockAlert;
    @Schema(description = "是否上架：0-下架, 1-上架")
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
