package com.glass.user.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResourcesDTO implements Serializable {

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 资源名
     */
    private String resourceName;

    /**
     * 资源类型（PACKAGE：包，ITEM：单项，SUBMENU：按钮）
     */
    private String type;

    /**
     * 权限
     */
    private String authority;

    /**
     * 资源说明
     */
    private String description;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 排序
     */
    private String sort;

    /**
     * 创建人
     */
    private String createdBy;

}
