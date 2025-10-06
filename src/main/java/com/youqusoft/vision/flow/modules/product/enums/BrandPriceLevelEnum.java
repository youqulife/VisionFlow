package com.youqusoft.vision.flow.modules.product.enums;

import com.youqusoft.vision.flow.common.base.IBaseEnum;
import lombok.Getter;

/**
 * 品牌价格定位枚举
 *
 * @author youqusoft
 * @since 2025-10-05
 */
@Getter
public enum BrandPriceLevelEnum implements IBaseEnum<String> {

    /**
     * 高端 premium
     */
    PREMIUM("premium", "高端"),

    /**
     * 中高端 mid_high
     */
    MID_HIGH("mid_high", "中高端"),

    /**
     * 中端 mid
     */
    MID("mid", "中端"),

    /**
     * 平价 affordable
     */
    AFFORDABLE("affordable", "平价");

    private final String value;

    private final String label;

    BrandPriceLevelEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }
}