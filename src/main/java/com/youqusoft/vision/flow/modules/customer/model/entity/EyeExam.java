package com.youqusoft.vision.flow.modules.customer.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youqusoft.vision.flow.common.base.BaseEntity;

/**
 * 验光记录实体对象
 *
 * @author youqusoft
 * @since 2025-10-01 11:31
 */
@Getter
@Setter
@TableName("eye_exam")
public class EyeExam extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 关联的顾客ID
     */
    private Long customerId;
    /**
     * 验光日期
     */
    private LocalDate examDate;
    /**
     * 验光师
     */
    private String optometrist;
    /**
     * 验光类型
     */
    private String examType;
    /**
     * 右眼球镜
     */
    private BigDecimal odSph;
    /**
     * 右眼柱镜
     */
    private BigDecimal odCyl;
    /**
     * 右眼轴位
     */
    private Integer odAxis;
    /**
     * 右眼瞳距
     */
    private BigDecimal odPd;
    /**
     * 左眼球镜
     */
    private BigDecimal osSph;
    /**
     * 左眼柱镜
     */
    private BigDecimal osCyl;
    /**
     * 左眼轴位
     */
    private Integer osAxis;
    /**
     * 左眼瞳距
     */
    private BigDecimal osPd;
    /**
     * 双眼瞳距
     */
    private BigDecimal pdTotal;
    /**
     * 下加光
     */
    private BigDecimal addition;
    /**
     * 带镜史数据
     */
    private String wearingHistory;
    /**
     * 是否有戴镜史
     */
    private Integer hasGlassesHistory;
    /**
     * 是否首次验光
     */
    private Integer isFirstExam;
    private Integer isDeleted;
}
