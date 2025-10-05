package com.youqusoft.vision.flow.modules.product.converter;

import com.youqusoft.vision.flow.modules.product.model.entity.ProductSku;
import com.youqusoft.vision.flow.modules.product.model.form.ProductSKUAttribute;
import com.youqusoft.vision.flow.modules.product.model.form.ProductSkuForm;
import com.youqusoft.vision.flow.modules.product.model.vo.ProductSkuVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * 商品SKU对象转换器
 *
 * @author youqusoft
 * @since 2025-10-05
 */
@Mapper(componentModel = "spring")
public interface ProductSkuConverter {

    @Mapping(source = "skuAttributes", target = "skuAttributes", qualifiedByName = "jsonToProductSKUAttribute")
    ProductSkuForm toForm(ProductSku entity);

    @Mapping(source = "skuAttributes", target = "skuAttributes", qualifiedByName = "productSKUAttributeToJson")
    ProductSku toEntity(ProductSkuForm formData);

    @Mapping(source = "skuAttributes", target = "skuAttributes", qualifiedByName = "jsonToProductSKUAttribute")
    ProductSkuVO toVO(ProductSku entity);

    @Named("productSKUAttributeToJson")
    default String productSKUAttributeToJson(ProductSKUAttribute productSKUAttribute) {
        return productSKUAttribute.toJsonString();
    }

    @Named("jsonToProductSKUAttribute")
    default ProductSKUAttribute jsonToProductSKUAttribute(String productSKUAttributeJson) {
        return productSKUAttributeJson != null ? ProductSKUAttribute.fromJsonString(productSKUAttributeJson) : null;
    }
}