package com.youqusoft.vision.flow.modules.order.converter;

import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.modules.order.model.entity.Orders;
import com.youqusoft.vision.flow.modules.order.model.form.OrdersForm;

/**
 * 订单对象转换器
 *
 * @author youqusoft
 * @since 2025-10-06 21:13
 */
@Mapper(componentModel = "spring")
public interface OrdersConverter{

    OrdersForm toForm(Orders entity);

    Orders toEntity(OrdersForm formData);
}