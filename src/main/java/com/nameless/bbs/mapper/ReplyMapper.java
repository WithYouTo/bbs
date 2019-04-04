package com.nameless.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nameless.bbs.entity.Reply;
import org.apache.ibatis.annotations.Param;

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
public interface ReplyMapper extends BaseMapper<Reply> {

    List<Map> getReplyByPage(Page page, @Param("postsId") Integer postsId);

}
