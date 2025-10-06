package com.youqusoft.vision.flow.modules.product.enums;

import com.youqusoft.vision.flow.common.base.IBaseEnum;
import lombok.Getter;

/**
 * 品牌类别枚举
 *
 * @author youqusoft
 * @since 2025-10-05
 */
@Getter
public enum BrandCategoryEnum implements IBaseEnum<String> {

    /**
     * 奢侈品品牌
     */
    LUXURY("luxury", "奢侈品"),

    /**
     * 时尚品牌
     */
    FASHION("fashion", "时尚"),

    /**
     * 运动品牌
     */
    SPORTS("sports", "运动"),

    /**
     * 光学专业品牌
     */
    OPTICAL("optical", "光学"),

    /**
     * 儿童品牌
     */
    KIDS("kids", "儿童"),

    /**
     * 本土品牌
     */
    LOCAL("local", "本土");

    private final String value;

    private final String label;

    BrandCategoryEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }
}