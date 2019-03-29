package com.nameless.bbs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nameless.bbs.entity.Notification;
import com.nameless.bbs.mapper.NotificationMapper;
import com.nameless.bbs.service.NotificationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cindy
 * @since 2019-03-29
 */
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements NotificationService {

}
