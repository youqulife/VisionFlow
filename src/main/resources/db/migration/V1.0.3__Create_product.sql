-- =============================================
-- 表：顾客表 (customers)
-- 功能：存储眼镜店的所有顾客基本信息
-- =============================================
CREATE TABLE customer
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    tenant_id    BIGINT                             NOT NULL COMMENT '租户ID',
    name         VARCHAR(100)                       NOT NULL COMMENT '顾客姓名',
    phone        VARCHAR(20) COMMENT '手机号码（可作为唯一标识）',
    gender       ENUM ('male', 'female', 'unknown') NOT NULL DEFAULT 'unknown' COMMENT '性别：male-男, female-女, unknown-未知',
    birthday     DATE COMMENT '生日（用于客户关怀和营销）',
    source       VARCHAR(50) COMMENT '顾客来源：如"大众点评"、"朋友推荐"、"线下自然流量"等',
    tags         JSON COMMENT '顾客标签：JSON数组格式，如["高价值客户", "儿童", "价格敏感"]',
    member_level VARCHAR(20)                                 DEFAULT '普通' COMMENT '会员等级：如"普通","银卡","金卡","钻石"',
    balance      DECIMAL(10, 2)                     NOT NULL DEFAULT 0.00 COMMENT '账户余额（储值金额）',
    notes        TEXT COMMENT '备注信息：顾客偏好、特殊需求、病史等',
    is_deleted   TINYINT(1)                         NOT NULL DEFAULT 0 COMMENT '软删除标记：0-未删除, 1-已删除',
    created_at   TIMESTAMP                          NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    updated_at   TIMESTAMP                          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',

    -- 索引定义
    -- 修改索引，包含tenant_id
    UNIQUE INDEX idx_tenant_phone (tenant_id, phone, is_deleted) COMMENT '手机号唯一索引（考虑软删除状态）',
    INDEX idx_tenant_name (tenant_id, name),
    INDEX idx_member (member_level) COMMENT '会员等级查询索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='顾客信息表';

-- =============================================
-- 表：验光记录表 (eye_exams)
-- 功能：存储顾客的每次验光详细数据
-- =============================================
CREATE TABLE eye_exam
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT     NOT NULL COMMENT '关联的顾客ID',
    exam_date   DATE       NOT NULL COMMENT '验光日期',
    optometrist VARCHAR(100) COMMENT '验光师姓名',

    -- 右眼 (OD - Oculus Dexter) 数据
    od_sph      DECIMAL(4, 2) COMMENT '右眼球镜度数：近视(-)或远视(+)度数',
    od_cyl      DECIMAL(4, 2) COMMENT '右眼柱镜度数：散光度数',
    od_axis     INT COMMENT '右眼轴位：散光方向，0-180度',
    od_pd       DECIMAL(4, 1) COMMENT '右眼单眼瞳距：单位毫米',

    -- 左眼 (OS - Oculus Sinister) 数据
    os_sph      DECIMAL(4, 2) COMMENT '左眼球镜度数',
    os_cyl      DECIMAL(4, 2) COMMENT '左眼柱镜度数',
    os_axis     INT COMMENT '左眼轴位',
    os_pd       DECIMAL(4, 1) COMMENT '左眼单眼瞳距',

    -- 其他验光数据
    pd_total    DECIMAL(4, 1) COMMENT '双眼瞳距：总瞳距',
    `add`       DECIMAL(4, 2) COMMENT '下加光：老花度数（ADD）',
    notes       TEXT COMMENT '验光备注：如矫正视力、特殊验光要求等',
    is_deleted  TINYINT(1) NOT NULL DEFAULT 0 COMMENT '软删除标记：0-未删除, 1-已删除',
    created_at  TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    updated_at  TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',

    -- 外键和索引
    FOREIGN KEY (customer_id) REFERENCES customer (id),
    INDEX idx_customer_date (customer_id, exam_date DESC) COMMENT '复合索引：按顾客和验光日期快速查询'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='验光记录表';

