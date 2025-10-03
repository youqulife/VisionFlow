package com.youqusoft.vision.flow.common.cache;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 统一缓存管理器接口
 * 对外提供统一的缓存操作，内部自动选择Caffeine或Redis实现
 *
 * @author System
 * @since 2025-02-20
 */
public interface UnifiedCacheManager {

    /**
     * 获取缓存值
     *
     * @param cacheName 缓存名称
     * @param key       缓存键
     * @return 缓存值，如果不存在返回null
     */
    <T> T get(String cacheName, Object key);

    /**
     * 获取缓存值，如果不存在则通过valueLoader加载
     *
     * @param cacheName   缓存名称
     * @param key         缓存键
     * @param valueLoader 值加载器
     * @return 缓存值
     */
    <T> T get(String cacheName, Object key, Callable<T> valueLoader);

    /**
     * 放入缓存
     *
     * @param cacheName 缓存名称
     * @param key       缓存键
     * @param value     缓存值
     */
    void put(String cacheName, Object key, Object value);

    /**
     * 放入缓存并设置过期时间
     *
     * @param cacheName 缓存名称
     * @param key       缓存键
     * @param value     缓存值
     * @param timeout   过期时间
     * @param timeUnit  时间单位
     */
    void putWithExpire(String cacheName, Object key, Object value, long timeout, TimeUnit timeUnit);

    /**
     * 设置缓存过期时间
     *
     * @param cacheName 缓存名称
     * @param key       缓存键
     * @param timeout   过期时间
     * @param timeUnit  时间单位
     * @return 是否设置成功
     */
    boolean setExpire(String cacheName, Object key, long timeout, TimeUnit timeUnit);

    /**
     * 删除缓存
     *
     * @param cacheName 缓存名称
     * @param key       缓存键
     */
    void evict(String cacheName, Object key);

    /**
     * 清空指定缓存的所有数据
     *
     * @param cacheName 缓存名称
     */
    void clear(String cacheName);

    /**
     * 获取所有缓存名称
     *
     * @return 缓存名称集合
     */
    Collection<String> getCacheNames();

    /**
     * 获取当前使用的缓存类型
     *
     * @return 缓存类型 (caffeine/redis)
     */
    String getCacheType();

    /**
     * 获取剩余过期时间
     *
     * @param cacheName 缓存名称
     * @param key       缓存键
     * @param timeUnit  时间单位
     * @return 剩余过期时间，-1表示永不过期，-2表示key不存在
     */
    long getExpire(String cacheName, Object key, TimeUnit timeUnit);

    /**
     * 检查缓存中是否存在指定键
     *
     * @param cacheName 缓存名称
     * @param key       缓存键
     * @return 是否存在指定键
     */
    boolean hasKey(String cacheName, Object key);
}