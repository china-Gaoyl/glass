package com.glass.user.security.base;

import com.glass.user.model.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class Menu {

    /**
     * 资源id
     */
    private String id;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 请求路径
     */
    private String pattern;
    /**
     * 角色集合
     */
    private List<Role> roles;
}
