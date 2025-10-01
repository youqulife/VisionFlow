package com.youqusoft.vision.flow.modules.customer.model.form;

import java.io.Serial;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.*;

/**
 * 顾客信息表单对象
 *
 * @author youqusoft
 * @since 2025-09-27 15:49
 */
@Getter
@Setter
@Schema(description = "顾客信息表单对象")
public class CustomerForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "顾客姓名")
    @NotBlank(message = "顾客姓名不能为空")
    @Size(max=100, message="顾客姓名长度不能超过100个字符")
    private String name;

    @Schema(description = "手机号码（可作为唯一标识）")
    @NotBlank(message = "手机号码（可作为唯一标识）不能为空")
    @Size(max=20, message="手机号码（可作为唯一标识）长度不能超过20个字符")
    private String phone;

    @Schema(description = "性别：male-男, female-女, unknown-未知")
    @NotNull(message = "性别：male-男, female-女, unknown-未知不能为空")
    @Size(max=7, message="性别：male-男, female-女, unknown-未知长度不能超过7个字符")
    private String gender;

    @Schema(description = "生日（用于客户关怀和营销）")
    private LocalDate birthday;

    @Schema(description = "顾客来源：如'大众点评'、'朋友推荐'、'线下自然流量'等")
    @Size(max=50, message="顾客来源：如'大众点评'、'朋友推荐'、'线下自然流量'等长度不能超过50个字符")
    private String source;

    @Schema(description = "备注信息：顾客偏好、特殊需求、病史等")
    @Size(max=512, message="备注信息：顾客偏好、特殊需求、病史等长度不能超过512个字符")
    private String notes;

}
