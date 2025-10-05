package com.youqusoft.vision.flow.modules.product.service;

import com.youqusoft.vision.flow.common.model.Option;
import com.youqusoft.vision.flow.modules.product.model.entity.Category;
import com.youqusoft.vision.flow.modules.product.model.form.CategoryForm;
import com.youqusoft.vision.flow.modules.product.model.query.CategoryQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.CategoryVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品分类服务类
 *
 * @author youqusoft
 * @since 2025-09-27 12:28
 */
public interface CategoryService extends IService<Category> {

    /**
     * 获取商品分类列表（树状结构）
     *
     * @return {@link List<CategoryVO>} 商品分类列表
     */
    List<CategoryVO> listCategories(CategoryQuery queryParams);

    /**
     * 商品分类下拉列表
     *
     * @return {@link List<Option>} 商品分类下拉列表
     */
    List<Option<Long>> listCategoryOptions();

    /**
     * 获取商品分类表单数据
     *
     * @param id 商品分类ID
     * @return 商品分类表单数据
     */
     CategoryForm getCategoryFormData(Long id);

    /**
     * 新增商品分类
     *
     * @param formData 商品分类表单对象
     * @return 是否新增成功
     */
    boolean saveCategory(CategoryForm formData);

    /**
     * 修改商品分类
     *
     * @param id   商品分类ID
     * @param formData 商品分类表单对象
     * @return 是否修改成功
     */
    boolean updateCategory(Long id, CategoryForm formData);

    /**
     * 删除商品分类
     *
     * @param ids 商品分类ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    boolean deleteCategories(String ids);

}