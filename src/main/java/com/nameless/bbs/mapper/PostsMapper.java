package com.nameless.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nameless.bbs.entity.Label;
import com.nameless.bbs.entity.Posts;
import com.nameless.bbs.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cindy
 * @since 2019-03-29
 */
public interface PostsMapper extends BaseMapper<Posts> {

    @Select(value = "select p.id, p.title , p.reply_count from quark_posts p where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <=DATE(p.init_time) ORDER by reply_count desc limit 10")
    List<Object> findHot();

    List<Posts> findByUser(IPage<Posts> page, User user);

    List<Posts> findByLabel(IPage<Posts> page, Label label);
}
