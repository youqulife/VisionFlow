-- 验光记录表（每个顾客可有多条记录）
CREATE TABLE eye_exam
(
    id                 BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    tenant_id          BIGINT     NOT NULL COMMENT '租户ID',
    customer_id        BIGINT     NOT NULL COMMENT '关联的顾客ID',

    -- 验光基本信息
    exam_date DATE NOT NULL COMMENT '验光日期',
    optometrist VARCHAR(100) COMMENT '验光师',
    exam_type ENUM('routine', 'complaint', 'first_time', 'glasses_change') DEFAULT 'routine' COMMENT '验光类型',

    -- 验光数据（字段化存储，便于查询）
    -- 右眼数据
    od_sph DECIMAL(4, 2) COMMENT '右眼球镜',
    od_cyl DECIMAL(4, 2) COMMENT '右眼柱镜',
    od_axis INT COMMENT '右眼轴位',
    od_pd DECIMAL(4, 1) COMMENT '右眼瞳距',

    -- 左眼数据
    os_sph DECIMAL(4, 2) COMMENT '左眼球镜',
    os_cyl DECIMAL(4, 2) COMMENT '左眼柱镜',
    os_axis INT COMMENT '左眼轴位',
    os_pd DECIMAL(4, 1) COMMENT '左眼瞳距',

    -- 双眼数据
    pd_total DECIMAL(4, 1) COMMENT '双眼瞳距',
    addition DECIMAL(4, 2) COMMENT '下加光',

    -- 带镜史数据（JSON存储）
    wearing_history JSON NOT NULL COMMENT '带镜史数据',

    -- 业务标记
    has_glasses_history TINYINT(1) DEFAULT 0 COMMENT '是否有戴镜史',
    is_first_exam TINYINT(1) DEFAULT 0 COMMENT '是否首次验光',

    is_deleted         TINYINT(1) NOT NULL DEFAULT 0,
    create_time        TIMESTAMP           DEFAULT CURRENT_TIMESTAMP,
    update_time        TIMESTAMP           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_tenant_customer_date (tenant_id, customer_id, exam_date DESC)
) COMMENT ='验光记录表';