package com.youqusoft.vision.flow.modules.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youqusoft.vision.flow.modules.order.mapper.OrderItemMapper;
import com.youqusoft.vision.flow.modules.order.service.OrderItemService;
import com.youqusoft.vision.flow.modules.order.model.entity.OrderItem;
import com.youqusoft.vision.flow.modules.order.model.form.OrderItemForm;
import com.youqusoft.vision.flow.modules.order.model.query.OrderItemQuery;
import com.youqusoft.vision.flow.modules.order.model.vo.OrderItemVO;
import com.youqusoft.vision.flow.modules.order.converter.OrderItemConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 订单明细服务实现类
 *
 * @author youqusoft
 * @since 2025-10-06 21:14
 */
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    private final OrderItemConverter orderItemConverter;

    /**
    * 获取订单明细分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<OrderItemVO>} 订单明细分页列表
    */
    @Override
    public IPage<OrderItemVO> getOrderItemPage(OrderItemQuery queryParams) {
        Page<OrderItemVO> pageVO = this.baseMapper.getOrderItemPage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams
        );
        return pageVO;
    }
    
    /**
     * 获取订单明细表单数据
     *
     * @param id 订单明细ID
     * @return 订单明细表单数据
     */
    @Override
    public OrderItemForm getOrderItemFormData(Long id) {
        OrderItem entity = this.getById(id);
        return orderItemConverter.toForm(entity);
    }
    
    /**
     * 新增订单明细
     *
     * @param formData 订单明细表单对象
     * @return 是否新增成功
     */
    @Override
    public boolean saveOrderItem(OrderItemForm formData) {
        OrderItem entity = orderItemConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新订单明细
     *
     * @param id   订单明细ID
     * @param formData 订单明细表单对象
     * @return 是否修改成功
     */
    @Override
    public boolean updateOrderItem(Long id,OrderItemForm formData) {
        OrderItem entity = orderItemConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除订单明细
     *
     * @param ids 订单明细ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    @Override
    public boolean deleteOrderItems(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的订单明细数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

}
