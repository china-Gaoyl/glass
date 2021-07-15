package com.glass.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author gaoyl
 * @since 2021-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ROLE")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId("ID")
    private String id;

    /**
     * 角色名
     */
    @TableField("ROLE_NAME")
    private String roleName;

    /**
     * 角色说明
     */
    @TableField("DESCRIPTION")
    private String description;

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
