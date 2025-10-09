package com.youqusoft.vision.flow.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.modules.order.model.entity.Orders;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.modules.order.model.query.OrdersQuery;
import com.youqusoft.vision.flow.modules.order.model.vo.OrdersVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单Mapper接口
 *
 * @author youqusoft
 * @since 2025-10-06 21:13
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    /**
     * 获取订单分页数据
     *
     * @param page 分页对象
     * @param queryParams 查询参数
     * @return {@link Page<OrdersVO>} 订单分页列表
     */
    Page<OrdersVO> getOrdersPage(Page<OrdersVO> page, OrdersQuery queryParams);

}
