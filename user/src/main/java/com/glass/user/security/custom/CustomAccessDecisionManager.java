package com.glass.user.security.custom;

import com.glass.common.base.ResponseCode;
import com.glass.user.security.base.SecurityDict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

        Boolean flag = true;

        for (ConfigAttribute configAttribute : collection) {
            if (SecurityDict.ROLE_DEF.equals(configAttribute.getAttribute())) {

                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException(ResponseCode.NO_PERMISSION.getMsg());
                } else {
                    log.info("其他类型用户，请配置相关资源信息");
                }
            }

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority auth : authorities) {
                if (auth.getAuthority().equals(configAttribute.getAttribute()))
                    flag = false;
            }
        }

        if (flag) throw new AccessDeniedException(ResponseCode.NO_PERMISSION.getMsg());
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
