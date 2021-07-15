package com.glass.user.service;

import com.glass.user.model.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.glass.common.base.ResourceVO;
import com.glass.user.model.dto.RoleDTO;
import com.glass.user.security.base.Menu;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author gaoyl
 * @since 2021-06-10
 */
public interface RoleService extends IService<Role> {

    List<Role> selectListByUserId(String id);

    List<ResourceVO> selectResourceListByUserId(String id);

    void addRole(RoleDTO dto);

    void deleteRole(RoleDTO dto);

    List<Menu> selectMenuList(String query);
}
