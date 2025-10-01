package com.youqusoft.vision.flow.modules.customer.converter;

import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.modules.customer.model.entity.Customer;
import com.youqusoft.vision.flow.modules.customer.model.form.CustomerForm;

/**
 * 顾客信息对象转换器
 *
 * @author youqusoft
 * @since 2025-09-27 15:49
 */
@Mapper(componentModel = "spring")
public interface CustomerConverter{

    CustomerForm toForm(Customer entity);

    Customer toEntity(CustomerForm formData);
}