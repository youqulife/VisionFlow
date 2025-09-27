package com.youqusoft.vision.flow.modules.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youqusoft.vision.flow.modules.product.model.entity.Category;
import com.youqusoft.vision.flow.modules.product.model.form.CategoryForm;
import com.youqusoft.vision.flow.modules.product.model.vo.CategoryVO;

/**
 * 商品分类服务接口层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
public interface CategoryService extends IService<Category> {

    /**
     * 商品分类分页列表
     *
     * @param queryParams 商品分类查询参数
     * @return 商品分类分页列表
     */
    IPage<CategoryVO> getCategoryPage(CategoryForm queryParams);

    /**
     * 保存商品分类
     *
     * @param categoryForm 商品分类表单数据
     * @return 是否保存成功
     */
    boolean saveCategory(CategoryForm categoryForm);

    /**
     * 获取商品分类表单数据
     *
     * @param id 商品分类ID
     * @return 商品分类表单数据
     */
    CategoryForm getCategoryForm(Long id);

    /**
     * 更新商品分类
     *
     * @param id 商品分类ID
     * @param categoryForm 商品分类表单数据
     * @return 是否更新成功
     */
    boolean updateCategory(Long id, CategoryForm categoryForm);

    /**
     * 删除商品分类
     *
     * @param ids 商品分类ID数组
     * @return 是否删除成功
     */
    boolean deleteCategories(Long[] ids);
}