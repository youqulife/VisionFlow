package com.youqusoft.vision.flow.modules.customer.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.youqusoft.vision.flow.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 验光记录实体类
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("eye_exam")
public class EyeExam extends BaseEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关联的顾客ID
     */
    private Long customerId;

    /**
     * 验光日期
     */
    private LocalDate examDate;

    /**
     * 验光师姓名
     */
    private String optometrist;

    /**
     * 右眼球镜度数
     */
    private Double odSph;

    /**
     * 右眼柱镜度数
     */
    private Double odCyl;

    /**
     * 右眼轴位
     */
    private Integer odAxis;

    /**
     * 右眼单眼瞳距
     */
    private Double odPd;

    /**
     * 左眼球镜度数
     */
    private Double osSph;

    /**
     * 左眼柱镜度数
     */
    private Double osCyl;

    /**
     * 左眼轴位
     */
    private Integer osAxis;

    /**
     * 左眼单眼瞳距
     */
    private Double osPd;

    /**
     * 双眼瞳距
     */
    private Double pdTotal;

    /**
     * 下加光
     */
    @TableField("`add`")
    private Double add;

    /**
     * 验光备注
     */
    private String notes;

    /**
     * 软删除标记：0-未删除, 1-已删除
     */
    @TableLogic
    private Integer isDeleted;
}