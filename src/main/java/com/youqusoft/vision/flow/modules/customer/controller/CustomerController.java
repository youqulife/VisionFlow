package com.youqusoft.vision.flow.modules.customer.controller;

import com.youqusoft.vision.flow.modules.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youqusoft.vision.flow.modules.customer.model.form.CustomerForm;
import com.youqusoft.vision.flow.modules.customer.model.query.CustomerQuery;
import com.youqusoft.vision.flow.modules.customer.model.vo.CustomerVO;
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
 * 顾客信息前端控制层
 *
 * @author youqusoft
 * @since 2025-09-27 15:49
 */
@Tag(name = "顾客信息接口")
@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController  {

    private final CustomerService customerService;

    @Operation(summary = "顾客信息分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPerm('customer:customer:query')")
    public PageResult<CustomerVO> getCustomerPage(CustomerQuery queryParams ) {
        IPage<CustomerVO> result = customerService.getCustomerPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增顾客信息")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('customer:customer:add')")
    public Result<Void> saveCustomer(@RequestBody @Valid CustomerForm formData ) {
        boolean result = customerService.saveCustomer(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取顾客信息表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('customer:customer:edit')")
    public Result<CustomerForm> getCustomerForm(
        @Parameter(description = "顾客信息ID") @PathVariable Long id
    ) {
        CustomerForm formData = customerService.getCustomerFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改顾客信息")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('customer:customer:edit')")
    public Result<Void> updateCustomer(
            @Parameter(description = "顾客信息ID") @PathVariable Long id,
            @RequestBody @Validated CustomerForm formData
    ) {
        boolean result = customerService.updateCustomer(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除顾客信息")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('customer:customer:delete')")
    public Result<Void> deleteCustomers(
        @Parameter(description = "顾客信息ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = customerService.deleteCustomers(ids);
        return Result.judge(result);
    }
}
