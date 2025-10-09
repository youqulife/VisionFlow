package com.youqusoft.vision.flow.modules.dashboard.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DashboardVO {

    @Schema(description = "客户统计")
    private CustomerStatsVO customerStats;

    @Schema(description = "验光统计")
    private EyeExamStatsVO eyeExamStats;

    @Schema(description = "订单统计")
    private OrderStatsVO orderStats;

    @Getter
    @Setter
    public static class CustomerStatsVO{
        @Schema(description = "新增客户数")
        private int newCustomerCount;
    }

    @Getter
    @Setter
    public static class EyeExamStatsVO{
        @Schema(description = "验光记录量")
        private int eyeExamCount;
    }

    @Getter
    @Setter
    public static class OrderStatsVO{
        @Schema(description = "订单量")
        private Long orderCount;
        @Schema(description = "订单金额")
        private BigDecimal orderAmount;
    }
}
