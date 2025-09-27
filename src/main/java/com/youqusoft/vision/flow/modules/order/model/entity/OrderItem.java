package com.youqusoft.vision.flow.modules.order.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.youqusoft.vision.flow.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单明细实体类
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("order_items")
public class OrderItem extends BaseEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关联的订单ID
     */
    private Long orderId;

    /**
     * 关联的商品ID
     */
    private Long productId;

    /**
     * 商品分类ID快照
     */
    private Long categoryId;

    /**
     * 商品类型：frame-镜架, lens-镜片, other-其他
     */
    private String productType;

    /**
     * 商品名称快照
     */
    private String productName;

    /**
     * 商品单价快照
     */
    private Double unitPrice;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 商品总价
     */
    private Double itemTotal;

    /**
     * 软删除标记：0-未删除, 1-已删除
     */
    @TableLogic
    private Integer isDeleted;
}