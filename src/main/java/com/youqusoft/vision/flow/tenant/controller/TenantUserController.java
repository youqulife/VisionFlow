package com.youqusoft.vision.flow.tenant.controller;

import com.youqusoft.vision.flow.tenant.service.TenantUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youqusoft.vision.flow.tenant.model.form.TenantUserForm;
import com.youqusoft.vision.flow.tenant.model.query.TenantUserQuery;
import com.youqusoft.vision.flow.tenant.model.vo.TenantUserVO;
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
 * 租户用户（多租户）前端控制层
 *
 * @author youqulife
 * @since 2025-09-27 08:53
 */
@Tag(name = "租户用户（多租户）接口")
@RestController
@RequestMapping("/api/v1/tenant-user")
@RequiredArgsConstructor
public class TenantUserController  {

    private final TenantUserService tenantUserService;

    @Operation(summary = "租户用户（多租户）分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPerm('tenant:tenant-user:query')")
    public PageResult<TenantUserVO> getTenantUserPage(TenantUserQuery queryParams ) {
        IPage<TenantUserVO> result = tenantUserService.getTenantUserPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增租户用户（多租户）")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('tenant:tenant-user:add')")
    public Result<Void> saveTenantUser(@RequestBody @Valid TenantUserForm formData ) {
        boolean result = tenantUserService.saveTenantUser(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取租户用户（多租户）表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('tenant:tenant-user:edit')")
    public Result<TenantUserForm> getTenantUserForm(
        @Parameter(description = "租户用户（多租户）ID") @PathVariable Long id
    ) {
        TenantUserForm formData = tenantUserService.getTenantUserFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改租户用户（多租户）")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('tenant:tenant-user:edit')")
    public Result<Void> updateTenantUser(
            @Parameter(description = "租户用户（多租户）ID") @PathVariable Long id,
            @RequestBody @Validated TenantUserForm formData
    ) {
        boolean result = tenantUserService.updateTenantUser(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除租户用户（多租户）")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('tenant:tenant-user:delete')")
    public Result<Void> deleteTenantUsers(
        @Parameter(description = "租户用户（多租户）ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = tenantUserService.deleteTenantUsers(ids);
        return Result.judge(result);
    }
}
