package com.glass.user.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.glass.common.base.SysUser;
import com.glass.user.model.entity.Role;
import com.glass.user.model.entity.User;
import com.glass.user.security.base.SecurityDict;
import com.glass.user.service.RoleService;
import com.glass.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user == null) {
            throw new UsernameNotFoundException("Not found username:" + username);
        }
        List<Role> roles = roleService.selectListByUserId(user.getId());
        List<GrantedAuthority> authorities = new LinkedList<>();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(SecurityDict.ROLE_PREFIX + role.getRoleName()));
        });
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(user,sysUser);
        sysUser.setAuthorities(authorities);
        sysUser.setResourceList(roleService.selectResourceListByUserId(user.getId()));
        return sysUser;
    }
}
