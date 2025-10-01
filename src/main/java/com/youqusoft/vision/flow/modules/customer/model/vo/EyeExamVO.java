package com.youqusoft.vision.flow.modules.customer.model.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * 验光记录视图对象
 *
 * @author youqusoft
 * @since 2025-10-01 11:31
 */
@Getter
@Setter
@Schema( description = "验光记录视图对象")
public class EyeExamVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;
    @Schema(description = "租户ID")
    private Long customerId;
    @Schema(description = "顾客名称")
    private String customerName;
    @Schema(description = "顾客手机号")
    private String customerPhone;
    @Schema(description = "验光日期")
    private LocalDate examDate;
    @Schema(description = "验光师")
    private String optometrist;
    @Schema(description = "验光类型")
    private String examType;
    @Schema(description = "右眼球镜")
    private BigDecimal odSph;
    @Schema(description = "右眼柱镜")
    private BigDecimal odCyl;
    @Schema(description = "右眼轴位")
    private Integer odAxis;
    @Schema(description = "右眼瞳距")
    private BigDecimal odPd;
    @Schema(description = "左眼球镜")
    private BigDecimal osSph;
    @Schema(description = "左眼柱镜")
    private BigDecimal osCyl;
    @Schema(description = "左眼轴位")
    private Integer osAxis;
    @Schema(description = "左眼瞳距")
    private BigDecimal osPd;
    @Schema(description = "双眼瞳距")
    private BigDecimal pdTotal;
    @Schema(description = "下加光")
    private BigDecimal addition;
    @Schema(description = "带镜史数据")
    private String wearingHistory;
    @Schema(description = "是否有戴镜史")
    private Integer hasGlassesHistory;
    @Schema(description = "是否首次验光")
    private Integer isFirstExam;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
