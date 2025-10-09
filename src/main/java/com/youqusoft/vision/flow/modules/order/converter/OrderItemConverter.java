package com.youqusoft.vision.flow.modules.order.converter;

import com.youqusoft.vision.flow.modules.order.model.vo.OrderItemVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.modules.order.model.entity.OrderItem;
import com.youqusoft.vision.flow.modules.order.model.form.OrderItemForm;
import com.youqusoft.vision.flow.modules.product.model.form.ProductSKUAttribute;

/**
 * 订单明细对象转换器
 *
 * @author youqusoft
 * @since 2025-10-06 21:14
 */
@Mapper(componentModel = "spring")
public interface OrderItemConverter{

    @Mapping(source = "skuAttributes", target = "skuAttributes", qualifiedByName = "jsonToProductSKUAttribute")
    OrderItemForm toForm(OrderItem entity);

    @Mapping(source = "skuAttributes", target = "skuAttributes", qualifiedByName = "productSKUAttributeToJson")
    OrderItem toEntity(OrderItemForm formData);

    @Mapping(source = "skuAttributes", target = "skuAttributes", qualifiedByName = "jsonToProductSKUAttribute")
    OrderItemVO toVO(OrderItem entity);

    @Named("productSKUAttributeToJson")
    default String productSKUAttributeToJson(ProductSKUAttribute productSKUAttribute) {
        return productSKUAttribute != null ? productSKUAttribute.toJsonString() : null;
    }

    @Named("jsonToProductSKUAttribute")
    default ProductSKUAttribute jsonToProductSKUAttribute(String productSKUAttributeJson) {
        return productSKUAttributeJson != null ? ProductSKUAttribute.fromJsonString(productSKUAttributeJson) : null;
    }
}