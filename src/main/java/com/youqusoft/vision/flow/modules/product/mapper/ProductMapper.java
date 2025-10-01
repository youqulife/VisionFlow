package com.youqusoft.vision.flow.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.modules.product.model.entity.Product;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.modules.product.model.query.ProductQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品信息Mapper接口
 *
 * @author youqusoft
 * @since 2025-09-27 11:59
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 获取商品信息分页数据
     *
     * @param page 分页对象
     * @param queryParams 查询参数
     * @return {@link Page<ProductVO>} 商品信息分页列表
     */
    Page<ProductVO> getProductPage(Page<ProductVO> page, ProductQuery queryParams);

}
