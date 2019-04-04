package com.nameless.bbs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nameless.bbs.base.BaseController;
import com.nameless.bbs.dto.RespResult;
import com.nameless.bbs.entity.Label;
import com.nameless.bbs.service.LabelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cindy
 * @since 2019-03-29
 */
@RestController
@RequestMapping("rest/label")
public class LabelController extends BaseController {

    @Autowired
    private LabelService labelService;

    @ApiOperation("获取标签")
    @GetMapping
    public RespResult getAllLabel() {

        RespResult result = restProcessor(() -> {
            List<Label> labels = labelService.list(new QueryWrapper<>());
            return RespResult.ok(labels);
        });
        return result;
    }

}
