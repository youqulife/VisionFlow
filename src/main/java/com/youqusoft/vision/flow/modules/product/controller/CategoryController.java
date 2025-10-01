package com.youqusoft.vision.flow.modules.product.controller;

import com.youqusoft.vision.flow.modules.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youqusoft.vision.flow.modules.product.model.form.CategoryForm;
import com.youqusoft.vision.flow.modules.product.model.query.CategoryQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.CategoryVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youqusoft.vision.flow.common.result.PageResult;
import com.youqusoft.vision.flow.common.result.Result;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

/**
 * 商品分类前端控制层
 *
 * @author youqusoft
 * @since 2025-09-27 12:28
 */
@Tag(name = "商品分类接口")
@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController  {

    private final CategoryService categoryService;

    @Operation(summary = "商品分类分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPerm('product:category:query')")
    public PageResult<CategoryVO> getCategoryPage(CategoryQuery queryParams ) {
        IPage<CategoryVO> result = categoryService.getCategoryPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增商品分类")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('product:category:add')")
    public Result<Void> saveCategory(@RequestBody @Valid CategoryForm formData ) {
        boolean result = categoryService.saveCategory(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取商品分类表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('product:category:edit')")
    public Result<CategoryForm> getCategoryForm(
        @Parameter(description = "商品分类ID") @PathVariable Long id
    ) {
        CategoryForm formData = categoryService.getCategoryFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改商品分类")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('product:category:edit')")
    public Result<Void> updateCategory(
            @Parameter(description = "商品分类ID") @PathVariable Long id,
            @RequestBody @Validated CategoryForm formData
    ) {
        boolean result = categoryService.updateCategory(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除商品分类")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('product:category:delete')")
    public Result<Void> deleteCategorys(
        @Parameter(description = "商品分类ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = categoryService.deleteCategorys(ids);
        return Result.judge(result);
    }
}
