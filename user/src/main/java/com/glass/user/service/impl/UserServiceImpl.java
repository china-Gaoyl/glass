package com.glass.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glass.user.entity.User;
import com.glass.user.mapper.UserMapper;
import com.glass.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author gaoyl
 * @since 2021-06-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
