package com.youqusoft.vision.flow.system.model.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户会话DTO
 *
 * @author Ray.Hao
 * @since 3.0.0
 */
@Data
public class UserSessionDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户会话ID集合
     */
    private Set<String> sessionIds;

    /**
     * 最后活动时间
     */
    private long lastActiveTime;

    public UserSessionDTO(String username) {
        this.username = username;
        this.sessionIds = new HashSet<>();
        this.lastActiveTime = System.currentTimeMillis();
    }
} 
