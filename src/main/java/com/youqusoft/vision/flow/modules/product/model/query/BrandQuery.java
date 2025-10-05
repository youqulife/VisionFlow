package com.youqusoft.vision.flow.modules.product.model.query;

import com.youqusoft.vision.flow.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 品牌分页查询对象
 *
 * @author youqusoft
 * @since 2025-10-04 20:49
 */
@Schema(description ="品牌查询对象")
@Getter
@Setter
public class BrandQuery extends BasePageQuery {

}
