package com.youqusoft.vision.flow.system.service.impl;

import com.youqusoft.vision.flow.common.cache.UnifiedCacheManager;
import com.youqusoft.vision.flow.system.model.entity.User;
import com.youqusoft.vision.flow.system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户缓存服务实现示例
 * 展示如何使用统一缓存服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserCacheServiceImpl {
    
    private final UnifiedCacheManager cacheManager;
    private final UserService userService;
    
    private static final String USER_CACHE_NAME = "users";
    
    /**
     * 获取用户信息（带缓存）
     * 
     * @param userId 用户ID
     * @return 用户信息
     */
    public User getUserById(Long userId) {
        String key = "user:id:" + userId;

        // 先检查缓存中是否存在
        if (cacheManager.hasKey(USER_CACHE_NAME, key)) {
            log.debug("从缓存获取用户信息: {}", userId);
            // 如果存在，直接获取
            return cacheManager.get(USER_CACHE_NAME, key);
        }
        
        // 缓存未命中，从数据库获取
        log.debug("缓存未命中，从数据库获取用户信息: {}", userId);
        User user = userService.getById(userId);
        
        // 放入缓存
        if (user != null) {
            cacheManager.put(USER_CACHE_NAME, key, user);
        }
        
        return user;
    }
    
    /**
     * 更新用户信息
     * 
     * @param user 用户信息
     */
    public void updateUser(User user) {
        // 更新数据库
        userService.updateById(user);
        
        // 更新缓存
        String key = "user:id:" + user.getId();
        cacheManager.put(USER_CACHE_NAME, key, user);
        
        log.debug("用户信息已更新，缓存已同步: {}", user.getId());
    }
    
    /**
     * 删除用户信息
     * 
     * @param userId 用户ID
     */
    public void deleteUser(Long userId) {
        // 删除数据库记录
        userService.removeById(userId);
        
        // 删除缓存
        String key = "user:id:" + userId;
        cacheManager.evict(USER_CACHE_NAME, key);
        
        log.debug("用户信息已删除，缓存已清理: {}", userId);
    }
    
    /**
     * 清空用户缓存
     */
    public void clearUserCache() {
        cacheManager.clear(USER_CACHE_NAME);
        log.debug("用户缓存已清空");
    }
}