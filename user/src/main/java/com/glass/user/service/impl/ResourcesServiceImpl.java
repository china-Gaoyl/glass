package com.glass.user.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glass.user.model.entity.Resources;
import com.glass.user.mapper.ResourcesMapper;
import com.glass.user.model.dto.ResourcesDTO;
import com.glass.user.service.ResourcesService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author gaoyl
 * @since 2021-06-10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements ResourcesService {

    /**
     * 资源-》新增
     *
     * @param dto
     */
    @Override
    public void addResources(ResourcesDTO dto) {

        String uuid = IdUtil.simpleUUID();
        Resources ent = new Resources();
        BeanUtils.copyProperties(dto, ent);
        ent.setId(uuid);
        super.save(ent);
    }
}
