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
 * 用户表
 * </p>
 *
 * @author gaoyl
 * @since 2021-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId("ID")
    private String id;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 手机号
     */
    @TableField("MOBILE")
    private String mobile;

    /**
     * 邮箱
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 性别
     */
    @TableField("GENDER")
    private String gender;

    /**
     * 账号过期
     */
    @TableField("IS_ACCOUNT_NON_EXPIRED")
    private Boolean isAccountNonExpired;

    /**
     * 账号锁定
     */
    @TableField("IS_ACCOUNT_NON_LOCKED")
    private Boolean isAccountNonLocked;

    /**
     * 凭证过期
     */
    @TableField("IS_CREDENTIALS_NON_EXPIRED")
    private Boolean isCredentialsNonExpired;

    /**
     * 账号禁用
     */
    @TableField("IS_ENABLED")
    private Boolean isEnabled;

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
