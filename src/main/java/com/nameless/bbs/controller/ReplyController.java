package com.nameless.bbs.controller;


import com.nameless.bbs.base.BaseController;
import com.nameless.bbs.dto.RespResult;
import com.nameless.bbs.entity.Reply;
import com.nameless.bbs.entity.User;
import com.nameless.bbs.service.ReplyService;
import com.nameless.bbs.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cindy
 * @since 2019-03-29
 */
@RestController
@RequestMapping("rest/reply")
public class ReplyController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReplyService replyService;

    @ApiOperation("发布回复接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "回复内容", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户令牌", dataType = "String"),
            @ApiImplicitParam(name = "postsId", value = "帖子ID", dataType = "Integer")
    })
    @PostMapping
    public RespResult CreateReply(Reply reply, Integer postsId, String token) {
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

            replyService.saveReply(reply, postsId, user);
            return RespResult.ok();
        });
        return result;
    }

}
