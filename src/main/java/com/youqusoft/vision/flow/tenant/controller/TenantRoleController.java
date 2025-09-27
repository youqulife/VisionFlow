package com.youqusoft.vision.flow.tenant.controller;

import com.youqusoft.vision.flow.tenant.service.TenantRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youqusoft.vision.flow.tenant.model.form.TenantRoleForm;
import com.youqusoft.vision.flow.tenant.model.query.TenantRoleQuery;
import com.youqusoft.vision.flow.tenant.model.vo.TenantRoleVO;
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
 * 租户角色前端控制层
 *
 * @author youqulife
 * @since 2025-09-27 08:47
 */
@Tag(name = "租户角色接口")
@RestController
@RequestMapping("/api/v1/tenant-role")
@RequiredArgsConstructor
public class TenantRoleController  {

    private final TenantRoleService tenantRoleService;

    @Operation(summary = "租户角色分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPerm('tenant:tenant-role:query')")
    public PageResult<TenantRoleVO> getTenantRolePage(TenantRoleQuery queryParams ) {
        IPage<TenantRoleVO> result = tenantRoleService.getTenantRolePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增租户角色")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('tenant:tenant-role:add')")
    public Result<Void> saveTenantRole(@RequestBody @Valid TenantRoleForm formData ) {
        boolean result = tenantRoleService.saveTenantRole(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取租户角色表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('tenant:tenant-role:edit')")
    public Result<TenantRoleForm> getTenantRoleForm(
        @Parameter(description = "租户角色ID") @PathVariable Long id
    ) {
        TenantRoleForm formData = tenantRoleService.getTenantRoleFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改租户角色")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('tenant:tenant-role:edit')")
    public Result<Void> updateTenantRole(
            @Parameter(description = "租户角色ID") @PathVariable Long id,
            @RequestBody @Validated TenantRoleForm formData
    ) {
        boolean result = tenantRoleService.updateTenantRole(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除租户角色")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('tenant:tenant-role:delete')")
    public Result<Void> deleteTenantRoles(
        @Parameter(description = "租户角色ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = tenantRoleService.deleteTenantRoles(ids);
        return Result.judge(result);
    }
}
