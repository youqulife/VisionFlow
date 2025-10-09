package com.youqusoft.vision.flow.modules.order.model.query;

import com.youqusoft.vision.flow.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单分页查询对象
 *
 * @author youqusoft
 * @since 2025-10-06 21:13
 */
@Schema(description ="订单查询对象")
@Getter
@Setter
public class OrdersQuery extends BasePageQuery {

}
