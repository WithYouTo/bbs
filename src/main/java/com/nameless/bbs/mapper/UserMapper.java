package com.nameless.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nameless.bbs.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cindy
 * @since 2019-03-29
 */
public interface UserMapper extends BaseMapper<User> {

    @Select(value = "select u.id, u.username , u.icon from user u where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <=DATE(u.init_time) ORDER BY u.id DESC limit 12")
    List<User> findNewUser();
}
