package com.nameless.bbs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nameless.bbs.entity.Permission;
import com.nameless.bbs.mapper.PermissionMapper;
import com.nameless.bbs.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cindy
 * @since 2019-03-29
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
