package com.youqusoft.vision.flow.modules.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youqusoft.vision.flow.modules.order.model.entity.OrderItem;
import com.youqusoft.vision.flow.modules.order.model.form.OrderItemForm;

/**
 * 订单明细服务接口层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
public interface OrderItemService extends IService<OrderItem> {

    /**
     * 保存订单明细
     *
     * @param orderItemForm 订单明细表单数据
     * @return 是否保存成功
     */
    boolean saveOrderItem(OrderItemForm orderItemForm);

    /**
     * 获取订单明细表单数据
     *
     * @param id 订单明细ID
     * @return 订单明细表单数据
     */
    OrderItemForm getOrderItemForm(Long id);

    /**
     * 更新订单明细
     *
     * @param id 订单明细ID
     * @param orderItemForm 订单明细表单数据
     * @return 是否更新成功
     */
    boolean updateOrderItem(Long id, OrderItemForm orderItemForm);

    /**
     * 删除订单明细
     *
     * @param ids 订单明细ID数组
     * @return 是否删除成功
     */
    boolean deleteOrderItems(Long[] ids);
}