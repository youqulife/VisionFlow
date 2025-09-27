package com.youqusoft.vision.flow.modules.customer.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youqusoft.vision.flow.common.result.PageResult;
import com.youqusoft.vision.flow.common.result.Result;
import com.youqusoft.vision.flow.modules.customer.model.form.CustomerForm;
import com.youqusoft.vision.flow.modules.customer.model.query.CustomerQuery;
import com.youqusoft.vision.flow.modules.customer.model.vo.CustomerVO;
import com.youqusoft.vision.flow.modules.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 顾客信息控制层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Tag(name = "顾客管理")
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "顾客分页列表")
    @GetMapping
    public PageResult<CustomerVO> getCustomerPage(
            @Parameter(description = "顾客查询参数") CustomerQuery queryParams
    ) {
        IPage<CustomerVO> page = customerService.getCustomerPage(queryParams);
        return PageResult.success(page);
    }

    @Operation(summary = "保存顾客信息")
    @PostMapping
    public Result<Boolean> saveCustomer(
            @RequestBody @Valid CustomerForm customerForm
    ) {
        boolean result = customerService.saveCustomer(customerForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取顾客表单数据")
    @GetMapping("/{id}")
    public Result<CustomerForm> getCustomerForm(
            @Parameter(description = "顾客ID") @PathVariable Long id
    ) {
        CustomerForm customerForm = customerService.getCustomerForm(id);
        return Result.success(customerForm);
    }

    @Operation(summary = "更新顾客信息")
    @PutMapping("/{id}")
    public Result<Boolean> updateCustomer(
            @Parameter(description = "顾客ID") @PathVariable Long id,
            @RequestBody @Valid CustomerForm customerForm
    ) {
        boolean result = customerService.updateCustomer(id, customerForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除顾客信息")
    @DeleteMapping("/{ids}")
    public Result<Boolean> deleteCustomers(
            @Parameter(description = "顾客ID数组") @PathVariable Long[] ids
    ) {
        boolean result = customerService.deleteCustomers(ids);
        return Result.judge(result);
    }
}