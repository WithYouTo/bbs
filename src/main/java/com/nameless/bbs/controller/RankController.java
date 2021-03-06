package com.nameless.bbs.controller;

import com.nameless.bbs.base.BaseController;
import com.nameless.bbs.common.Constants;
import com.nameless.bbs.dto.RespResult;
import com.nameless.bbs.service.RankService;
import com.nameless.bbs.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author LHR
 * Create By 2017/8/31
 */
@Api(value = "排行榜接口", description = "七天内热帖排行榜,七天内新注册用户排行榜")
@RestController
@RequestMapping("rest/rank")
public class RankController extends BaseController {

    @Resource
    private RankService rankService;

    @Autowired
    private RedisService<List<Object>> redisService;

    private String REDIS_RANK_POSTS = Constants.REDIS_RANK_POSTS;

    private String REDIS_RANK_USERS = Constants.REDIS_RANK_USERS;

    @ApiOperation("获取一个月内热帖排行榜")
    @GetMapping("/topPosts")
    public RespResult getTotPosts() {
        RespResult result = restProcessor(() -> {
            List<Object> hot = redisService.getString(REDIS_RANK_POSTS);
            hot = (List) rankService.findPostsRank();
            redisService.cacheString(REDIS_RANK_POSTS, hot, 1);
            return RespResult.ok(hot);
        });
        return result;
    }

    @ApiOperation("获取一个月内新注册的用户")
    @GetMapping("/newUsers")
    public RespResult getNewUser() {
        RespResult result = restProcessor(() -> {
            List<Object> users = (List) rankService.findUserRank();
            redisService.cacheString(REDIS_RANK_USERS, users, 1);
            return RespResult.ok(users);
        });
        return result;
    }

}
