package com.youqusoft.vision.flow.modules.product.converter;

import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.modules.product.model.entity.Product;
import com.youqusoft.vision.flow.modules.product.model.form.ProductForm;

/**
 * 商品信息对象转换器
 *
 * @author youqusoft
 * @since 2025-09-27 11:59
 */
@Mapper(componentModel = "spring")
public interface ProductConverter{

    ProductForm toForm(Product entity);

    Product toEntity(ProductForm formData);
}