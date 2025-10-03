package com.youqusoft.vision.flow.common.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 统一缓存管理器实现
 * 自动根据配置选择底层缓存实现
 *
 * @author System
 * @since 2025-02-20
 */
@Slf4j
@Primary
@Component
public class DefaultUnifiedCacheManager implements UnifiedCacheManager {

    private final CacheManager cacheManager;
    private final String cacheType;
    private RedisTemplate<String, Object> redisTemplate; // 用于Redis特定操作

    @Autowired(required = false)
    public DefaultUnifiedCacheManager(CacheManager cacheManager, RedisTemplate<String, Object> redisTemplate) {
        this.cacheManager = cacheManager;
        this.redisTemplate = redisTemplate;
        this.cacheType = determineCacheType(cacheManager);
        log.info("统一缓存管理器初始化完成，当前缓存类型: {}", this.cacheType);
    }

    @Autowired(required = false)
    public DefaultUnifiedCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        this.cacheType = determineCacheType(cacheManager);
        log.info("统一缓存管理器初始化完成，当前缓存类型: {}", this.cacheType);
    }

    @Override
    public <T> T get(String cacheName, Object key) {
        Cache cache = getCache(cacheName);
        Cache.ValueWrapper valueWrapper = cache.get(key);
        return valueWrapper != null ? (T) valueWrapper.get() : null;
    }

    @Override
    public <T> T get(String cacheName, Object key, Callable<T> valueLoader) {
        Cache cache = getCache(cacheName);
        return cache.get(key, valueLoader);
    }

    @Override
    public void put(String cacheName, Object key, Object value) {
        Cache cache = getCache(cacheName);
        cache.put(key, value);
    }

    @Override
    public void putWithExpire(String cacheName, Object key, Object value, long timeout, TimeUnit timeUnit) {
        if ("redis".equals(cacheType)) {
            // Redis支持单独设置过期时间
            putWithRedisExpire(cacheName, key, value, timeout, timeUnit);
        } else {
            // Caffeine不支持动态设置单个key的过期时间，使用默认配置
            log.warn("Caffeine缓存不支持单个key的过期时间设置，使用默认过期时间");
            put(cacheName, key, value);
        }
    }

    @Override
    public boolean setExpire(String cacheName, Object key, long timeout, TimeUnit timeUnit) {
        if ("redis".equals(cacheType)) {
            return setRedisExpire(cacheName, key, timeout, timeUnit);
        } else {
            log.warn("Caffeine缓存不支持动态设置单个key的过期时间");
            return false;
        }
    }

    @Override
    public void evict(String cacheName, Object key) {
        Cache cache = getCache(cacheName);
        cache.evict(key);
    }

    @Override
    public void clear(String cacheName) {
        Cache cache = getCache(cacheName);
        cache.clear();
    }

    @Override
    public Collection<String> getCacheNames() {
        return cacheManager.getCacheNames();
    }

    @Override
    public String getCacheType() {
        return cacheType;
    }

    @Override
    public long getExpire(String cacheName, Object key, TimeUnit timeUnit) {
        if ("redis".equals(cacheType) && redisTemplate != null) {
            return getRedisExpire(cacheName, key, timeUnit);
        } else {
            log.warn("Caffeine缓存不支持获取单个key的剩余过期时间");
            return -1; // 返回永不过期
        }
    }

    @Override
    public boolean hasKey(String cacheName, Object key) {
        if ("redis".equals(cacheType) && redisTemplate != null) {
            return hasRedisKey(cacheName, key);
        } else {
            // Caffeine或其他缓存实现
            Cache cache = getCache(cacheName);
            Cache.ValueWrapper wrapper = cache.get(key);
            return wrapper != null;
        }
    }

    /**
     * Redis特定操作 - 设置过期时间
     */
    private boolean setRedisExpire(String cacheName, Object key, long timeout, TimeUnit timeUnit) {
        try {
            String redisKey = generateRedisKey(cacheName, key);
            Boolean result = redisTemplate.expire(redisKey, timeout, timeUnit);
            return result != null && result;
        } catch (Exception e) {
            log.error("设置Redis缓存过期时间失败 cacheName: {}, key: {}", cacheName, key, e);
            return false;
        }
    }

    /**
     * Redis特定操作 - 带过期时间的put操作
     */
    private void putWithRedisExpire(String cacheName, Object key, Object value, long timeout, TimeUnit timeUnit) {
        try {
            String redisKey = generateRedisKey(cacheName, key);
            redisTemplate.opsForValue().set(redisKey, value, timeout, timeUnit);
        } catch (Exception e) {
            log.error("Redis带过期时间的put操作失败 cacheName: {}, key: {}", cacheName, key, e);
            // 降级为普通put操作
            put(cacheName, key, value);
        }
    }

    /**
     * Redis特定操作 - 获取剩余过期时间
     */
    private long getRedisExpire(String cacheName, Object key, TimeUnit timeUnit) {
        try {
            String redisKey = generateRedisKey(cacheName, key);
            Long expire = redisTemplate.getExpire(redisKey, timeUnit);
            return expire != null ? expire : -2; // -2表示key不存在
        } catch (Exception e) {
            log.error("获取Redis缓存剩余过期时间失败 cacheName: {}, key: {}", cacheName, key, e);
            return -2;
        }
    }

    /**
     * Redis特定操作 - 检查key是否存在
     */
    private boolean hasRedisKey(String cacheName, Object key) {
        try {
            String redisKey = generateRedisKey(cacheName, key);
            Boolean result = redisTemplate.hasKey(redisKey);
            return result != null && result;
        } catch (Exception e) {
            log.error("检查Redis缓存key是否存在失败 cacheName: {}, key: {}", cacheName, key, e);
            // 出错时降级为标准检查方式
            Cache cache = getCache(cacheName);
            Cache.ValueWrapper wrapper = cache.get(key);
            return wrapper != null;
        }
    }

    /**
     * 生成Redis存储的key
     */
    private String generateRedisKey(String cacheName, Object key) {
        // RedisCacheManager默认的key生成规则
        return cacheName + "::" + String.valueOf(key);
    }

    /**
     * 获取缓存实例，如果不存在则创建
     */
    private Cache getCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            throw new IllegalArgumentException("缓存 '" + cacheName + "' 不存在");
        }
        return cache;
    }

    /**
     * 根据CacheManager类型判断缓存类型
     */
    private String determineCacheType(CacheManager cacheManager) {
        String className = cacheManager.getClass().getName();
        if (className.contains("CaffeineCacheManager")) {
            return "caffeine";
        } else if (className.contains("RedisCacheManager")) {
            return "redis";
        } else {
            return "unknown";
        }
    }
}