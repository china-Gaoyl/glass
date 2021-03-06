package com.glass.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author gaoyl
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Resources implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId("ID")
    private String id;

    /**
     * 父级id
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * 资源名
     */
    @TableField("RESOURCE_NAME")
    private String resourceName;

    /**
     * 资源类型（PACKAGE：包，ITEM：单项，SUBMENU：按钮）
     */
    @TableField("TYPE")
    private String type;

    /**
     * 权限
     */
    @TableField("AUTHORITY")
    private String authority;

    /**
     * 资源说明
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 资源路径
     */
    @TableField("URL")
    private String url;

    /**
     * 排序
     */
    @TableField("SORT")
    private String sort;

    /**
     * 创建时间
     */
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    /**
     * 创建人
     */
    @TableField("CREATED_BY")
    private String createdBy;

    /**
     * 修改时间
     */
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    /**
     * 修改人
     */
    @TableField("UPDATED_BY")
    private String updatedBy;

    /**
     * 删除标识（0:有效 1:删除）
     */
    @TableField("DELETE_FLAG")
    private String deleteFlag;


}
