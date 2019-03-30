package com.nameless.bbs.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nameless.bbs.entity.Reply;
import com.nameless.bbs.entity.User;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cindy
 * @since 2019-03-29
 */
public interface ReplyService extends IService<Reply> {

    /**
     * 翻页获取回复
     *
     * @param postsId
     * @param pageNo
     * @param length
     * @return
     */
    Page<Map> getReplyByPage(Integer postsId, int pageNo, int length);

    /**
     * 保存回复
     *
     * @param reply
     * @param postsId
     * @param user
     */
    void saveReply(Reply reply, Integer postsId, User user);


}
