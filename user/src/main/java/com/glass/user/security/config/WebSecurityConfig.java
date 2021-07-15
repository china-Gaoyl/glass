package com.glass.user.security.config;

import cn.hutool.core.util.IdUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glass.common.base.ResponseCode;
import com.glass.common.base.ResponseResult;
import com.glass.common.base.SysUser;
import com.glass.user.security.base.SecurityDict;
import com.glass.user.security.custom.CustomAccessDecisionManager;
import com.glass.user.security.custom.CustomFilterInvocationSecurityMetadataSource;
import com.glass.user.security.filter.AuthenticationFilter;
import com.glass.user.security.filter.AuthenticationTokenFilter;
import com.glass.user.security.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;
    @Autowired
    private CustomAccessDecisionManager customAccessDecisionManager;
    @Autowired
    private AuthenticationTokenFilter authenticationTokenFilter;

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                //动态配置权限 查db
                .withObjectPostProcessor(
                        new ObjectPostProcessor<FilterSecurityInterceptor>() {
                            @Override
                            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                                o.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                                o.setAccessDecisionManager(customAccessDecisionManager);
                                return o;
                            }})

                //登陆
                .and().formLogin()
                .usernameParameter("username").passwordParameter("password").permitAll()

                //登出
                .and().logout()
                .permitAll().logoutSuccessHandler(logoutSuccessHandler())


                .and().exceptionHandling()
                //未登录
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        ResponseResult result = new ResponseResult();

                        result.setFailCode(ResponseCode.NOT_LOGIN_ERROR);
                        httpServletResponse.setContentType(SecurityDict.CONTENT_TYPE);
                        httpServletResponse.getWriter().write(JSON.toJSONString(result));
                    }
                })
                //没有权限
                .accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {
                    ResponseResult result = new ResponseResult();

                    result.setFailCode(ResponseCode.NO_PERMISSION);
                    httpServletResponse.setContentType(SecurityDict.CONTENT_TYPE);
                    httpServletResponse.getWriter().write(JSON.toJSONString(result));
                })
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;

        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 认证
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }


    @Bean
    public AuthenticationFilter authenticationFilter() throws Exception {
        AuthenticationFilter filter = new AuthenticationFilter();

        //登陆成功
        filter.setAuthenticationSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {

            SysUser user = (SysUser) authentication.getPrincipal();
            user.setPassword(null); //清除密码
            ResponseResult result = new ResponseResult();
            result.setResponseCode(ResponseCode.LOGIN_SUCCESS);
            result.setData(user);
            String token = IdUtil.simpleUUID();
            Jedis jedis = RedisDS.create().getJedis();
            jedis.set(SecurityDict.TOKEN_PREFIX + token, JSON.toJSONString(user));
            jedis.expire(SecurityDict.TOKEN_PREFIX + token, SecurityDict.TOKEN_TIME);

            httpServletResponse.setHeader(SecurityDict.TOKEN, token);
            httpServletResponse.setContentType(SecurityDict.CONTENT_TYPE);
            httpServletResponse.getWriter().write(JSON.toJSONString(result));
        });

        //登陆失败
        filter.setAuthenticationFailureHandler((httpServletRequest, httpServletResponse, e) -> {
            ResponseResult result = new ResponseResult();
            if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                result.setFailCode(ResponseCode.LOGIN_USERNAME_OR_PASSWORD_ERROR);
            } else if (e instanceof LockedException) {
                result.setFailCode(ResponseCode.LOGIN_LOCKED_ERROR);
            } else if (e instanceof CredentialsExpiredException) {
                result.setFailCode(ResponseCode.LOGIN_CREDENTIALS_EXPIRED_ERROR);
            } else if (e instanceof AccountExpiredException) {
                result.setFailCode(ResponseCode.LOGIN_ACCOUNT_EXPIRED_ERROR);
            } else if (e instanceof DisabledException) {
                result.setFailCode(ResponseCode.LOGIN_DISABLED_ERROR);
            } else {
                result.setFailCode(ResponseCode.LOGIN_USERNAME_OR_PASSWORD_ERROR);
            }
            httpServletResponse.setContentType(SecurityDict.CONTENT_TYPE);
            httpServletResponse.getWriter().write(JSON.toJSONString(result));
        });

        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            ResponseResult result = new ResponseResult();
            result.setResponseCode(ResponseCode.LOGOUT_SUCCESS);
            httpServletResponse.setContentType(SecurityDict.CONTENT_TYPE);
            httpServletResponse.getWriter().write(JSON.toJSONString(result));
        };
    }


}
