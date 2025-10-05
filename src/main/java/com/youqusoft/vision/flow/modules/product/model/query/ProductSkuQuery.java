package com.youqusoft.vision.flow.modules.product.model.query;

import com.youqusoft.vision.flow.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "商品SKU查询参数")
@Getter
@Setter
public class ProductSkuQuery extends BasePageQuery {
}
