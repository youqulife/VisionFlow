package com.youqusoft.vision.flow.modules.customer.model.query;

import com.youqusoft.vision.flow.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 顾客查询对象
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Data
@Schema(description = "顾客查询对象")
public class CustomerQuery extends BasePageQuery {

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
     * 会员等级
     */
    @Schema(description = "会员等级")
    private String memberLevel;
}