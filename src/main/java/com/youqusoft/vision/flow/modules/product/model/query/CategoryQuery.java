package com.youqusoft.vision.flow.modules.product.model.query;

import com.youqusoft.vision.flow.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 商品分类分页查询对象
 *
 * @author youqusoft
 * @since 2025-09-27 12:28
 */
@Schema(description ="商品分类查询对象")
@Getter
@Setter
public class CategoryQuery extends BasePageQuery {

    @Schema(description="关键字(分类名称)")
    private String keywords;

    @Schema(description="状态(1->显示；0->隐藏)")
    private Integer status;

}
