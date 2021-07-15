package com.glass.user.security.base;

/**
 * SpringSecurity 相关字典数据目录
 */
public class SecurityDict {

    /**
     * 权限集合key
     */
    public static final String RESOURCE_ROLE_KEY = "glass_spring_security_resource_role_key";

    /**
     * 角色前缀
     */
    public static final String ROLE_PREFIX = "ROLE_";

    /**
     * 默认角色
     */
    public static final String ROLE_DEF = "ROLE_游客";

    public static final String QUERY = "0";

    public static final String UPD = "1";

    /**
     * token
     */
    public static final String TOKEN = "User-Token";
    /**
     * token前缀
     */
    public static final String TOKEN_PREFIX = "glass_token_";

    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    /**
     * token失效时间
     */
    public static final Long TOKEN_TIME = 30 * 60L;

}
