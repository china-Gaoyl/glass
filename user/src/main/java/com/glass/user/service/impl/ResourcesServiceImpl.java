package com.glass.user.service.impl;

import com.glass.user.entity.Resources;
import com.glass.user.mapper.ResourcesMapper;
import com.glass.user.service.ResourcesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author gaoyl
 * @since 2021-06-10
 */
@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements ResourcesService {

}
