package com.youqusoft.vision.flow.modules.product.converter;

import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.modules.product.model.entity.Brand;
import com.youqusoft.vision.flow.modules.product.model.form.BrandForm;

/**
 * 品牌对象转换器
 *
 * @author youqusoft
 * @since 2025-10-04 20:49
 */
@Mapper(componentModel = "spring")
public interface BrandConverter{

    BrandForm toForm(Brand entity);

    Brand toEntity(BrandForm formData);
}