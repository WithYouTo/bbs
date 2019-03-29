package com.nameless.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nameless.bbs.common.Constants;
import com.nameless.bbs.entity.User;
import com.nameless.bbs.exception.ServiceProcessException;
import com.nameless.bbs.mapper.UserMapper;
import com.nameless.bbs.service.RedisService;
import com.nameless.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cindy
 * @since 2019-03-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisService<User> redisService;

    private String REDIS_USER_KEY = Constants.REDIS_USER_KEY;

    private Integer REDIS_USER_TIME = Constants.REDIS_USER_TIME;

    @Override
    public boolean checkUserName(String username) {
        User user = this.baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        return user != null ? true : false;
    }

    @Override
    public boolean checkUserEmail(String email) {
        User user = this.baseMapper.selectOne(new QueryWrapper<User>().eq("email", email));
        return user != null ? true : false;
    }

    @Override
    public User findByEmail(String email) {
        return this.baseMapper.selectOne(new QueryWrapper<User>().eq("email", email));
    }

    @Override
    public void createUser(String email, String username, String password) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        user.setInitTime(new Date());
        this.baseMapper.insert(user);
    }

    @Override
    public String LoginUser(User user) {
        String token = UUID.randomUUID().toString();
        redisService.cacheString(REDIS_USER_KEY + token, user, REDIS_USER_TIME);
        return token;
    }

    @Override
    public User getUserByToken(String token) {
        User user = redisService.getStringAndUpDate(REDIS_USER_KEY + token, REDIS_USER_TIME);
        return user;
    }

    @Override
    public void LogoutUser(String token) {
        User user = getUserByToken(token);
        redisService.deleteString(REDIS_USER_KEY + token);
    }

    @Override
    public void updateUser(String token, String username, String signature, Integer sex) {
        User cacheUser = redisService.getString(REDIS_USER_KEY + token);
        if (cacheUser == null) {
            throw new ServiceProcessException("session过期,请重新登录");
        }
        User user = this.baseMapper.selectOne(new QueryWrapper<User>().eq("id", cacheUser.getId()));
        user.setUsername(username);
        user.setSex(sex);
        user.setSignature(signature);
        baseMapper.updateById(user);
        redisService.cacheString(REDIS_USER_KEY + token, user, REDIS_USER_TIME);
    }

    @Override
    public void updataUserIcon(String token, String icon) {
        User cacheUser = redisService.getString(REDIS_USER_KEY + token);
        if (cacheUser == null) {
            throw new ServiceProcessException("session过期,请重新登录");
        }
        User user = this.baseMapper.selectOne(new QueryWrapper<User>().eq("id", cacheUser.getId()));
        user.setIcon(icon);
        baseMapper.updateById(user);
        redisService.cacheString(REDIS_USER_KEY + token, user, REDIS_USER_TIME);
    }

    @Override
    public void updateUserPassword(String token, String oldpsd, String newpsd) {
        User cacheUser = redisService.getString(REDIS_USER_KEY + token);
        if (cacheUser == null) {
            throw new ServiceProcessException("用户Session过期，请重新登录");
        }
        User user = this.baseMapper.selectOne(new QueryWrapper<User>().eq("id", cacheUser.getId()));
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(oldpsd.getBytes()))) {
            throw new ServiceProcessException("原始密码错误,请重新输入");
        }
        user.setPassword(DigestUtils.md5DigestAsHex(newpsd.getBytes()));
        baseMapper.updateById(user);
        redisService.deleteString(REDIS_USER_KEY + token);
    }
}
