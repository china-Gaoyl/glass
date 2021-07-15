package com.glass.user.security.filter;

import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.glass.common.base.SysUser;
import com.glass.user.model.entity.Role;
import com.glass.user.security.base.SecurityDict;
import com.glass.user.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import redis.clients.jedis.Jedis;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RoleService roleService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

         String token = request.getHeader(SecurityDict.TOKEN);

        if (StringUtils.isNotBlank(token)) {
            Jedis jedis = RedisDS.create().getJedis();
            String object = jedis.get(SecurityDict.TOKEN_PREFIX + token);
            if (StringUtils.isNotBlank(object)) {
                SysUser user = JSONUtil.toBean(object, SysUser.class);
                List<Role> roleList = roleService.selectListByUserId(user.getId());
                List<GrantedAuthority> authorities = new LinkedList<>();
                roleList.forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(SecurityDict.ROLE_PREFIX + role.getRoleName()));
                });
                user.setAuthorities(authorities);
                //更新token过期时间
                jedis.set(SecurityDict.TOKEN_PREFIX + token, JSON.toJSONString(user));
                jedis.expire(SecurityDict.TOKEN_PREFIX + token, SecurityDict.TOKEN_TIME);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, user.getId(), user.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request,response);
    }
}