-- =============================================
-- 表：商品分类表 (categories)
-- 功能：存储商品的分类信息，支持层级结构
-- =============================================
CREATE TABLE category
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    tenant_id   BIGINT      NOT NULL COMMENT '租户ID',
    name        VARCHAR(50) NOT NULL COMMENT '分类名称',
    parent_id   BIGINT               DEFAULT NULL COMMENT '父级分类ID：用于支持多级分类，NULL表示顶级分类',
    sort_order  INT         NOT NULL DEFAULT 0 COMMENT '排序号：数字越小排序越前',
    description TEXT COMMENT '分类描述',
    is_active   TINYINT(1)  NOT NULL DEFAULT 1 COMMENT '是否启用：0-禁用, 1-启用',
    is_deleted  TINYINT(1)  NOT NULL DEFAULT 0 COMMENT '软删除标记：0-未删除, 1-已删除',
    created_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    updated_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',

    -- 索引
    FOREIGN KEY (parent_id) REFERENCES category (id),
    INDEX idx_parent_sort (parent_id, sort_order) COMMENT '按父级和排序号查询索引',
    UNIQUE INDEX idx_name_parent (name, parent_id, is_deleted) COMMENT '同一父级下分类名称唯一'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='商品分类表';

-- 插入默认分类数据
INSERT INTO category (name, tenant_id, parent_id, sort_order, description)
VALUES ('镜架', 1, NULL, 1, '光学镜架、太阳镜架等'),
       ('镜片', 1, NULL, 2, '各种光学镜片'),
       ('隐形眼镜', 1, NULL, 3, '月抛、日抛等隐形眼镜'),
       ('护理产品', 1, NULL, 4, '护理液、眼镜清洁剂等'),
       ('配件', 1, NULL, 5, '眼镜盒、眼镜布等配件'),
       ('金属镜架', 1, 1, 1, '金属材质的镜架'),
       ('塑料镜架', 1, 1, 2, '塑料材质的镜架'),
       ('钛合金镜架', 1, 1, 3, '钛合金材质的镜架'),
       ('单光镜片', 1, 2, 1, '普通单光镜片'),
       ('渐进镜片', 1, 2, 2, '渐进多焦点镜片'),
       ('防蓝光镜片', 1, 2, 3, '防蓝光功能镜片');

-- =============================================
-- 表：商品表 (products) - 修正版
-- 功能：存储镜架、镜片等商品信息
-- =============================================
CREATE TABLE products
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    tenant_id        BIGINT         NOT NULL COMMENT '租户ID',
    sku              VARCHAR(50) UNIQUE COMMENT '商品SKU编码：唯一库存单位编码',
    name             VARCHAR(200)   NOT NULL COMMENT '商品名称：展示给客户的名称',
    category_id      BIGINT         NOT NULL COMMENT '商品分类ID：关联categories表',
    brand            VARCHAR(100) COMMENT '品牌名称：如"依视路", "蔡司", "雷朋"等',
    model            VARCHAR(100) COMMENT '型号：商品具体型号',

    -- 镜片特有属性（对于非镜片商品这些字段为NULL）
    refractive_index DECIMAL(3, 2) COMMENT '折射率：如1.56, 1.60, 1.67, 1.74等',
    lens_function    VARCHAR(100) COMMENT '镜片功能：如"防蓝光", "变色", "渐进多焦点"等',

    -- 通用商品属性
    purchase_price   DECIMAL(10, 2) NOT NULL COMMENT '进货价格',
    sale_price       DECIMAL(10, 2) NOT NULL COMMENT '销售价格',
    stock_quantity   INT            NOT NULL DEFAULT 0 COMMENT '库存数量',
    min_stock_alert  INT            NOT NULL DEFAULT 5 COMMENT '最低库存预警线',
    is_active        TINYINT(1)     NOT NULL DEFAULT 1 COMMENT '是否上架：0-下架, 1-上架',
    is_deleted       TINYINT(1)     NOT NULL DEFAULT 0 COMMENT '软删除标记：0-未删除, 1-已删除',
    created_at       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    updated_at       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',

    -- 外键和索引
    FOREIGN KEY (category_id) REFERENCES category (id),
    INDEX idx_category (category_id) COMMENT '分类查询索引',
    INDEX idx_brand (brand) COMMENT '品牌查询索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='商品信息表';

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
    created_at      TIMESTAMP                                                                 NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
    updated_at      TIMESTAMP                                                                 NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单最后更新时间',

    -- 外键和索引
    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (eye_exam_id) REFERENCES eye_exam (id),
    INDEX idx_status (status) COMMENT '订单状态查询索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单主表';


-- =============================================
-- 表：订单明细表 (order_items) - 修正版
-- 功能：存储订单的具体商品明细
-- =============================================
CREATE TABLE order_items
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
    created_at   TIMESTAMP                       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',

    -- 外键和索引
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (category_id) REFERENCES category (id),
    INDEX idx_order (order_id) COMMENT '订单查询索引：快速查找订单的所有商品'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单明细表';