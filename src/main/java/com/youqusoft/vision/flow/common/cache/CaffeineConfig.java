package com.youqusoft.vision.flow.common.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * caffeine缓存配置
 * 已通过 ConditionalCacheConfig 统一管理，此处仅保留类结构
 *
 * @author Theo
 * @since 2025-01-22 17:40:23
 */
// 添加条件注解，避免与新的条件化配置冲突
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "caffeine", matchIfMissing = false)
@Slf4j
@Configuration
public class CaffeineConfig {
    @Value("${spring.cache.caffeine.spec}")
    private String caffeineSpec;

    @Bean
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeineBuilder = Caffeine.from(caffeineSpec);
        cacheManager.setCaffeine(caffeineBuilder);
        return cacheManager;
    }
}