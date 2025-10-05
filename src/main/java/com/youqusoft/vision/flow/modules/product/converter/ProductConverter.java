package com.youqusoft.vision.flow.modules.product.converter;

import com.youqusoft.vision.flow.modules.product.model.entity.Product;
import com.youqusoft.vision.flow.modules.product.model.form.ProductAttribute;
import com.youqusoft.vision.flow.modules.product.model.form.ProductForm;
import com.youqusoft.vision.flow.modules.product.model.vo.ProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * 商品信息对象转换器
 *
 * @author youqusoft
 * @since 2025-09-27 11:59
 */
@Mapper(componentModel = "spring")
public interface ProductConverter {

    @Mapping(source = "attribute", target = "attribute", qualifiedByName = "jsonToProductAttribute")
    ProductForm toForm(Product entity);

    @Mapping(source = "attribute", target = "attribute", qualifiedByName = "productAttributeToJson")
    Product toEntity(ProductForm formData);

    @Mapping(source = "attribute", target = "attribute", qualifiedByName = "jsonToProductAttribute")
    ProductVO toVO(Product entity);

    @Named("productAttributeToJson")
    default String productAttributeToJson(ProductAttribute attribute) {
        return attribute.toJsonString();
    }

    @Named("jsonToProductAttribute")
    default ProductAttribute jsonToProductAttribute(String attributeJson) {
        return attributeJson != null ? ProductAttribute.fromJsonString(attributeJson) : null;
    }
}