package com.youqusoft.vision.flow.modules.order.model.query;

import com.youqusoft.vision.flow.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 订单查询对象
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Data
@Schema(description = "订单查询对象")
public class OrderQuery extends BasePageQuery {

    /**
     * 订单编号
     */
    @Schema(description = "订单编号")
    private String orderNumber;

    /**
     * 关联的顾客ID
     */
    @Schema(description = "关联的顾客ID")
    private Long customerId;

    /**
     * 订单状态
     */
    @Schema(description = "订单状态")
    private String status;
}