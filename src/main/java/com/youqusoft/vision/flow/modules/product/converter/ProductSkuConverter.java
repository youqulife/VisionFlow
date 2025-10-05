package com.youqusoft.vision.flow.modules.product.converter;

import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.modules.product.model.entity.ProductSku;
import com.youqusoft.vision.flow.modules.product.model.form.ProductSkuForm;

/**
 * 商品SKU对象转换器
 *
 * @author youqusoft
 * @since 2025-10-05 09:55
 */
@Mapper(componentModel = "spring")
public interface ProductSkuConverter{

    ProductSkuForm toForm(ProductSku entity);

    ProductSku toEntity(ProductSkuForm formData);
}