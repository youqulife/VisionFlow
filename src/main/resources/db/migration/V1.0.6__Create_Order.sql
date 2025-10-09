-- =============================================
-- 表：订单表 (orders)
-- 功能：存储销售订单的头信息
-- =============================================
CREATE TABLE `orders`
(
    id                BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    tenant_id         BIGINT      NOT NULL COMMENT '租户ID',
    customer_id       BIGINT      NOT NULL COMMENT '顾客ID',
    eye_exam_id       BIGINT COMMENT '关联的验光记录ID',

    -- 订单基本信息
    order_number      VARCHAR(50) NOT NULL UNIQUE COMMENT '订单号',
    order_type        ENUM ('optical', 'sunglasses', 'contact_lens', 'accessories', 'service')              DEFAULT 'optical',

    -- 金额信息
    total_amount      DECIMAL(10, 2)                                                                        DEFAULT 0.00 COMMENT '订单总金额',
    discount_amount   DECIMAL(10, 2)                                                                        DEFAULT 0.00 COMMENT '优惠金额',
    final_amount      DECIMAL(10, 2)                                                                        DEFAULT 0.00 COMMENT '实付金额',

    -- 状态管理
    status            ENUM ('draft', 'quoted', 'confirmed', 'paid', 'processing', 'completed', 'cancelled') DEFAULT 'draft',
    payment_method    ENUM ('cash', 'alipay', 'wechat', 'card', 'member','other') COMMENT '支付方式',

    -- 处方信息快照
    prescription_data JSON COMMENT '处方数据快照',

    notes             TEXT COMMENT '订单备注',
    is_deleted        TINYINT(1)                                                                            DEFAULT 0,
    create_time       TIMESTAMP                                                                             DEFAULT CURRENT_TIMESTAMP,
    update_time       TIMESTAMP                                                                             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_tenant_status (tenant_id, status),
    INDEX idx_tenant_customer (tenant_id, customer_id),
    INDEX idx_eye_exam (eye_exam_id)
) COMMENT ='订单表';

-- =============================================
-- 表：订单明细表 (order_items) - 修正版
-- 功能：存储订单的具体商品明细
-- =============================================
CREATE TABLE order_item
(
    id                BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    tenant_id         BIGINT         NOT NULL COMMENT '租户ID',
    order_id          BIGINT         NOT NULL COMMENT '订单ID',

    -- 关联具体SKU（核心变更）
    product_sku_id    BIGINT         NOT NULL COMMENT '商品SKU ID',

    -- 商品基础信息快照
    product_id        BIGINT         NOT NULL COMMENT '商品ID',
    product_name      VARCHAR(200)   NOT NULL COMMENT '商品名称快照',
    brand_name        VARCHAR(100) COMMENT '品牌名称快照',
    category_name     VARCHAR(50) COMMENT '分类名称快照',

    -- SKU详细信息快照
    sku_code          VARCHAR(50)    NOT NULL COMMENT 'SKU编码快照',
    sku_attributes    JSON           NOT NULL COMMENT 'SKU属性快照',

    -- 价格信息
    unit_price        DECIMAL(10, 2) NOT NULL COMMENT '销售单价快照',
    cost_price        DECIMAL(10, 2) COMMENT '成本单价快照',
    quantity          INT            NOT NULL        DEFAULT 1 COMMENT '购买数量',
    item_total        DECIMAL(10, 2) NOT NULL COMMENT '商品总价',

    -- 镜片特殊字段
    eye_side          ENUM ('right', 'left', 'both') DEFAULT 'both' COMMENT '适用眼睛',
    lens_prescription JSON COMMENT '镜片处方数据',

    is_deleted        TINYINT(1)                     DEFAULT 0,
    create_time       TIMESTAMP                      DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_order (order_id),
    INDEX idx_product_sku (product_sku_id),
    INDEX idx_tenant_sku (tenant_id, sku_code)
) COMMENT ='订单明细表';