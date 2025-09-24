package com.youqusoft.vision.flow.shared.auth.service;


import com.youqusoft.vision.flow.shared.auth.model.AuthTokenResponse;
import org.springframework.security.core.Authentication;

/**
 * 令牌接口
 *
 * @author Ray
 * @since 2.16.0
 */
public interface TokenService {

    /**
     * 生成认证 Token
     *
     * @param authentication 用户认证信息
     * @return 认证 Token 响应
     */
    AuthTokenResponse generateToken(Authentication authentication);

    /**
     * 解析 Token 获取认证信息
     *
     * @param token JWT Token
     * @return 用户认证信息
     */
    Authentication parseToken(String token);


    /**
     * 校验 Token 是否有效
     *
     * @param token JWT Token
     * @return 是否有效
     */
    boolean validateToken(String token);


    /**
     *  刷新 Token
     *
     * @param token 刷新令牌
     * @return 认证 Token 响应
     */
    AuthTokenResponse refreshToken(String token);

    /**
     * 将 Token 加入黑名单
     *
     * @param token JWT Token
     */
    default void blacklistToken(String token) {
        // 默认实现可以是空的，或者抛出不支持的操作异常
        // throw new UnsupportedOperationException("Not implemented");
    }



}
