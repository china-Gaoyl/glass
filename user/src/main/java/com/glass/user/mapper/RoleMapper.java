package com.glass.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glass.common.base.ResourceVO;
import com.glass.user.model.entity.Role;
import com.glass.user.security.base.Menu;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author gaoyl
 * @since 2021-06-10
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectListByUserId(String id);

    List<ResourceVO> selectResourceListByUserId(String id);

    List<Menu> selectMenuList();

}
