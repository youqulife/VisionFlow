package com.youqusoft.vision.flow.system.controller;

import com.youqusoft.vision.flow.common.cache.UnifiedCacheManager;
import com.youqusoft.vision.flow.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * 缓存测试控制器
 * 用于演示统一缓存服务的使用
 */
@Tag(name = "缓存测试", description = "缓存服务测试接口")
@RestController
@RequestMapping("/api/v1/cache-test")
@Slf4j
@RequiredArgsConstructor
public class CacheTestController {
    
    private final UnifiedCacheManager cacheService;
    
    @Operation(summary = "设置缓存值")
    @PostMapping("/put")
    public Result<?> put(
            @RequestParam String cacheName,
            @RequestParam String key,
            @RequestParam String value) {
        
        cacheService.put(cacheName, key, value);
        return Result.success("缓存设置成功");
    }
    
    @Operation(summary = "设置带过期时间的缓存值")
    @PostMapping("/put-with-ttl")
    public Result<?> putWithTtl(
            @RequestParam String cacheName,
            @RequestParam String key,
            @RequestParam String value,
            @RequestParam long ttl,
            @RequestParam TimeUnit timeUnit) {
        
        cacheService.putWithExpire(cacheName, key, value, ttl, timeUnit);
        return Result.success("带过期时间的缓存设置成功");
    }
    
    @Operation(summary = "获取缓存值")
    @GetMapping("/get")
    public Result<Object> get(
            @RequestParam String cacheName,
            @RequestParam String key) {
        
        Object value = cacheService.get(cacheName, key);
        return Result.success(value);
    }
    
    @Operation(summary = "删除缓存值")
    @DeleteMapping("/evict")
    public Result<?> evict(
            @RequestParam String cacheName,
            @RequestParam String key) {
        
        cacheService.evict(cacheName, key);
        return Result.success("缓存删除成功");
    }
    
    @Operation(summary = "清空缓存")
    @DeleteMapping("/clear")
    public Result<?> clear(@RequestParam String cacheName) {
        
        cacheService.clear(cacheName);
        return Result.success("缓存清空成功");
    }
}