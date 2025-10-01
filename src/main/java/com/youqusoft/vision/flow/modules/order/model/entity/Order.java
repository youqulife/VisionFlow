package com.youqusoft.vision.flow.modules.order.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.youqusoft.vision.flow.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单主表实体类
 *
 * @author Jack
 * @since 2025-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("orders")
public class Order extends BaseEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 关联的顾客ID
     */
    private Long customerId;

    /**
     * 关联的验光记录ID（可空）
     */
    private Long eyeExamId;

    /**
     * 订单总金额
     */
    private Double totalAmount;

    /**
     * 优惠金额
     */
    private Double discountAmount;

    /**
     * 实付金额
     */
    private Double finalAmount;

    /**
     * 订单状态：draft-草稿, pending-待支付, paid-已支付, processing-加工中, completed-已完成, cancelled-已取消
     */
    private String status;

    /**
     * 支付方式：cash-现金, alipay-支付宝, wechat-微信, card-银行卡, member-会员余额
     */
    private String paymentMethod;

    /**
     * 订单备注
     */
    private String notes;

    /**
     * 软删除标记：0-未删除, 1-已删除
     */
    @TableLogic
    private Integer isDeleted;

    private OrderType orderType;

    /**
     * 判断是否需要验光数据
     */
    public boolean requiresEyeExam() {
        return orderType == OrderType.OPTICAL || orderType == OrderType.PROGRESSIVE;
    }

    public enum OrderType {
        OPTICAL,        // 光学眼镜（需要验光）
        SUNGLASSES,     // 太阳镜（不需要验光）
        CONTACT_LENS,   // 隐形眼镜（可能需要验光）
        ACCESSORIES,    // 配件（不需要验光）
        SERVICE,        // 维修服务（不需要验光）
        PROGRESSIVE     // 渐进镜片（需要验光）
    }
}