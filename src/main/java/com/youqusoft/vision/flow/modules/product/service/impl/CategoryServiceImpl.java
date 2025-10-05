package com.youqusoft.vision.flow.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.youqusoft.vision.flow.common.constant.SystemConstants;
import com.youqusoft.vision.flow.common.constant.TenantConstants;
import com.youqusoft.vision.flow.common.model.Option;
import com.youqusoft.vision.flow.system.enums.MenuTypeEnum;
import com.youqusoft.vision.flow.system.model.entity.Menu;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import org.springframework.transaction.annotation.Transactional;

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
     * 获取商品分类列表（树状结构）
     *
     * @return {@link List<CategoryVO>} 商品分类列表
     */
    @Override
    public List<CategoryVO> listCategories(CategoryQuery queryParams) {
        List<Category> categories = this.list(new LambdaQueryWrapper<Category>()
                .like(StrUtil.isNotBlank(queryParams.getKeywords()), Category::getName, queryParams.getKeywords())
                .orderByAsc(Category::getSortOrder)
        );
        
        // 获取所有父级ID
        Set<Long> parentIds = categories.stream()
                .map(Category::getParentId)
                .collect(Collectors.toSet());

        // 过滤掉null值
        parentIds.remove(null);

        // 使用递归函数来构建分类树，从根节点开始
        return buildCategoryTree(null, categories);
    }

    /**
     * 商品分类下拉列表
     *
     * @return {@link List<Option>} 商品分类下拉列表
     */
    @Override
    public List<Option<Long>> listCategoryOptions() {
        List<Category> categoryList = this.list(new LambdaQueryWrapper<Category>()
                .orderByAsc(Category::getSortOrder)
        );
        return buildCategoryOptions(null, categoryList);
    }

    /**
     * 递归生成商品分类下拉层级列表
     *
     * @param parentId 父级ID
     * @param categoryList 商品分类列表
     * @return 商品分类下拉列表
     */
    private List<Option<Long>> buildCategoryOptions(Long parentId, List<Category> categoryList) {
        List<Option<Long>> categoryOptions = new ArrayList<>();

        for (Category category : categoryList) {
            if ((parentId == null && category.getParentId() == null) || 
                (parentId != null && parentId.equals(category.getParentId()))) {
                Option<Long> option = new Option<>(category.getId(), category.getName());
                List<Option<Long>> subCategoryOptions = buildCategoryOptions(category.getId(), categoryList);
                if (!subCategoryOptions.isEmpty()) {
                    option.setChildren(subCategoryOptions);
                }
                categoryOptions.add(option);
            }
        }

        return categoryOptions;
    }

    /**
     * 递归生成分类列表
     *
     * @param parentId 父级ID
     * @param categoryList 分类列表
     * @return 分类列表
     */
    private List<CategoryVO> buildCategoryTree(Long parentId, List<Category> categoryList) {
        return CollectionUtil.emptyIfNull(categoryList)
                .stream()
                .filter(category -> {
                    if (parentId == null) {
                        return category.getParentId() == null;
                    }
                    return category.getParentId() != null && category.getParentId().equals(parentId);
                })
                .map(entity -> {
                    CategoryVO categoryVO = categoryConverter.toVo(entity);
                    List<CategoryVO> children = buildCategoryTree(entity.getId(), categoryList);
                    categoryVO.setChildren(children);
                    return categoryVO;
                }).toList();
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
        entity.setIsDeleted(0);
        entity.setTenantId(TenantConstants.DEFAULT_TENANT_ID);
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
    @Transactional
    public boolean deleteCategories(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的商品分类数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

}