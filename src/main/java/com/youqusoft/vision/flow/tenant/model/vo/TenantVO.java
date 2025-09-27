package com.youqusoft.vision.flow.tenant.model.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 租户信息视图对象
 *
 * @author youqulife
 * @since 2025-09-26 22:24
 */
@Getter
@Setter
@Schema( description = "租户信息视图对象")
public class TenantVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "租户ID")
    private Long id;
    @Schema(description = "租户编码：唯一标识，可用于子域名")
    private String tenantCode;
    @Schema(description = "租户名称（店铺名称）")
    private String name;
    @Schema(description = "联系人姓名")
    private String contactPerson;
    @Schema(description = "联系电话")
    private String contactPhone;
    @Schema(description = "联系邮箱")
    private String contactEmail;
    @Schema(description = "店铺地址")
    private String address;
    @Schema(description = "行业类型：optical-眼镜店, hair-美发店等")
    private String industryType;
    @Schema(description = "订阅计划")
    private String subscriptionPlan;
    @Schema(description = "订阅状态")
    private String subscriptionStatus;
    @Schema(description = "订阅到期日期")
    private LocalDate expiresAt;
    @Schema(description = "最大用户数")
    private Integer maxUsers;
    @Schema(description = "是否激活")
    private Integer isActive;
    @Schema(description = "创建时间")
    private String createdAt;
    @Schema(description = "更新时间")
    private String updatedAt;
}
