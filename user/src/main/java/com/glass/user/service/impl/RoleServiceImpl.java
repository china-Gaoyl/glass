package com.glass.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glass.user.entity.Role;
import com.glass.user.mapper.RoleMapper;
import com.glass.user.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author gaoyl
 * @since 2021-06-10
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
