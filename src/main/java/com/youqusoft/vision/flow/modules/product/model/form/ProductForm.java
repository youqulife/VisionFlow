package com.youqusoft.vision.flow.modules.product.model.form;

import java.io.Serial;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import jakarta.validation.constraints.*;

/**
 * 商品信息表单对象
 *
 * @author youqusoft
 * @since 2025-09-27 11:59
 */
@Getter
@Setter
@Schema(description = "商品信息表单对象")
public class ProductForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "租户ID")
    @NotNull(message = "租户ID不能为空")
    private Long tenantId;

    @Schema(description = "商品名称：展示给客户的名称")
    @NotBlank(message = "商品名称：展示给客户的名称不能为空")
    @Size(max=200, message="商品名称：展示给客户的名称长度不能超过200个字符")
    private String name;

    @Schema(description = "商品SKU编码：唯一库存单位编码")
    @Size(max=50, message="商品SKU编码：唯一库存单位编码长度不能超过50个字符")
    private String sku;

    @Schema(description = "商品分类ID：关联categories表")
    @NotNull(message = "商品分类ID：关联categories表不能为空")
    private Long categoryId;

    @Schema(description = "品牌名称：如'依视路', '蔡司', '雷朋'等")
    @Size(max=100, message="品牌名称：如'依视路', '蔡司', '雷朋'等长度不能超过100个字符")
    private String brand;

    @Schema(description = "型号：商品具体型号")
    @Size(max=100, message="型号：商品具体型号长度不能超过100个字符")
    private String model;

    @Schema(description = "折射率：如1.56, 1.60, 1.67, 1.74等")
    private BigDecimal refractiveIndex;

    @Schema(description = "镜片功能：如'防蓝光', '变色', '渐进多焦点'等")
    @Size(max=100, message="镜片功能：如'防蓝光', '变色', '渐进多焦点'等长度不能超过100个字符")
    private String lensFunction;

    @Schema(description = "进货价格")
    @NotNull(message = "进货价格不能为空")
    private BigDecimal purchasePrice;

    @Schema(description = "销售价格")
    @NotNull(message = "销售价格不能为空")
    private BigDecimal salePrice;

    @Schema(description = "库存数量")
    @NotNull(message = "库存数量不能为空")
    private Integer stockQuantity;

    @Schema(description = "最低库存预警线")
    @NotNull(message = "最低库存预警线不能为空")
    private Integer minStockAlert;

    @Schema(description = "是否上架：0-下架, 1-上架")
    @NotNull(message = "是否上架：0-下架, 1-上架不能为空")
    private Integer isActive;

    @Schema(description = "软删除标记：0-未删除, 1-已删除")
    @NotNull(message = "软删除标记：0-未删除, 1-已删除不能为空")
    private Integer isDeleted;


}
