package com.glass.user.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDTO implements Serializable {

    /**
     * 主键id
     */
    private String id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色说明
     */
    private String description;

    /**
     * 创建人
     */
    private String createdBy;

}
