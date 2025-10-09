package com.youqusoft.vision.flow.modules.order.controller;

import com.youqusoft.vision.flow.modules.order.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youqusoft.vision.flow.modules.order.model.form.OrderItemForm;
import com.youqusoft.vision.flow.modules.order.model.query.OrderItemQuery;
import com.youqusoft.vision.flow.modules.order.model.vo.OrderItemVO;
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
 * 订单明细前端控制层
 *
 * @author youqusoft
 * @since 2025-10-06 21:14
 */
@Tag(name = "订单明细接口")
@RestController
@RequestMapping("/api/v1/order-item")
@RequiredArgsConstructor
public class OrderItemController  {

    private final OrderItemService orderItemService;

    @Operation(summary = "订单明细分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPerm('order:order-item:query')")
    public PageResult<OrderItemVO> getOrderItemPage(OrderItemQuery queryParams ) {
        IPage<OrderItemVO> result = orderItemService.getOrderItemPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增订单明细")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('order:order-item:add')")
    public Result<Void> saveOrderItem(@RequestBody @Valid OrderItemForm formData ) {
        boolean result = orderItemService.saveOrderItem(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取订单明细表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('order:order-item:edit')")
    public Result<OrderItemForm> getOrderItemForm(
        @Parameter(description = "订单明细ID") @PathVariable Long id
    ) {
        OrderItemForm formData = orderItemService.getOrderItemFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改订单明细")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('order:order-item:edit')")
    public Result<Void> updateOrderItem(
            @Parameter(description = "订单明细ID") @PathVariable Long id,
            @RequestBody @Validated OrderItemForm formData
    ) {
        boolean result = orderItemService.updateOrderItem(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除订单明细")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('order:order-item:delete')")
    public Result<Void> deleteOrderItems(
        @Parameter(description = "订单明细ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = orderItemService.deleteOrderItems(ids);
        return Result.judge(result);
    }
}
