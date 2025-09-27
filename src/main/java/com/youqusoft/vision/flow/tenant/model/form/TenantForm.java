package com.youqusoft.vision.flow.tenant.model.form;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

/**
 * 租户信息表单对象
 *
 * @author youqulife
 * @since 2025-09-26 22:24
 */
@Getter
@Setter
@Schema(description = "租户信息表单对象")
public class TenantForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "租户编码：唯一标识，可用于子域名")
    @NotBlank(message = "租户编码：唯一标识，可用于子域名不能为空")
    @Size(max=50, message="租户编码：唯一标识，可用于子域名长度不能超过50个字符")
    private String tenantCode;

    @Schema(description = "租户名称（店铺名称）")
    @NotBlank(message = "租户名称（店铺名称）不能为空")
    @Size(max=100, message="租户名称（店铺名称）长度不能超过100个字符")
    private String name;

    @Schema(description = "联系人姓名")
    @Size(max=50, message="联系人姓名长度不能超过50个字符")
    private String contactPerson;

    @Schema(description = "联系电话")
    @Size(max=20, message="联系电话长度不能超过20个字符")
    private String contactPhone;

    @Schema(description = "联系邮箱")
    @Size(max=100, message="联系邮箱长度不能超过100个字符")
    private String contactEmail;

    @Schema(description = "店铺地址")
    @Size(max=512, message="店铺地址长度不能超过512个字符")
    private String address;

    @Schema(description = "行业类型：optical-眼镜店, hair-美发店等")
    @Size(max=50, message="行业类型：optical-眼镜店, hair-美发店等长度不能超过50个字符")
    private String industryType;

    @Schema(description = "订阅计划")
    @NotNull(message = "订阅计划不能为空")
    @Size(max=12, message="订阅计划长度不能超过12个字符")
    private String subscriptionPlan;

    @Schema(description = "订阅状态")
    @NotNull(message = "订阅状态不能为空")
    @Size(max=9, message="订阅状态长度不能超过9个字符")
    private String subscriptionStatus;

    @Schema(description = "订阅到期日期")
    private LocalDate expiresAt;

    @Schema(description = "最大用户数")
    @NotNull(message = "最大用户数不能为空")
    private Integer maxUsers;

    @Schema(description = "是否激活")
    @NotNull(message = "是否激活不能为空")
    private Integer isActive;


}
