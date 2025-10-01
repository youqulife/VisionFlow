package com.youqusoft.vision.flow.modules.customer.model.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * 顾客信息视图对象
 *
 * @author youqusoft
 * @since 2025-09-27 15:49
 */
@Getter
@Setter
@Schema( description = "顾客信息视图对象")
public class CustomerVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;
    @Schema(description = "顾客姓名")
    private String name;
    @Schema(description = "手机号码（可作为唯一标识）")
    private String phone;
    @Schema(description = "性别：male-男, female-女, unknown-未知")
    private String gender;
    @Schema(description = "生日（用于客户关怀和营销）")
    private LocalDate birthday;
    @Schema(description = "顾客来源：如'大众点评'、'朋友推荐'、'线下自然流量'等")
    private String source;
    @Schema(description = "顾客标签：JSON数组格式，如[`高价值客户`, `儿童`, `价格敏感`]")
    private String tags;
    @Schema(description = "会员等级：如`普通`,`银卡`,`金卡`,`钻石`")
    private String memberLevel;
    @Schema(description = "账户余额（储值金额）")
    private BigDecimal balance;
    @Schema(description = "备注信息：顾客偏好、特殊需求、病史等")
    private String notes;
    @Schema(description = "软删除标记：0-未删除, 1-已删除")
    private Integer isDeleted;
    @Schema(description = "记录创建时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime createTime;
    @Schema(description = "记录最后更新时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime updateTime;
}
