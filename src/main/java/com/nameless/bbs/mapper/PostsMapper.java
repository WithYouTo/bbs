package com.nameless.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nameless.bbs.entity.Posts;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cindy
 * @since 2019-03-29
 */
public interface PostsMapper extends BaseMapper<Posts> {

    @Select(value = "select p.id, p.title , p.reply_count from posts p where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <=DATE(p.init_time) ORDER by reply_count desc limit 10")
    List<Posts> findHot();

    List<Map> selectPostsByPage(Page page, @Param("type") String type, @Param("search") String search);

    List<Map> getPostsByLabel(Page page, Integer labelId);

    Map getPostsById(Integer postsId);
}
