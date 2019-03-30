package com.nameless.bbs.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nameless.bbs.entity.Label;
import com.nameless.bbs.entity.Posts;
import com.nameless.bbs.entity.User;
import com.nameless.bbs.exception.ServiceProcessException;
import com.nameless.bbs.mapper.LabelMapper;
import com.nameless.bbs.mapper.PostsMapper;
import com.nameless.bbs.mapper.UserMapper;
import com.nameless.bbs.service.PostsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class PostsServiceImpl extends ServiceImpl<PostsMapper, Posts> implements PostsService {

    public static Logger logger = LoggerFactory.getLogger(PostsServiceImpl.class);

    @Resource
    private LabelMapper labelMapper;

    @Resource
    private UserMapper userMapper;

    @Transactional
    @Override
    public void savePosts(Posts posts, Integer labelId, User user) {
        try {
            Label label = labelMapper.selectById(labelId);
            if (label == null) {
                throw new ServiceProcessException("标签不存在!");
            }
            //标签的帖子数量+1
            Integer postsCount = label.getPostsCount();
            label.setPostsCount(++postsCount);
            labelMapper.updateById(label);

            //添加帖子
            posts.setLabelId(labelId);
            posts.setInitTime(LocalDateTime.now());
            posts.setUserId(user.getId());
            baseMapper.insert(posts);
        } catch (ServiceProcessException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            // 所有编译期异常转换为运行期异常
            logger.error(e.getMessage());
            throw new ServiceProcessException("发布帖子失败!");
        }
    }

    @Override
    public IPage<Map> selectPostsByPage(Integer current, Integer pageSize, String type, String search) {
        Page<Map> page = new Page<>(current, Convert.toLong(pageSize));
        page.setRecords(baseMapper.selectPostsByPage(page, type, search));
        return page;
    }

    @Override
    public IPage<Posts> getPostsByUser(Integer current, Integer pageSize, String userId) {
        Page<Posts> page = new Page<>(current, Convert.toLong(pageSize));
        return baseMapper.selectPage(page, new QueryWrapper<Posts>()
                .eq("user_id", userId));
    }

    @Override
    public IPage<Map> getPostsByLabel(Label label, int pageNo, int lenght) {
        Page<Map> page = new Page<>(pageNo, Convert.toLong(lenght));
        page.setRecords(baseMapper.getPostsByLabel(page, label.getId()));
        return page;
    }

    @Override
    public Map getPostsById(Integer postsId) {
        return baseMapper.getPostsById(postsId);
    }

}
