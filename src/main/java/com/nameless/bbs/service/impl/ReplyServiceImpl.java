package com.nameless.bbs.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nameless.bbs.entity.Posts;
import com.nameless.bbs.entity.Reply;
import com.nameless.bbs.entity.User;
import com.nameless.bbs.exception.ServiceProcessException;
import com.nameless.bbs.mapper.PostsMapper;
import com.nameless.bbs.mapper.ReplyMapper;
import com.nameless.bbs.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cindy
 * @since 2019-03-29
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {

    public static Logger logger = LoggerFactory.getLogger(PostsServiceImpl.class);

    @Resource
    PostsMapper postsMapper;

    @Override
    public Page<Map> getReplyByPage(Integer postsId, int pageNo, int length) {
        Page<Map> page = new Page<>(pageNo, Convert.toLong(length));
        page.setRecords(baseMapper.getReplyByPage(page, postsId));
        return page;
    }

    @Override
    public void saveReply(Reply reply, Integer postsId, User user) {
        try {
            Posts posts = postsMapper.selectById(postsId);
            if (posts == null) {
                throw new ServiceProcessException("帖子不存在!");
            }

            //帖子回复数+1
            int count = posts.getReplyCount();
            posts.setReplyCount(++count);
            postsMapper.updateById(posts);

            //添加回复
            reply.setInitTime(LocalDateTime.now());
            reply.setUserId(user.getId());
            reply.setPostsId(posts.getId());
            baseMapper.insert(reply);

        } catch (ServiceProcessException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            // 所有编译期异常转换为运行期异常
            logger.error(e.getMessage());
            throw new ServiceProcessException("发布回复失败!");
        }

    }
}
