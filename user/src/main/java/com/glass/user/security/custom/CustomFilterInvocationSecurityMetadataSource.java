package com.glass.user.security.custom;

import com.glass.user.security.base.Menu;
import com.glass.user.security.base.SecurityDict;
import com.glass.user.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private RoleService roleService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        FilterInvocation fi = (FilterInvocation) o;
        if (isMatcherWhiteList(fi)) {
            return null;
        }
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Menu> menuList = roleService.selectMenuList(SecurityDict.QUERY);
        for (Menu menu : menuList) {
            if (pathMatcher.match(menu.getPattern(), requestUrl)) {
                String[] roleArr = menu.getRoles().stream().map(r -> SecurityDict.ROLE_PREFIX + r.getRoleName()).toArray(String[]::new);
                return SecurityConfig.createList(roleArr);
            }
        }
        return SecurityConfig.createList(SecurityDict.ROLE_DEF);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * 判断当前请求是否在不需要认证的路径中
     *
     * @param fi
     * @return
     */
    private boolean isMatcherWhiteList(FilterInvocation fi) {
        return whiteList().stream().map(AntPathRequestMatcher::new)
                .filter(pathMatcher -> pathMatcher.matches(fi.getHttpRequest()))
                .toArray().length > 0;
    }

    /**
     * 不需要认证的路径
     *
     * @return
     */
    private List<String> whiteList() {
        return Arrays.asList(
                "/login",
                "/logout"
        );
    }
}
