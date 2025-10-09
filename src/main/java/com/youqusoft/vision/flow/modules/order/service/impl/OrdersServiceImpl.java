package com.youqusoft.vision.flow.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.youqusoft.vision.flow.common.constant.TenantConstants;
import com.youqusoft.vision.flow.modules.order.converter.OrderItemConverter;
import com.youqusoft.vision.flow.modules.order.model.entity.OrderItem;
import com.youqusoft.vision.flow.modules.order.model.form.OrderItemForm;
import com.youqusoft.vision.flow.modules.order.model.vo.OrderItemVO;
import com.youqusoft.vision.flow.modules.order.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youqusoft.vision.flow.modules.order.mapper.OrdersMapper;
import com.youqusoft.vision.flow.modules.order.service.OrdersService;
import com.youqusoft.vision.flow.modules.order.model.entity.Orders;
import com.youqusoft.vision.flow.modules.order.model.form.OrdersForm;
import com.youqusoft.vision.flow.modules.order.model.query.OrdersQuery;
import com.youqusoft.vision.flow.modules.order.model.vo.OrdersVO;
import com.youqusoft.vision.flow.modules.order.converter.OrdersConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单服务实现类
 *
 * @author youqusoft
 * @since 2025-10-06 21:13
 */
@Service
@RequiredArgsConstructor
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    private final OrdersConverter ordersConverter;
    private final OrderItemService orderItemService;
    private final OrderItemConverter orderItemConverter;

    /**
    * 获取订单分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<OrdersVO>} 订单分页列表
    */
    @Override
    public IPage<OrdersVO> getOrdersPage(OrdersQuery queryParams) {
        Page<OrdersVO> pageVO = this.baseMapper.getOrdersPage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams
        );
        if (pageVO.getRecords().isEmpty()) {
            return pageVO;
        }
        List<Long> orderIds = pageVO.getRecords().stream()
                .map(OrdersVO::getId)
                .toList();
        List<OrderItem> orderItemList = this.orderItemService.list(
                new LambdaQueryWrapper<OrderItem>().in(OrderItem::getOrderId, orderIds));

        // 将OrderItem按orderId分组
        Map<Long, List<OrderItem>> orderItemMapByOrderId = orderItemList.stream()
                .collect(Collectors.groupingBy(OrderItem::getOrderId));

        // 将OrderItem转换为OrderItemVO并按orderId分组
        Map<Long, List<OrderItemVO>> orderItemVoMapByOrderId = orderItemMapByOrderId.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(orderItemConverter::toVO)
                                .toList()
                ));

        // 将OrderItemVO列表设置到对应的OrdersVO中
        pageVO.getRecords().forEach(orderVO -> {
            // 将OrderItemVO列表设置到OrdersVO中
            List<OrderItemVO> orderItemVOs = orderItemVoMapByOrderId.getOrDefault(orderVO.getId(), Lists.newArrayList());
            orderVO.setItems(orderItemVOs);
        });
        return pageVO;
    }
    
    /**
     * 获取订单表单数据
     *
     * @param id 订单ID
     * @return 订单表单数据
     */
    @Override
    public OrdersForm getOrdersFormData(Long id) {
        Orders entity = this.getById(id);
        return ordersConverter.toForm(entity);
    }
    
    /**
     * 新增订单
     *
     * @param formData 订单表单对象
     * @return 是否新增成功
     */
    @Override
    @Transactional
    public boolean saveOrders(OrdersForm formData) {
        Orders entity = ordersConverter.toEntity(formData);
        entity.setTenantId(TenantConstants.DEFAULT_TENANT_ID);
        boolean isSuccess = this.save(entity);
        if (!isSuccess) {
            throw new RuntimeException("新增订单失败");
        }
        if (formData.getItems().isEmpty()) {
            throw new RuntimeException("订单明细为空");
        }
        List<OrderItem> orderItemList = formData.getItems().stream()
                .map(orderItemConverter::toEntity)
                .peek(orderItem -> {
                    orderItem.setOrderId(entity.getId());
                    orderItem.setTenantId(TenantConstants.DEFAULT_TENANT_ID);
                })
                .toList();
        return this.orderItemService.saveBatch(orderItemList);
    }
    
    /**
     * 更新订单
     *
     * @param id   订单ID
     * @param formData 订单表单对象
     * @return 是否修改成功
     */
    @Override
    @Transactional
    public boolean updateOrders(Long id,OrdersForm formData) {
        // 更新订单主表信息
        Orders entity = ordersConverter.toEntity(formData);
        entity.setId(id); // 确保ID正确
        boolean updateResult = this.updateById(entity);

        if (!updateResult) {
            throw new RuntimeException("更新订单失败");
        }

        // 处理订单明细项的更新
        if (formData.getItems() != null) {
            // 获取当前数据库中该订单的所有明细项
            List<OrderItem> existingItems = this.orderItemService.list(
                new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, id)
            );

            // 收集需要更新和新增的明细项
            List<OrderItem> itemsToSave = formData.getItems().stream()
                .map(orderItemConverter::toEntity)
                .peek(orderItem -> {
                    orderItem.setOrderId(id);
                    orderItem.setTenantId(entity.getTenantId());
                    orderItem.setIsDeleted(0);
                })
                .toList();

            // 设置需要更新的明细项ID
            for (int i = 0; i < formData.getItems().size(); i++) {
                OrderItemForm itemForm = formData.getItems().get(i);
                OrderItem item = itemsToSave.get(i);
                if (itemForm.getId() != null) {
                    // 更新已存在的明细项
                    item.setId(itemForm.getId());
                }
            }

            // 查找需要删除的明细项（存在于数据库但不在表单中的明细项）
            List<Long> formItemIds = formData.getItems().stream()
                .map(OrderItemForm::getId)
                .filter(Objects::nonNull)
                .toList();

            List<Long> itemsToDelete = existingItems.stream()
                .map(OrderItem::getId)
                .filter(existingId -> !formItemIds.contains(existingId))
                .toList();

            // 执行订单明细项的批量更新和新增
            if (!itemsToSave.isEmpty()) {
                this.orderItemService.saveOrUpdateBatch(itemsToSave);
            }

            // 执行订单明细项的删除操作
            if (!itemsToDelete.isEmpty()) {
                this.removeByIds(itemsToDelete);
            }
        }

        return true;
    }
    
    /**
     * 删除订单
     *
     * @param ids 订单ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    @Override
    @Transactional
    public boolean deleteOrderss(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的订单数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

}
