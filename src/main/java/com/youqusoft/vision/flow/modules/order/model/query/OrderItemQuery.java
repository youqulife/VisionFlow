package com.youqusoft.vision.flow.modules.order.model.query;

import com.youqusoft.vision.flow.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 订单明细分页查询对象
 *
 * @author youqusoft
 * @since 2025-10-06 21:14
 */
@Schema(description ="订单明细查询对象")
@Getter
@Setter
public class OrderItemQuery extends BasePageQuery {
    List<Long> orderIds;
}
