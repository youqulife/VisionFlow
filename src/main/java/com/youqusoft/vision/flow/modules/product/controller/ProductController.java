package com.youqusoft.vision.flow.modules.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youqusoft.vision.flow.common.result.PageResult;
import com.youqusoft.vision.flow.common.result.Result;
import com.youqusoft.vision.flow.modules.product.model.form.ProductForm;
import com.youqusoft.vision.flow.modules.product.model.query.ProductQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.ProductVO;
import com.youqusoft.vision.flow.modules.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 商品信息控制层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Tag(name = "商品管理")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "商品分页列表")
    @GetMapping
    public PageResult<ProductVO> getProductPage(
            @Parameter(description = "商品查询参数") ProductQuery queryParams
    ) {
        IPage<ProductVO> page = productService.getProductPage(queryParams);
        return PageResult.success(page);
    }

    @Operation(summary = "保存商品信息")
    @PostMapping
    public Result<Boolean> saveProduct(
            @RequestBody @Valid ProductForm productForm
    ) {
        boolean result = productService.saveProduct(productForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取商品表单数据")
    @GetMapping("/{id}")
    public Result<ProductForm> getProductForm(
            @Parameter(description = "商品ID") @PathVariable Long id
    ) {
        ProductForm productForm = productService.getProductForm(id);
        return Result.success(productForm);
    }

    @Operation(summary = "更新商品信息")
    @PutMapping("/{id}")
    public Result<Boolean> updateProduct(
            @Parameter(description = "商品ID") @PathVariable Long id,
            @RequestBody @Valid ProductForm productForm
    ) {
        boolean result = productService.updateProduct(id, productForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除商品信息")
    @DeleteMapping("/{ids}")
    public Result<Boolean> deleteProducts(
            @Parameter(description = "商品ID数组") @PathVariable Long[] ids
    ) {
        boolean result = productService.deleteProducts(ids);
        return Result.judge(result);
    }
}