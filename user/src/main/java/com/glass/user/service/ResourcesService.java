package com.glass.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glass.user.model.entity.Resources;
import com.glass.user.model.dto.ResourcesDTO;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author gaoyl
 * @since 2021-06-10
 */
public interface ResourcesService extends IService<Resources> {

    /**
     * 资源-》新增
     *
     * @param dto
     */
    void addResources(ResourcesDTO dto);
}
