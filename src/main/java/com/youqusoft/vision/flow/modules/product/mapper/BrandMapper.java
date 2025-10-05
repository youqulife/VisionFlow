package com.youqusoft.vision.flow.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.modules.product.model.entity.Brand;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.modules.product.model.query.BrandQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.BrandVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌Mapper接口
 *
 * @author youqusoft
 * @since 2025-10-04 20:49
 */
@Mapper
public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * 获取品牌分页数据
     *
     * @param page 分页对象
     * @param queryParams 查询参数
     * @return {@link Page<BrandVO>} 品牌分页列表
     */
    Page<BrandVO> getBrandPage(Page<BrandVO> page, BrandQuery queryParams);

}
