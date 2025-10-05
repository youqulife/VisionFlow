package com.youqusoft.vision.flow.system.controller;

import com.youqusoft.vision.flow.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查控制器
 *
 * @author Lingma
 * @since 2025-10-03
 */
@Tag(name = "11.健康检查接口")
@RestController
public class HealthController {

    @Operation(summary = "健康检查")
    @GetMapping("/health")
    public Result<String> health() {
        return Result.success("服务运行正常");
    }
}