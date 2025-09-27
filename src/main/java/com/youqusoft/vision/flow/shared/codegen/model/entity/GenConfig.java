package com.youqusoft.vision.flow.shared.codegen.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import com.youqusoft.vision.flow.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 代码生成基础配置
 *
 * @author Ray
 * @since 2.10.0
 */
@TableName(value = "gen_config")
@Getter
@Setter
public class GenConfig extends BaseEntity {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 实体类名
     */
    private String entityName;

    /**
     * 业务名
     */
    private String businessName;

    /**
     * 父菜单ID
     */
    private Long parentMenuId;

    /**
     * 作者
     */
    private String author;

    /**
     * 页面类型 classic|curd
     */
    private String pageType;

    /**
     * 要移除的表前缀，如: sys_
     */
    private String removeTablePrefix;
}