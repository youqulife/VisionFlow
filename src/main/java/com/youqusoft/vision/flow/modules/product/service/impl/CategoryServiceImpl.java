package com.youqusoft.vision.flow.modules.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youqusoft.vision.flow.modules.product.mapper.CategoryMapper;
import com.youqusoft.vision.flow.modules.product.service.CategoryService;
import com.youqusoft.vision.flow.modules.product.model.entity.Category;
import com.youqusoft.vision.flow.modules.product.model.form.CategoryForm;
import com.youqusoft.vision.flow.modules.product.model.query.CategoryQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.CategoryVO;
import com.youqusoft.vision.flow.modules.product.converter.CategoryConverter;

import java.util.Arrays;
import java.util.List;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 商品分类服务实现类
 *
 * @author youqusoft
 * @since 2025-09-27 12:28
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    private final CategoryConverter categoryConverter;

    /**
    * 获取商品分类分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<CategoryVO>} 商品分类分页列表
    */
    @Override
    public IPage<CategoryVO> getCategoryPage(CategoryQuery queryParams) {
        Page<CategoryVO> pageVO = this.baseMapper.getCategoryPage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams
        );
        return pageVO;
    }
    
    /**
     * 获取商品分类表单数据
     *
     * @param id 商品分类ID
     * @return 商品分类表单数据
     */
    @Override
    public CategoryForm getCategoryFormData(Long id) {
        Category entity = this.getById(id);
        return categoryConverter.toForm(entity);
    }
    
    /**
     * 新增商品分类
     *
     * @param formData 商品分类表单对象
     * @return 是否新增成功
     */
    @Override
    public boolean saveCategory(CategoryForm formData) {
        Category entity = categoryConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新商品分类
     *
     * @param id   商品分类ID
     * @param formData 商品分类表单对象
     * @return 是否修改成功
     */
    @Override
    public boolean updateCategory(Long id,CategoryForm formData) {
        Category entity = categoryConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除商品分类
     *
     * @param ids 商品分类ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    @Override
    public boolean deleteCategorys(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的商品分类数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

}
