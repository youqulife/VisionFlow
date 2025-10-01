package com.youqusoft.vision.flow.modules.product.converter;

import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.modules.product.model.entity.Category;
import com.youqusoft.vision.flow.modules.product.model.form.CategoryForm;

/**
 * 商品分类对象转换器
 *
 * @author youqusoft
 * @since 2025-09-27 12:28
 */
@Mapper(componentModel = "spring")
public interface CategoryConverter{

    CategoryForm toForm(Category entity);

    Category toEntity(CategoryForm formData);
}