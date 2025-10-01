package com.youqusoft.vision.flow.modules.product.controller;

import com.youqusoft.vision.flow.modules.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youqusoft.vision.flow.modules.product.model.form.ProductForm;
import com.youqusoft.vision.flow.modules.product.model.query.ProductQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.ProductVO;
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
 * 商品信息前端控制层
 *
 * @author youqusoft
 * @since 2025-09-27 11:59
 */
@Tag(name = "商品信息接口")
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController  {

    private final ProductService productService;

    @Operation(summary = "商品信息分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPerm('product:product:query')")
    public PageResult<ProductVO> getProductPage(ProductQuery queryParams ) {
        IPage<ProductVO> result = productService.getProductPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增商品信息")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('product:product:add')")
    public Result<Void> saveProduct(@RequestBody @Valid ProductForm formData ) {
        boolean result = productService.saveProduct(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取商品信息表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('product:product:edit')")
    public Result<ProductForm> getProductForm(
        @Parameter(description = "商品信息ID") @PathVariable Long id
    ) {
        ProductForm formData = productService.getProductFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改商品信息")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('product:product:edit')")
    public Result<Void> updateProduct(
            @Parameter(description = "商品信息ID") @PathVariable Long id,
            @RequestBody @Validated ProductForm formData
    ) {
        boolean result = productService.updateProduct(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除商品信息")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('product:product:delete')")
    public Result<Void> deleteProducts(
        @Parameter(description = "商品信息ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = productService.deleteProducts(ids);
        return Result.judge(result);
    }
}
