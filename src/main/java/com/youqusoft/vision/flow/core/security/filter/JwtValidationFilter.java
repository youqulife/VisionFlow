package com.youqusoft.vision.flow.core.security.filter;

import cn.hutool.core.util.StrUtil;
import com.youqusoft.vision.flow.common.constant.SecurityConstants;
import com.youqusoft.vision.flow.common.result.ResultCode;
import com.youqusoft.vision.flow.common.util.ResponseUtils;
import com.youqusoft.vision.flow.shared.auth.service.impl.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT token 校验过滤器
 *
 * @author Ray Hao
 * @since 2023/9/13
 */
public class JwtValidationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;


    public JwtValidationFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }


    /**
     * 从请求中获取 JWT Token，校验 JWT Token 是否合法
     * <p>
     * 如果合法则将 Authentication 设置到 Spring Security Context 上下文中
     * 如果不合法则清空 Spring Security Context 上下文，并直接返回响应
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            if (StrUtil.isNotBlank(token) && token.startsWith(SecurityConstants.JWT_TOKEN_PREFIX)) {
                // 去除 Bearer 前缀
                token = token.substring(SecurityConstants.JWT_TOKEN_PREFIX.length());
                // 校验 JWT Token ，包括验签和是否过期
                boolean isValidate = jwtTokenService.validateToken(token);
                if (!isValidate) {
                    ResponseUtils.writeErrMsg(response, ResultCode.TOKEN_INVALID);
                    return;
                }
                // 将 Token 解析为 Authentication 对象，并设置到 Spring Security 上下文中
                Authentication authentication = jwtTokenService.parseToken(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            ResponseUtils.writeErrMsg(response, ResultCode.TOKEN_INVALID);
            return;
        }
        // Token有效或无Token时继续执行过滤链
        filterChain.doFilter(request, response);
    }
}
