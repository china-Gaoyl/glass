package com.glass.user.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glass.user.model.entity.Role;
import com.glass.user.mapper.RoleMapper;
import com.glass.common.base.ResourceVO;
import com.glass.user.model.dto.RoleDTO;
import com.glass.user.security.base.Menu;
import com.glass.user.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author gaoyl
 * @since 2021-06-10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectListByUserId(String id) {
        return roleMapper.selectListByUserId(id);
    }

    @Override
    public List<ResourceVO> selectResourceListByUserId(String id) {
       return roleMapper.selectResourceListByUserId(id);
    }

    @Override
    public void addRole(RoleDTO dto) {
        Role ent = new Role();
        BeanUtils.copyProperties(dto, ent);
        ent.setId(IdUtil.simpleUUID());
        super.save(ent);
    }

    @Override
    public void deleteRole(RoleDTO dto) {
        super.removeById(dto.getId());
    }

    @Override
    public List<Menu> selectMenuList(String query) {
        return roleMapper.selectMenuList();
    }
}
