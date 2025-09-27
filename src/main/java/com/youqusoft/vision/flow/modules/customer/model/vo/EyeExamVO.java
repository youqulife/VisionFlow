package com.youqusoft.vision.flow.modules.customer.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 验光记录VO
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Data
@Schema(description = "验光记录")
public class EyeExamVO {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 关联的顾客ID
     */
    @Schema(description = "关联的顾客ID")
    private Long customerId;

    /**
     * 验光日期
     */
    @Schema(description = "验光日期")
    private LocalDate examDate;

    /**
     * 验光师姓名
     */
    @Schema(description = "验光师姓名")
    private String optometrist;

    /**
     * 右眼球镜度数
     */
    @Schema(description = "右眼球镜度数")
    private Double odSph;

    /**
     * 右眼柱镜度数
     */
    @Schema(description = "右眼柱镜度数")
    private Double odCyl;

    /**
     * 右眼轴位
     */
    @Schema(description = "右眼轴位")
    private Integer odAxis;

    /**
     * 右眼单眼瞳距
     */
    @Schema(description = "右眼单眼瞳距")
    private Double odPd;

    /**
     * 左眼球镜度数
     */
    @Schema(description = "左眼球镜度数")
    private Double osSph;

    /**
     * 左眼柱镜度数
     */
    @Schema(description = "左眼柱镜度数")
    private Double osCyl;

    /**
     * 左眼轴位
     */
    @Schema(description = "左眼轴位")
    private Integer osAxis;

    /**
     * 左眼单眼瞳距
     */
    @Schema(description = "左眼单眼瞳距")
    private Double osPd;

    /**
     * 双眼瞳距
     */
    @Schema(description = "双眼瞳距")
    private Double pdTotal;

    /**
     * 下加光
     */
    @Schema(description = "下加光")
    private Double add;

    /**
     * 验光备注
     */
    @Schema(description = "验光备注")
    private String notes;

    /**
     * 记录创建时间
     */
    @Schema(description = "记录创建时间")
    private LocalDateTime createdAt;

    /**
     * 记录最后更新时间
     */
    @Schema(description = "记录最后更新时间")
    private LocalDateTime updatedAt;
}