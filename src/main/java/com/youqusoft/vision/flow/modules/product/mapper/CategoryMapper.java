package com.youqusoft.vision.flow.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.modules.product.model.entity.Category;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.modules.product.model.query.CategoryQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类Mapper接口
 *
 * @author youqusoft
 * @since 2025-09-27 12:28
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 获取商品分类分页数据
     *
     * @param page 分页对象
     * @param queryParams 查询参数
     * @return {@link Page<CategoryVO>} 商品分类分页列表
     */
    Page<CategoryVO> getCategoryPage(Page<CategoryVO> page, CategoryQuery queryParams);

}
