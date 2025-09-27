package com.youqusoft.vision.flow.modules.customer.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * 顾客表单对象
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Data
@Schema(description = "顾客表单对象")
public class CustomerForm {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 租户ID
     */
    @Schema(description = "租户ID")
    private Long tenantId;

    /**
     * 顾客姓名
     */
    @Schema(description = "顾客姓名")
    private String name;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String phone;

    /**
     * 性别：male-男, female-女, unknown-未知
     */
    @Schema(description = "性别")
    private String gender;

    /**
     * 生日
     */
    @Schema(description = "生日")
    private LocalDate birthday;

    /**
     * 顾客来源
     */
    @Schema(description = "顾客来源")
    private String source;

    /**
     * 顾客标签
     */
    @Schema(description = "顾客标签")
    private String tags;

    /**
     * 会员等级
     */
    @Schema(description = "会员等级")
    private String memberLevel;

    /**
     * 账户余额
     */
    @Schema(description = "账户余额")
    private Double balance;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String notes;
}