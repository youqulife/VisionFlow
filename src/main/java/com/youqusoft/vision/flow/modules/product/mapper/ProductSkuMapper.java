package com.youqusoft.vision.flow.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.modules.product.model.entity.ProductSku;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.modules.product.model.query.ProductSkuQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.ProductSkuVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品SKUMapper接口
 *
 * @author youqusoft
 * @since 2025-10-05 09:55
 */
@Mapper
public interface ProductSkuMapper extends BaseMapper<ProductSku> {

    /**
     * 获取商品SKU分页数据
     *
     * @param page 分页对象
     * @param queryParams 查询参数
     * @return {@link Page<ProductSkuVO>} 商品SKU分页列表
     */
    Page<ProductSkuVO> getProductSkuPage(Page<ProductSkuVO> page, ProductSkuQuery queryParams);

}
