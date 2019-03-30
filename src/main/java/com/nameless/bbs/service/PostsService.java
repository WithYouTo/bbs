package com.nameless.bbs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nameless.bbs.entity.Label;
import com.nameless.bbs.entity.Posts;
import com.nameless.bbs.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cindy
 * @since 2019-03-29
 */
public interface PostsService extends IService<Posts> {

    /**
     * 保存帖子
     *
     * @param posts   帖子
     * @param labelId 标签id
     */
    void savePosts(Posts posts, Integer labelId, User user);

    /**
     * 翻页查询帖子
     *
     * @param type
     * @param search
     * @return
     */
    IPage<Map> selectPostsByPage(Integer page, Integer pageSize, @Param("type") String type, @Param("search") String search);

    /**
     * 获取用户最近发布的10个POSTS
     *
     * @param userId
     * @return
     */
    IPage<Posts> getPostsByUser(Integer page, Integer pageSize, String userId);

    /**
     * 根据标签分页获取获取Posts
     *
     * @param label
     * @return
     */
    IPage<Map> getPostsByLabel(Label label, int pageNo, int lenght);


    Map getPostsById(Integer postsId);
}
