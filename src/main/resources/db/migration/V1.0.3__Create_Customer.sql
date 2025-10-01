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
    create_time   TIMESTAMP                          NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    update_time   TIMESTAMP                          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',

    -- 索引定义
    -- 修改索引，包含tenant_id
    UNIQUE INDEX idx_tenant_phone (tenant_id, phone, is_deleted) COMMENT '手机号唯一索引（考虑软删除状态）',
    INDEX idx_tenant_name (tenant_id, name),
    INDEX idx_member (member_level) COMMENT '会员等级查询索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='顾客信息表';
