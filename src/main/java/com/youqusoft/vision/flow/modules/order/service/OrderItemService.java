package com.youqusoft.vision.flow.modules.order.service;

import com.youqusoft.vision.flow.modules.order.model.entity.OrderItem;
import com.youqusoft.vision.flow.modules.order.model.form.OrderItemForm;
import com.youqusoft.vision.flow.modules.order.model.query.OrderItemQuery;
import com.youqusoft.vision.flow.modules.order.model.vo.OrderItemVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 订单明细服务类
 *
 * @author youqusoft
 * @since 2025-10-06 21:14
 */
public interface OrderItemService extends IService<OrderItem> {

    /**
     *订单明细分页列表
     *
     * @return {@link IPage<OrderItemVO>} 订单明细分页列表
     */
    IPage<OrderItemVO> getOrderItemPage(OrderItemQuery queryParams);

    /**
     * 获取订单明细表单数据
     *
     * @param id 订单明细ID
     * @return 订单明细表单数据
     */
     OrderItemForm getOrderItemFormData(Long id);

    /**
     * 新增订单明细
     *
     * @param formData 订单明细表单对象
     * @return 是否新增成功
     */
    boolean saveOrderItem(OrderItemForm formData);

    /**
     * 修改订单明细
     *
     * @param id   订单明细ID
     * @param formData 订单明细表单对象
     * @return 是否修改成功
     */
    boolean updateOrderItem(Long id, OrderItemForm formData);

    /**
     * 删除订单明细
     *
     * @param ids 订单明细ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    boolean deleteOrderItems(String ids);

}
