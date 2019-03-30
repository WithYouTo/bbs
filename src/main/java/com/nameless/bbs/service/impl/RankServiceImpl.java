package com.nameless.bbs.service.impl;/**
 * @Classname RankServiceImpl
 * @Description TODO
 * @Date 2019/3/29 17:25
 * @Created by zengxin
 */

import com.nameless.bbs.entity.Posts;
import com.nameless.bbs.entity.User;
import com.nameless.bbs.mapper.PostsMapper;
import com.nameless.bbs.mapper.UserMapper;
import com.nameless.bbs.service.RankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 曾欣
 * @version 1.0
 * @description
 * @date 2019/3/29 17:25
 */
@Service
public class RankServiceImpl implements RankService {

    @Resource
    PostsMapper postsMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public List<Posts> findPostsRank() {
        return postsMapper.findHot();
    }


    @Override
    public List<User> findUserRank() {
        return userMapper.findNewUser();
    }
}
