package com.youqusoft.vision.flow.modules.order.service;

import com.youqusoft.vision.flow.modules.order.model.entity.Orders;
import com.youqusoft.vision.flow.modules.order.model.form.OrdersForm;
import com.youqusoft.vision.flow.modules.order.model.query.OrdersQuery;
import com.youqusoft.vision.flow.modules.order.model.vo.OrdersVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 订单服务类
 *
 * @author youqusoft
 * @since 2025-10-06 21:13
 */
public interface OrdersService extends IService<Orders> {

    /**
     *订单分页列表
     *
     * @return {@link IPage<OrdersVO>} 订单分页列表
     */
    IPage<OrdersVO> getOrdersPage(OrdersQuery queryParams);

    /**
     * 获取订单表单数据
     *
     * @param id 订单ID
     * @return 订单表单数据
     */
     OrdersForm getOrdersFormData(Long id);

    /**
     * 新增订单
     *
     * @param formData 订单表单对象
     * @return 是否新增成功
     */
    boolean saveOrders(OrdersForm formData);

    /**
     * 修改订单
     *
     * @param id   订单ID
     * @param formData 订单表单对象
     * @return 是否修改成功
     */
    boolean updateOrders(Long id, OrdersForm formData);

    /**
     * 删除订单
     *
     * @param ids 订单ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    boolean deleteOrderss(String ids);

}
