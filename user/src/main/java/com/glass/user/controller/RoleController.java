package com.glass.user.controller;


import com.glass.common.base.BaseController;
import com.glass.common.base.ResponseResult;
import com.glass.user.model.dto.ResourcesDTO;
import com.glass.user.model.dto.RoleDTO;
import com.glass.user.service.ResourcesService;
import com.glass.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author gaoyl
 * @since 2021-06-10
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {


    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourcesService resourcesService;

    /**
     * 角色-》新增
     *
     * @param dto
     * @return
     */
    @PostMapping("/role/add")
    public ResponseResult addRole(@RequestBody RoleDTO dto) {
        roleService.addRole(dto);
        return success();
    }

    /**
     * 角色-》删除
     *
     * @param dto
     * @return
     */
    @PostMapping("/role/delete")
    public ResponseResult deleteRole(@RequestBody RoleDTO dto) {
        roleService.deleteRole(dto);
        return success();
    }

    /**
     * 资源-》新增
     *
     * @param dto
     */
    @PostMapping("/resources/add")
    public ResponseResult addResources(@RequestBody ResourcesDTO dto) {
        resourcesService.addResources(dto);
        return success();
    }

}
