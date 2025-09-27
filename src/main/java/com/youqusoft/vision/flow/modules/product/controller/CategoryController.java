package com.youqusoft.vision.flow.modules.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youqusoft.vision.flow.common.result.PageResult;
import com.youqusoft.vision.flow.common.result.Result;
import com.youqusoft.vision.flow.modules.product.model.form.CategoryForm;
import com.youqusoft.vision.flow.modules.product.model.vo.CategoryVO;
import com.youqusoft.vision.flow.modules.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 商品分类控制层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Tag(name = "商品分类管理")
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "商品分类分页列表")
    @GetMapping
    public Result<PageResult<CategoryVO>> getCategoryPage(
            @Parameter(description = "商品分类查询参数") CategoryForm queryParams
    ) {
        IPage<CategoryVO> page = categoryService.getCategoryPage(queryParams);
        return Result.success(PageResult.success(page));
    }

    @Operation(summary = "保存商品分类")
    @PostMapping
    public Result<Boolean> saveCategory(
            @RequestBody @Valid CategoryForm categoryForm
    ) {
        boolean result = categoryService.saveCategory(categoryForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取商品分类表单数据")
    @GetMapping("/{id}")
    public Result<CategoryForm> getCategoryForm(
            @Parameter(description = "商品分类ID") @PathVariable Long id
    ) {
        CategoryForm categoryForm = categoryService.getCategoryForm(id);
        return Result.success(categoryForm);
    }

    @Operation(summary = "更新商品分类")
    @PutMapping("/{id}")
    public Result<Boolean> updateCategory(
            @Parameter(description = "商品分类ID") @PathVariable Long id,
            @RequestBody @Valid CategoryForm categoryForm
    ) {
        boolean result = categoryService.updateCategory(id, categoryForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除商品分类")
    @DeleteMapping("/{ids}")
    public Result<Boolean> deleteCategories(
            @Parameter(description = "商品分类ID数组") @PathVariable Long[] ids
    ) {
        boolean result = categoryService.deleteCategories(ids);
        return Result.judge(result);
    }
}