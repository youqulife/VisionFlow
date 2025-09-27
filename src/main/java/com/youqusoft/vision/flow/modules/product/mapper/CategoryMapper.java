package com.youqusoft.vision.flow.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.modules.product.model.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类访问层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}