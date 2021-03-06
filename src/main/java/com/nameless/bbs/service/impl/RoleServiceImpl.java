package com.nameless.bbs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nameless.bbs.entity.Role;
import com.nameless.bbs.mapper.RoleMapper;
import com.nameless.bbs.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
