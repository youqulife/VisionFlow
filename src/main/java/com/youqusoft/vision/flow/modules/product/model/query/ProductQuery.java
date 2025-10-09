package com.youqusoft.vision.flow.modules.product.model.query;

import com.youqusoft.vision.flow.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * 商品信息分页查询对象
 *
 * @author youqusoft
 * @since 2025-09-27 11:59
 */
@Schema(description ="商品信息查询对象")
@Getter
@Setter
public class ProductQuery extends BasePageQuery {
    @Schema(description = "商品名称")
    private String keywords;
    @Schema(description = "商品分类ID")
    private Long categoryId;
    @Schema(description = "商品品牌ID")
    private Long brandId;
}
