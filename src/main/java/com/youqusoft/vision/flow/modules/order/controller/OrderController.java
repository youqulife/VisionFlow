package com.youqusoft.vision.flow.modules.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youqusoft.vision.flow.common.result.PageResult;
import com.youqusoft.vision.flow.common.result.Result;
import com.youqusoft.vision.flow.modules.order.model.form.OrderForm;
import com.youqusoft.vision.flow.modules.order.model.query.OrderQuery;
import com.youqusoft.vision.flow.modules.order.model.vo.OrderVO;
import com.youqusoft.vision.flow.modules.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 订单主表控制层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Tag(name = "订单管理")
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "订单分页列表")
    @GetMapping
    public PageResult<OrderVO> getOrderPage(
            @Parameter(description = "订单查询参数") OrderQuery queryParams
    ) {
        IPage<OrderVO> page = orderService.getOrderPage(queryParams);
        return PageResult.success(page);
    }

    @Operation(summary = "保存订单信息")
    @PostMapping
    public Result<Boolean> saveOrder(
            @RequestBody @Valid OrderForm orderForm
    ) {
        boolean result = orderService.saveOrder(orderForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取订单表单数据")
    @GetMapping("/{id}")
    public Result<OrderForm> getOrderForm(
            @Parameter(description = "订单ID") @PathVariable Long id
    ) {
        OrderForm orderForm = orderService.getOrderForm(id);
        return Result.success(orderForm);
    }

    @Operation(summary = "更新订单信息")
    @PutMapping("/{id}")
    public Result<Boolean> updateOrder(
            @Parameter(description = "订单ID") @PathVariable Long id,
            @RequestBody @Valid OrderForm orderForm
    ) {
        boolean result = orderService.updateOrder(id, orderForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除订单信息")
    @DeleteMapping("/{ids}")
    public Result<Boolean> deleteOrders(
            @Parameter(description = "订单ID数组") @PathVariable Long[] ids
    ) {
        boolean result = orderService.deleteOrders(ids);
        return Result.judge(result);
    }
}