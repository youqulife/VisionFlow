package com.youqusoft.vision.flow.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.util.ArrayUtil;
import com.youqusoft.vision.flow.config.property.SecurityProperties;
import com.youqusoft.vision.flow.core.filter.RateLimiterFilter;
import com.youqusoft.vision.flow.core.security.exception.MyAccessDeniedHandler;
import com.youqusoft.vision.flow.core.security.exception.MyAuthenticationEntryPoint;
import com.youqusoft.vision.flow.core.security.extension.sms.SmsAuthenticationProvider;
import com.youqusoft.vision.flow.core.security.extension.wx.WxMiniAppCodeAuthenticationProvider;
import com.youqusoft.vision.flow.core.security.extension.wx.WxMiniAppPhoneAuthenticationProvider;
import com.youqusoft.vision.flow.core.security.filter.CaptchaValidationFilter;
import com.youqusoft.vision.flow.core.security.filter.TokenAuthenticationFilter;
import com.youqusoft.vision.flow.core.security.token.TokenManager;
import com.youqusoft.vision.flow.core.security.service.SysUserDetailsService;
import com.youqusoft.vision.flow.system.service.ConfigService;
import com.youqusoft.vision.flow.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 配置类
 *
 * @author Ray.Hao
 * @since 2023/2/17
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final RedisTemplate<String, Object> redisTemplate;
    private final PasswordEncoder passwordEncoder;

    private final TokenManager tokenManager;
    private final WxMaService wxMaService;
    private final UserService userService;
    private final SysUserDetailsService userDetailsService;

    private final CodeGenerator codeGenerator;
    private final ConfigService configService;
    private final SecurityProperties securityProperties;

    /**
     * 配置安全过滤链 SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(requestMatcherRegistry -> {
                            // 配置无需登录即可访问的公开接口
                            String[] ignoreUrls = securityProperties.getIgnoreUrls();
                            if (ArrayUtil.isNotEmpty(ignoreUrls)) {
                                requestMatcherRegistry.requestMatchers(ignoreUrls).permitAll();
                            }
                            // 其他所有请求需登录后访问
                            requestMatcherRegistry.anyRequest().authenticated();
                        }
                )
                .exceptionHandling(configurer ->
                        configurer
                                .authenticationEntryPoint(new MyAuthenticationEntryPoint()) // 未认证异常处理器
                                .accessDeniedHandler(new MyAccessDeniedHandler()) // 无权限访问异常处理器
                )

                // 禁用默认的 Spring Security 特性，适用于前后端分离架构
                .sessionManagement(configurer ->
                        configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 无状态认证，不使用 Session
                )
                .csrf(AbstractHttpConfigurer::disable)      // 禁用 CSRF 防护，前后端分离无需此防护机制
                .formLogin(AbstractHttpConfigurer::disable) // 禁用默认的表单登录功能，前后端分离采用 Token 认证方式
                .httpBasic(AbstractHttpConfigurer::disable) // 禁用 HTTP Basic 认证，避免弹窗式登录
                // 禁用 X-Frame-Options 响应头，允许页面被嵌套到 iframe 中
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                // 限流过滤器
                .addFilterBefore(new RateLimiterFilter(redisTemplate, configService), UsernamePasswordAuthenticationFilter.class)
                // 验证码校验过滤器
                .addFilterBefore(new CaptchaValidationFilter(redisTemplate, codeGenerator), UsernamePasswordAuthenticationFilter.class)
                // 验证和解析过滤器
                .addFilterBefore(new TokenAuthenticationFilter(tokenManager), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * 配置Web安全自定义器，以忽略特定请求路径的安全性检查。
     * <p>
     * 该配置用于指定哪些请求路径不经过Spring Security过滤器链。通常用于静态资源文件。
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> {
            String[] unsecuredUrls = securityProperties.getUnsecuredUrls();
            if (ArrayUtil.isNotEmpty(unsecuredUrls)) {
                web.ignoring().requestMatchers(unsecuredUrls);
            }
        };
    }

    /**
     * 默认密码认证的 Provider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    /**
     * 微信小程序Code认证Provider
     */
    @Bean
    public WxMiniAppCodeAuthenticationProvider wxMiniAppCodeAuthenticationProvider() {
        return new WxMiniAppCodeAuthenticationProvider(userService, wxMaService);
    }

    /**
     * 微信小程序手机号认证Provider
     */
    @Bean
    public WxMiniAppPhoneAuthenticationProvider wxMiniAppPhoneAuthenticationProvider() {
        return new WxMiniAppPhoneAuthenticationProvider(userService, wxMaService);
    }

    /**
     * 短信验证码认证 Provider
     */
    @Bean
    public SmsAuthenticationProvider smsAuthenticationProvider() {
        return new SmsAuthenticationProvider(userService, redisTemplate);
    }

    /**
     * 认证管理器
     */
    @Bean
    public AuthenticationManager authenticationManager(
            DaoAuthenticationProvider daoAuthenticationProvider,
            WxMiniAppCodeAuthenticationProvider wxMiniAppCodeAuthenticationProvider,
            WxMiniAppPhoneAuthenticationProvider wxMiniAppPhoneAuthenticationProvider,
            SmsAuthenticationProvider smsAuthenticationProvider
    ) {
        return new ProviderManager(
                daoAuthenticationProvider,
                wxMiniAppCodeAuthenticationProvider,
                wxMiniAppPhoneAuthenticationProvider,
                smsAuthenticationProvider
        );
    }
}
