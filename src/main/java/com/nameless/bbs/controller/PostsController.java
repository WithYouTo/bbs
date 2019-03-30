package com.nameless.bbs.controller;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nameless.bbs.base.BaseController;
import com.nameless.bbs.dto.RespResult;
import com.nameless.bbs.entity.Label;
import com.nameless.bbs.entity.Posts;
import com.nameless.bbs.entity.User;
import com.nameless.bbs.service.LabelService;
import com.nameless.bbs.service.PostsService;
import com.nameless.bbs.service.ReplyService;
import com.nameless.bbs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cindy
 * @since 2019-03-29
 */
@Api(value = "帖子接口", description = "发布帖子,获取帖子")
@RestController
@RequestMapping("rest/posts")
public class PostsController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private PostsService postsService;

    @Autowired
    private ReplyService replyService;


    @ApiOperation("发帖接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "帖子内容", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "帖子标题", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户令牌", dataType = "String"),
            @ApiImplicitParam(name = "labelId", value = "标签ID", dataType = "Integer")
    })
    @PostMapping
    public RespResult CreatePosts(Posts posts, String token, Integer labelId) {
        RespResult result = restProcessor(() -> {

            if (token == null) {
                return RespResult.warn("请先登录！");
            }

            User userbytoken = userService.getUserByToken(token);
            if (userbytoken == null) {
                return RespResult.warn("用户不存在,请先登录！");
            }

            User user = userService.getById(userbytoken.getId());
            if (user.getEnable() != 1) {
                return RespResult.warn("用户处于封禁状态！");
            }

            postsService.savePosts(posts, labelId, user);
            return RespResult.ok();
        });
        return result;
    }


    @ApiOperation("翻页查询帖子接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "查询条件", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "帖子类型[top : good : ]", dataType = "String"),
            @ApiImplicitParam(name = "pageNo", value = "页码[从1开始]", dataType = "int"),
            @ApiImplicitParam(name = "length", value = "返回结果数量[默认20]", dataType = "int")
    })
    @GetMapping()
    public RespResult GetPosts(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "") String type,
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "20") int length) {
        RespResult result = restProcessor(() -> {
            if (!type.equals("good") && !type.equals("top") && !type.equals("")) {
                return RespResult.error("类型错误!");
            }
            IPage<Map> page = postsService.selectPostsByPage(pageNo - 1, length, type, search);
            return RespResult.ok(page.getRecords(), length, Convert.toInt(page.getTotal()));
        });
        return result;
    }

    @ApiOperation("翻页帖子详情与回复接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postsid", value = "帖子的id", dataType = "int"),
            @ApiImplicitParam(name = "pageNo", value = "页码[从1开始]", dataType = "int"),
            @ApiImplicitParam(name = "length", value = "返回结果数量[默认20]", dataType = "int")
    })
    @GetMapping("/detail/{postsid}")
    public RespResult GetPostsDetail(
            @PathVariable("postsid") Integer postsid,
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "20") int length) {
        RespResult result = restProcessor(() -> {
            HashMap<String, Object> map = new HashMap<>();
            Posts posts = postsService.getById(postsid);
            if (posts == null) {
                return RespResult.error("帖子不存在");
            }
            Map postMap = postsService.getPostsById(postsid);
            map.put("posts", postMap);
            IPage<Map> page = replyService.getReplyByPage(postsid, pageNo - 1, length);
            map.put("replys", page.getRecords());
            return RespResult.ok(map, page.getSize(), Convert.toInt(page.getTotal()));
        });
        return result;

    }

    @ApiOperation("根据labelId获取帖子接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "labelid", value = "标签的id", dataType = "int"),
            @ApiImplicitParam(name = "pageNo", value = "页码[从1开始]", dataType = "int"),
            @ApiImplicitParam(name = "length", value = "返回结果数量[默认20]", dataType = "int"),
    })
    @GetMapping("/label/{labelid}")
    public RespResult GetPostsByLabel(
            @PathVariable("labelid") Integer labelid,
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "20") int length) {

        RespResult result = restProcessor(() -> {
            Label label = labelService.getById(labelid);
            if (label == null) {
                return RespResult.error("标签不存在");
            }
            IPage<Map> page = postsService.getPostsByLabel(label, pageNo - 1, length);
            return RespResult.ok(page.getRecords(), page.getSize(), Convert.toInt(page.getTotal()));
        });

        return result;

    }


}