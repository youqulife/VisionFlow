
-- 开启事务
START TRANSACTION;

-- =============================================
-- 表：租户表 (saas_tenants)
-- 功能：存储所有租户（眼镜店）的基本信息
-- =============================================
CREATE TABLE saas_tenant
(
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '租户ID',
    tenant_code         VARCHAR(50)                                  NOT NULL UNIQUE COMMENT '租户编码：唯一标识，可用于子域名',
    name                VARCHAR(100)                                 NOT NULL COMMENT '租户名称（店铺名称）',
    contact_person      VARCHAR(50) COMMENT '联系人姓名',
    contact_phone       VARCHAR(20) COMMENT '联系电话',
    contact_email       VARCHAR(100) COMMENT '联系邮箱',
    address             TEXT COMMENT '店铺地址',
    industry_type       VARCHAR(50) COMMENT '行业类型：optical-眼镜店, hair-美发店等',
    subscription_plan   ENUM ('basic', 'professional', 'enterprise') NOT NULL DEFAULT 'basic' COMMENT '订阅计划',
    subscription_status ENUM ('active', 'suspended', 'expired')      NOT NULL DEFAULT 'active' COMMENT '订阅状态',
    expires_at          DATE COMMENT '订阅到期日期',
    max_users           INT                                          NOT NULL DEFAULT 5 COMMENT '最大用户数',
    is_active           TINYINT(1)                                   NOT NULL DEFAULT 1 COMMENT '是否激活',
    create_time          TIMESTAMP                                    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time          TIMESTAMP                                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    INDEX idx_tenant_code (tenant_code),
    INDEX idx_subscription_status (subscription_status)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='租户信息表';

-- 插入一个示例租户
INSERT INTO saas_tenant (tenant_code, name, contact_person, contact_phone, industry_type)
VALUES ('lensmaster001', '明亮眼镜店', '张经理', '13800138000', 'optical');

-- =============================================
-- 表：租户用户表 (saas_tenant_users)
-- 功能：存储所有租户的用户账号信息
-- =============================================
CREATE TABLE saas_tenant_user
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    tenant_id     BIGINT                                                                                NOT NULL COMMENT '所属租户ID',
    username      VARCHAR(50)                                                                           NOT NULL COMMENT '用户名',
    email         VARCHAR(100) COMMENT '邮箱',
    phone         VARCHAR(20) COMMENT '手机号',
    password_hash VARCHAR(255)                                                                          NOT NULL COMMENT '密码哈希',
    real_name     VARCHAR(50)                                                                           NOT NULL COMMENT '真实姓名',
    avatar_url    VARCHAR(255) COMMENT '头像URL',
    role          ENUM ('super_admin', 'tenant_admin', 'manager', 'optometrist', 'sales', 'technician') NOT NULL DEFAULT 'sales' COMMENT '用户角色',
    department    VARCHAR(50) COMMENT '部门',
    is_active     TINYINT(1)                                                                            NOT NULL DEFAULT 1 COMMENT '是否激活',
    last_login_at TIMESTAMP                                                                             NULL COMMENT '最后登录时间',
    create_time    TIMESTAMP                                                                             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time    TIMESTAMP                                                                             NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    CONSTRAINT fk_tenant_user_tenant
        FOREIGN KEY (tenant_id) REFERENCES saas_tenant (id),

    UNIQUE INDEX idx_tenant_username (tenant_id, username),
    UNIQUE INDEX idx_tenant_email (tenant_id, email),
    INDEX idx_tenant_role (tenant_id, role)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='租户用户表（多租户）';

-- =============================================
-- 表：租户角色表 (saas_tenant_roles)
-- 功能：存储每个租户的角色定义
-- =============================================
CREATE TABLE saas_tenant_role
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '角色ID',
    tenant_id   BIGINT      NOT NULL COMMENT '所属租户ID',
    name        VARCHAR(64) NOT NULL COMMENT '角色名称',
    code        VARCHAR(32) NOT NULL COMMENT '角色编码',
    description VARCHAR(255) COMMENT '角色描述',
    is_system   TINYINT(1)  NOT NULL DEFAULT 0 COMMENT '是否系统角色',
    is_active   TINYINT(1)  NOT NULL DEFAULT 1 COMMENT '是否激活',
    create_time  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    CONSTRAINT fk_tenant_role_tenant
        FOREIGN KEY (tenant_id) REFERENCES saas_tenant (id),

    UNIQUE INDEX uk_tenant_role_code (tenant_id, code)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='租户角色表';

-- =============================================
-- 表：租户菜单表 (saas_tenant_menus)
-- 功能：存储每个租户的菜单定义
-- =============================================
CREATE TABLE `saas_tenant_menu`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   bigint       NOT NULL COMMENT '所属租户ID',
    `parent_id`   bigint       NOT NULL COMMENT '父菜单ID',
    `tree_path`   varchar(255) COMMENT '父节点ID路径',
    `name`        varchar(64)  NOT NULL COMMENT '菜单名称',
    `type`        tinyint      NOT NULL COMMENT '菜单类型（1-菜单 2-目录 3-外链 4-按钮）',
    `route_name`  varchar(255) COMMENT '路由名称（Vue Router 中用于命名路由）',
    `route_path`  varchar(128) COMMENT '路由路径（Vue Router 中定义的 URL 路径）',
    `component`   varchar(128) COMMENT '组件路径（组件页面完整路径，相对于 src/views/，缺省后缀 .vue）',
    `perm`        varchar(128) COMMENT '【按钮】权限标识',
    `always_show` tinyint    DEFAULT 0 COMMENT '【目录】只有一个子路由是否始终显示（1-是 0-否）',
    `keep_alive`  tinyint    DEFAULT 0 COMMENT '【菜单】是否开启页面缓存（1-是 0-否）',
    `visible`     tinyint(1) DEFAULT 1 COMMENT '显示状态（1-显示 0-隐藏）',
    `sort`        int        DEFAULT 0 COMMENT '排序',
    `icon`        varchar(64) COMMENT '菜单图标',
    `redirect`    varchar(128) COMMENT '跳转路径',
    `create_time` datetime     NULL COMMENT '创建时间',
    `update_time` datetime     NULL COMMENT '更新时间',
    `params`      varchar(255) NULL COMMENT '路由参数',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4 COMMENT = '租户菜单表';

-- =============================================
-- 表：租户用户角色关联表 (saas_tenant_user_roles)
-- 功能：存储租户用户和角色的关联关系
-- =============================================
CREATE TABLE saas_tenant_user_role
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    tenant_id  BIGINT    NOT NULL COMMENT '所属租户ID',
    user_id    BIGINT    NOT NULL COMMENT '用户ID',
    role_id    BIGINT    NOT NULL COMMENT '角色ID',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    CONSTRAINT fk_tenant_user_role_tenant
        FOREIGN KEY (tenant_id) REFERENCES saas_tenant (id),

    CONSTRAINT fk_tenant_user_role_user
        FOREIGN KEY (user_id) REFERENCES saas_tenant_user (id),

    CONSTRAINT fk_tenant_user_role_role
        FOREIGN KEY (role_id) REFERENCES saas_tenant_role (id),

    UNIQUE INDEX uk_tenant_user_role (tenant_id, user_id, role_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='租户用户角色关联表';

-- =============================================
-- 表：租户角色菜单关联表 (saas_tenant_role_menus)
-- 功能：存储租户角色和菜单的关联关系
-- =============================================
CREATE TABLE saas_tenant_role_menu
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    tenant_id  BIGINT    NOT NULL COMMENT '所属租户ID',
    role_id    BIGINT    NOT NULL COMMENT '角色ID',
    menu_id    BIGINT    NOT NULL COMMENT '菜单ID',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    CONSTRAINT fk_tenant_role_menu_tenant
        FOREIGN KEY (tenant_id) REFERENCES saas_tenant (id),

    CONSTRAINT fk_tenant_role_menu_role
        FOREIGN KEY (role_id) REFERENCES saas_tenant_role (id),

    CONSTRAINT fk_tenant_role_menu_menu
        FOREIGN KEY (menu_id) REFERENCES saas_tenant_menu (id),

    UNIQUE INDEX uk_tenant_role_menu (tenant_id, role_id, menu_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='租户角色菜单关联表';

-- =============================================
-- 表：租户管理员映射表 (saas_tenant_admins)
-- 功能：存储租户和平台管理员的映射关系
-- =============================================
CREATE TABLE saas_tenant_admin
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    tenant_id  BIGINT    NOT NULL COMMENT '租户ID',
    sys_user_id    BIGINT    NOT NULL COMMENT '平台管理员用户ID（指向sys_user表）',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    CONSTRAINT fk_tenant_admin_tenant
        FOREIGN KEY (tenant_id) REFERENCES saas_tenant (id),

    UNIQUE KEY uk_tenant (tenant_id),
    UNIQUE KEY uk_user (sys_user_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='租户管理员映射表';

-- 提交事务
COMMIT;
