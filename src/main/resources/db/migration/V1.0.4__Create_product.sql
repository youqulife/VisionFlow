
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
    create_time  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    update_time  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',

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
CREATE TABLE product
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
    create_time       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    update_time       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',

    -- 外键和索引
    FOREIGN KEY (category_id) REFERENCES category (id),
    INDEX idx_category (category_id) COMMENT '分类查询索引',
    INDEX idx_brand (brand) COMMENT '品牌查询索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='商品信息表';
