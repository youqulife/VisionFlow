package com.youqusoft.vision.flow.modules.product.controller;

import com.youqusoft.vision.flow.modules.product.service.ProductSkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youqusoft.vision.flow.modules.product.model.form.ProductSkuForm;
import com.youqusoft.vision.flow.modules.product.model.query.ProductSkuQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.ProductSkuVO;
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
 * 商品SKU前端控制层
 *
 * @author youqusoft
 * @since 2025-10-05 09:55
 */
@Tag(name = "商品SKU接口")
@RestController
@RequestMapping("/api/v1/product-sku")
@RequiredArgsConstructor
public class ProductSkuController  {

    private final ProductSkuService productSkuService;

    @Operation(summary = "新增商品SKU")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('product:product-sku:add')")
    public Result<Void> saveProductSku(@RequestBody @Valid ProductSkuForm formData ) {
        boolean result = productSkuService.saveProductSku(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取商品SKU表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('product:product-sku:edit')")
    public Result<ProductSkuForm> getProductSkuForm(
        @Parameter(description = "商品SKUID") @PathVariable Long id
    ) {
        ProductSkuForm formData = productSkuService.getProductSkuFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改商品SKU")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('product:product-sku:edit')")
    public Result<Void> updateProductSku(
            @Parameter(description = "商品SKUID") @PathVariable Long id,
            @RequestBody @Validated ProductSkuForm formData
    ) {
        boolean result = productSkuService.updateProductSku(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除商品SKU")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('product:product:delete')")
    public Result<Void> deleteProductSkus(
        @Parameter(description = "商品SKUID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        try {
            boolean result = productSkuService.deleteProductSkus(ids);
            return Result.judge(result);
        } catch (RuntimeException e) {
            return Result.failed(e.getMessage());
        }
    }
}
