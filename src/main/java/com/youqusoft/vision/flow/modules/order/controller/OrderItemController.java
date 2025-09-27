package com.youqusoft.vision.flow.modules.order.controller;

import com.youqusoft.vision.flow.common.result.Result;
import com.youqusoft.vision.flow.modules.order.model.form.OrderItemForm;
import com.youqusoft.vision.flow.modules.order.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 订单明细控制层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Tag(name = "订单明细管理")
@RestController
@RequestMapping("/api/v1/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Operation(summary = "保存订单明细")
    @PostMapping
    public Result<Boolean> saveOrderItem(
            @RequestBody @Valid OrderItemForm orderItemForm
    ) {
        boolean result = orderItemService.saveOrderItem(orderItemForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取订单明细表单数据")
    @GetMapping("/{id}")
    public Result<OrderItemForm> getOrderItemForm(
            @Parameter(description = "订单明细ID") @PathVariable Long id
    ) {
        OrderItemForm orderItemForm = orderItemService.getOrderItemForm(id);
        return Result.success(orderItemForm);
    }

    @Operation(summary = "更新订单明细")
    @PutMapping("/{id}")
    public Result<Boolean> updateOrderItem(
            @Parameter(description = "订单明细ID") @PathVariable Long id,
            @RequestBody @Valid OrderItemForm orderItemForm
    ) {
        boolean result = orderItemService.updateOrderItem(id, orderItemForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除订单明细")
    @DeleteMapping("/{ids}")
    public Result<Boolean> deleteOrderItems(
            @Parameter(description = "订单明细ID数组") @PathVariable Long[] ids
    ) {
        boolean result = orderItemService.deleteOrderItems(ids);
        return Result.judge(result);
    }
}