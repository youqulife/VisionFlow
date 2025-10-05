package com.youqusoft.vision.flow.modules.product.model.query;

import com.youqusoft.vision.flow.modules.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

/**
 * 商品SKU分页查询对象
 *
 * @author youqusoft
 * @since 2025-10-05 09:55
 */
@Schema(description ="商品SKU查询对象")
@Getter
@Setter
public class ProductSkuQuery extends BasePageQuery {

}
