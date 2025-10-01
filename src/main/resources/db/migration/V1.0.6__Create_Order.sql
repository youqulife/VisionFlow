
-- =============================================
-- 表：订单表 (orders)
-- 功能：存储销售订单的头信息
-- =============================================
CREATE TABLE orders
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    tenant_id       BIGINT                                                                    NOT NULL COMMENT '租户ID',
    order_number    VARCHAR(50)                                                               NOT NULL UNIQUE COMMENT '订单编号：唯一订单号，可按规则生成',
    customer_id     BIGINT                                                                    NOT NULL COMMENT '关联的顾客ID',
    eye_exam_id     BIGINT COMMENT '关联的验光记录ID：本次订单使用的验光数据',
    total_amount    DECIMAL(10, 2)                                                            NOT NULL DEFAULT 0.00 COMMENT '订单总金额：商品原价总和',
    discount_amount DECIMAL(10, 2)                                                            NOT NULL DEFAULT 0.00 COMMENT '优惠金额：总优惠金额',
    final_amount    DECIMAL(10, 2)                                                            NOT NULL DEFAULT 0.00 COMMENT '实付金额：total_amount - discount_amount',
    status          ENUM ('draft', 'pending', 'paid', 'processing', 'completed', 'cancelled') NOT NULL DEFAULT 'draft' COMMENT '订单状态：draft-草稿, pending-待支付, paid-已支付, processing-加工中, completed-已完成, cancelled-已取消',
    payment_method  ENUM ('cash', 'alipay', 'wechat', 'card', 'member') COMMENT '支付方式：cash-现金, alipay-支付宝, wechat-微信, card-银行卡, member-会员余额',
    notes           TEXT COMMENT '订单备注：如特殊加工要求、取镜时间等',
    is_deleted      TINYINT(1)                                                                NOT NULL DEFAULT 0 COMMENT '软删除标记：0-未删除, 1-已删除',
    create_time      TIMESTAMP                                                                 NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
    update_time      TIMESTAMP                                                                 NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单最后更新时间',

    -- 外键和索引
    INDEX idx_status (status) COMMENT '订单状态查询索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单主表';


-- =============================================
-- 表：订单明细表 (order_items) - 修正版
-- 功能：存储订单的具体商品明细
-- =============================================
CREATE TABLE order_item
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    order_id     BIGINT                          NOT NULL COMMENT '关联的订单ID',
    product_id   BIGINT                          NOT NULL COMMENT '关联的商品ID',
    category_id  BIGINT                          NOT NULL COMMENT '商品分类ID快照：下单时的分类',
    product_type ENUM ('frame', 'lens', 'other') NOT NULL COMMENT '商品类型：frame-镜架, lens-镜片, other-其他',
    product_name VARCHAR(200)                    NOT NULL COMMENT '商品名称快照：下单时的商品名称',
    unit_price   DECIMAL(10, 2)                  NOT NULL COMMENT '商品单价快照：下单时的销售单价',
    quantity     INT                             NOT NULL DEFAULT 1 COMMENT '购买数量',
    item_total   DECIMAL(10, 2)                  NOT NULL COMMENT '商品总价：unit_price * quantity',
    is_deleted   TINYINT(1)                      NOT NULL DEFAULT 0 COMMENT '软删除标记：0-未删除, 1-已删除',
    create_time   TIMESTAMP                       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',

    -- 外键和索引
    FOREIGN KEY (order_id) REFERENCES orders (id),
    INDEX idx_order (order_id) COMMENT '订单查询索引：快速查找订单的所有商品'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单明细表';