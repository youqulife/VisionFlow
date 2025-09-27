package com.youqusoft.vision.flow.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.modules.product.model.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品信息访问层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}