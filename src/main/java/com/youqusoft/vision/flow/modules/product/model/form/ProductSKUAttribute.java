package com.youqusoft.vision.flow.modules.product.model.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductSKUAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 颜色属性
     */
    private Color color;

    /**
     * 尺寸属性
     */
    private Size size;

    /**
     * 材料属性
     */
    private String material;

    /**
     * 具体属性
     */
    private SpecificAttributes specificAttributes;

    /**
     * 将对象转换为JSON字符串
     *
     * @return JSON字符串表示
     */
    public String toJsonString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            // 发生异常时返回null
            return null;
        }
    }

    /**
     * 从JSON字符串创建对象
     *
     * @param jsonString JSON字符串
     * @return ProductSKUAttribute对象
     */
    public static ProductSKUAttribute fromJsonString(String jsonString) {
        if (jsonString == null || jsonString.isEmpty()) {
            return null;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, ProductSKUAttribute.class);
        } catch (Exception e) {
            // 解析失败时返回null
            return null;
        }
    }

    /**
     * 颜色内部类
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Color implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 颜色代码
         */
        private String code;

        /**
         * 颜色名称
         */
        private String name;
    }

    /**
     * 尺寸内部类
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Size implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 镜片宽度
         */
        private Integer lensWidth;

        /**
         * 桥梁宽度
         */
        private Integer bridgeWidth;

        /**
         * 镜腿长度
         */
        private Integer templeLength;

        /**
         * 显示信息
         */
        private String display;
    }

    /**
     * 具体属性内部类
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SpecificAttributes implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 重量
         */
        private Double weight;

        /**
         * 是否有库存
         */
        private Boolean inStock;
    }
}