package com.youqusoft.vision.flow.modules.order.model.entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youqusoft.vision.flow.common.base.BaseEntity;

/**
 * 订单明细实体对象
 *
 * @author youqusoft
 * @since 2025-10-06 21:14
 */
@Getter
@Setter
@TableName("order_item")
public class OrderItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 商品SKU ID
     */
    private Long productSkuId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 商品名称快照
     */
    private String productName;
    /**
     * 品牌名称快照
     */
    private String brandName;
    /**
     * 分类名称快照
     */
    private String categoryName;
    /**
     * SKU编码快照
     */
    private String skuCode;
    /**
     * SKU属性快照
     */
    private String skuAttributes;
    /**
     * 销售单价快照
     */
    private BigDecimal unitPrice;
    /**
     * 成本单价快照
     */
    private BigDecimal costPrice;
    /**
     * 购买数量
     */
    private Integer quantity;
    /**
     * 商品小计
     */
    private BigDecimal subTotal;
    /**
     * 折扣率
     */
    private BigDecimal discountRate;
    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * 商品总价
     */
    private BigDecimal itemTotal;
    /**
     * 适用眼睛
     */
    private String eyeSide;
    /**
     * 镜片处方数据
     */
    private String lensPrescription;
    private Integer isDeleted;
}
