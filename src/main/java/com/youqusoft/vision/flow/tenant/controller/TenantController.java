package com.youqusoft.vision.flow.tenant.controller;

import com.youqusoft.vision.flow.tenant.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youqusoft.vision.flow.tenant.model.form.TenantForm;
import com.youqusoft.vision.flow.tenant.model.query.TenantQuery;
import com.youqusoft.vision.flow.tenant.model.vo.TenantVO;
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
 * 租户信息前端控制层
 *
 * @author youqulife
 * @since 2025-09-26 22:24
 */
@Tag(name = "租户信息接口")
@RestController
@RequestMapping("/api/v1/tenant")
@RequiredArgsConstructor
public class TenantController  {

    private final TenantService tenantService;

    @Operation(summary = "租户信息分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPerm('tenant:tenant:query')")
    public PageResult<TenantVO> getTenantPage(TenantQuery queryParams ) {
        IPage<TenantVO> result = tenantService.getTenantPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增租户信息")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('tenant:tenant:add')")
    public Result<Void> saveTenant(@RequestBody @Valid TenantForm formData ) {
        boolean result = tenantService.saveTenant(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取租户信息表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('tenant:tenant:edit')")
    public Result<TenantForm> getTenantForm(
        @Parameter(description = "租户信息ID") @PathVariable Long id
    ) {
        TenantForm formData = tenantService.getTenantFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改租户信息")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('tenant:tenant:edit')")
    public Result<Void> updateTenant(
            @Parameter(description = "租户信息ID") @PathVariable Long id,
            @RequestBody @Validated TenantForm formData
    ) {
        boolean result = tenantService.updateTenant(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除租户信息")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('tenant:tenant:delete')")
    public Result<Void> deleteTenants(
        @Parameter(description = "租户信息ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = tenantService.deleteTenants(ids);
        return Result.judge(result);
    }
}
