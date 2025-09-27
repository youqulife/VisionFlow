package com.youqusoft.vision.flow.modules.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youqusoft.vision.flow.modules.order.model.entity.Order;
import com.youqusoft.vision.flow.modules.order.model.form.OrderForm;
import com.youqusoft.vision.flow.modules.order.model.query.OrderQuery;
import com.youqusoft.vision.flow.modules.order.model.vo.OrderVO;

/**
 * 订单主表服务接口层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
public interface OrderService extends IService<Order> {

    /**
     * 订单分页列表
     *
     * @param queryParams 订单查询参数
     * @return 订单分页列表
     */
    IPage<OrderVO> getOrderPage(OrderQuery queryParams);

    /**
     * 保存订单信息
     *
     * @param orderForm 订单表单数据
     * @return 是否保存成功
     */
    boolean saveOrder(OrderForm orderForm);

    /**
     * 获取订单表单数据
     *
     * @param id 订单ID
     * @return 订单表单数据
     */
    OrderForm getOrderForm(Long id);

    /**
     * 更新订单信息
     *
     * @param id 订单ID
     * @param orderForm 订单表单数据
     * @return 是否更新成功
     */
    boolean updateOrder(Long id, OrderForm orderForm);

    /**
     * 删除订单信息
     *
     * @param ids 订单ID数组
     * @return 是否删除成功
     */
    boolean deleteOrders(Long[] ids);
}