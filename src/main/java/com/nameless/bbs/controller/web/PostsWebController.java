package com.nameless.bbs.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author LHR
 * Create By 2017/8/27
 */
@Controller
@RequestMapping("/posts")
public class PostsWebController {

    @RequestMapping("/add")
    public String add(){
        return "posts/add";
    }

    @RequestMapping("/detail")
    public String detail() {
        return "posts/detail";
    }
}
