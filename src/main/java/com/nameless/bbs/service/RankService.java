package com.nameless.bbs.service;

import com.nameless.bbs.entity.Posts;
import com.nameless.bbs.entity.User;

import java.util.List;

/**
 * @Classname RankService
 * @Description 热帖排行榜
 * @Date 2019/3/29 17:24
 * @Created by zengxin
 */
public interface RankService {
    /**
     * 获取最近一周热帖排行榜
     *
     * @return
     */
    List<Posts> findPostsRank();

    /**
     * 获取最近一周的新注册用户
     *
     * @return
     */
    List<User> findUserRank();
}
