package com.nameless.bbs.service.impl;/**
 * @Classname RankServiceImpl
 * @Description TODO
 * @Date 2019/3/29 17:25
 * @Created by zengxin
 */

import com.nameless.bbs.mapper.PostsMapper;
import com.nameless.bbs.mapper.UserMapper;
import com.nameless.bbs.service.RankService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 曾欣
 * @version 1.0
 * @description
 * @date 2019/3/29 17:25
 */

public class RankServiceImpl implements RankService {

    @Resource
    PostsMapper postsMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public List<Object> findPostsRank() {
        return null;
    }

    @Override
    public List<Object> findUserRank() {
        return null;
    }
}
