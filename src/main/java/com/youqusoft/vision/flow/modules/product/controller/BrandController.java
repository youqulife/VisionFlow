package com.youqusoft.vision.flow.modules.product.controller;

import com.youqusoft.vision.flow.modules.product.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youqusoft.vision.flow.modules.product.model.form.BrandForm;
import com.youqusoft.vision.flow.modules.product.model.query.BrandQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.BrandVO;
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
 * 品牌前端控制层
 *
 * @author youqusoft
 * @since 2025-10-04 20:49
 */
@Tag(name = "品牌接口")
@RestController
@RequestMapping("/api/v1/brand")
@RequiredArgsConstructor
public class BrandController  {

    private final BrandService brandService;

    @Operation(summary = "品牌分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPerm('product:brand:query')")
    public PageResult<BrandVO> getBrandPage(BrandQuery queryParams ) {
        IPage<BrandVO> result = brandService.getBrandPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增品牌")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('product:brand:add')")
    public Result<Void> saveBrand(@RequestBody @Valid BrandForm formData ) {
        boolean result = brandService.saveBrand(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取品牌表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('product:brand:edit')")
    public Result<BrandForm> getBrandForm(
        @Parameter(description = "品牌ID") @PathVariable Long id
    ) {
        BrandForm formData = brandService.getBrandFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改品牌")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('product:brand:edit')")
    public Result<Void> updateBrand(
            @Parameter(description = "品牌ID") @PathVariable Long id,
            @RequestBody @Validated BrandForm formData
    ) {
        boolean result = brandService.updateBrand(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除品牌")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('product:brand:delete')")
    public Result<Void> deleteBrands(
        @Parameter(description = "品牌ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = brandService.deleteBrands(ids);
        return Result.judge(result);
    }
}
