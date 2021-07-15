package com.glass.user.controller;


import com.glass.user.model.entity.User;
import com.glass.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author gaoyl
 * @since 2021-06-10
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("/selectObj")
    public List<User> selectObj(){
        return userService.list();
    }
}
