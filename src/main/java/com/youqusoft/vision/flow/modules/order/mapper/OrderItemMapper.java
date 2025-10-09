package com.youqusoft.vision.flow.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.modules.order.model.entity.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.modules.order.model.query.OrderItemQuery;
import com.youqusoft.vision.flow.modules.order.model.vo.OrderItemVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单明细Mapper接口
 *
 * @author youqusoft
 * @since 2025-10-06 21:14
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    /**
     * 获取订单明细分页数据
     *
     * @param page 分页对象
     * @param queryParams 查询参数
     * @return {@link Page<OrderItemVO>} 订单明细分页列表
     */
    Page<OrderItemVO> getOrderItemPage(Page<OrderItemVO> page, OrderItemQuery queryParams);

}
