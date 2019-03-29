package com.nameless.bbs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nameless.bbs.entity.AdminUser;
import com.nameless.bbs.mapper.AdminUserMapper;
import com.nameless.bbs.service.AdminUserService;
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
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

}
