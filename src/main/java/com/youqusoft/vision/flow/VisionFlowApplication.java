package com.youqusoft.vision.flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * 应用启动类
 *
 * @author Ray.Hao
 * @since 0.0.1
 */
@SpringBootApplication
@ConfigurationPropertiesScan // 开启配置属性绑定
public class VisionFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisionFlowApplication.class, args);
    }

}
