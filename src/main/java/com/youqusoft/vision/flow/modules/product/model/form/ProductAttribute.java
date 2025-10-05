package com.youqusoft.vision.flow.modules.product.model.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductAttribute implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 产品类型
     */
    private String productType;
    
    /**
     * 材料类型列表
     */
    private List<String> materialTypes;
    
    /**
     * 可用颜色列表
     */
    private List<AvailableColor> availableColors;
    
    /**
     * 可用尺寸列表
     */
    private List<AvailableSize> availableSizes;
    
    /**
     * 镜框样式
     */
    private String frameStyle;
    
    /**
     * 镜框形状
     */
    private String frameShape;
    
    /**
     * 铰链类型
     */
    private String hingeType;
    
    /**
     * 重量
     */
    private Double weight;
    
    /**
     * 尺寸信息
     */
    private Dimensions dimensions;
    
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
     * @return ProductAttribute对象
     */
    public static ProductAttribute fromJsonString(String jsonString) {
        if (jsonString == null || jsonString.isEmpty()) {
            return null;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, ProductAttribute.class);
        } catch (Exception e) {
            // 解析失败时返回null
            return null;
        }
    }
    
    /**
     * 可用颜色内部类
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AvailableColor implements Serializable {
        
        private static final long serialVersionUID = 1L;
        
        /**
         * 颜色代码
         */
        private String code;
        
        /**
         * 颜色名称
         */
        private String name;
        
        /**
         * 颜色图片路径
         */
        private String image;
    }
    
    /**
     * 可用尺寸内部类
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AvailableSize implements Serializable {
        
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
    }
    
    /**
     * 尺寸信息内部类
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Dimensions implements Serializable {
        
        private static final long serialVersionUID = 1L;
        
        /**
         * 镜片高度
         */
        private Integer lensHeight;
        
        /**
         * 镜片宽度
         */
        private Integer lensWidth;
        
        /**
         * 桥梁尺寸
         */
        private Integer bridge;
        
        /**
         * 镜腿尺寸
         */
        private Integer temple;
    }
}