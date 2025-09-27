package com.youqusoft.vision.flow.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.modules.order.model.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单明细访问层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}