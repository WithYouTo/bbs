package com.nameless.bbs.controller;

import com.nameless.bbs.dto.UploadResult;
import com.nameless.bbs.exception.ServiceProcessException;
import com.nameless.bbs.service.UserService;
import com.nameless.bbs.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author LHR
 * Create By 2017/8/26
 */
@Api(value = "文件上传接口",description = "图片上传")
@RestController
@RequestMapping("rest/upload")
public class UploadController {

    @Resource
    private UserService userService;

    @ApiOperation("图片上传接口")
    @PostMapping("/image")
    public UploadResult upload(@RequestParam("file") MultipartFile file) {
        UploadResult result = null;
        if (!file.isEmpty()) {
            try {
                String s = FileUtils.uploadFile(file);
                result = new UploadResult(0, new UploadResult.Data(s));
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                result = new UploadResult(1,"上传图片失败");
            }
            return result;
        }
        result = new UploadResult(1,"文件不存在");
        return result;
    }

    @ApiOperation("用户头像上传接口")
    @PostMapping("/usericon/{token}")
    public UploadResult iconUpload(@PathVariable("token") String token,@RequestParam("file") MultipartFile file){
        UploadResult result = null;
        if (!file.isEmpty()) {
            try {
                String icon = FileUtils.uploadFile(file);
                userService.updataUserIcon(token,icon);
                return new UploadResult(0, new UploadResult.Data(icon));

            } catch (IOException e) {
                e.printStackTrace();
                return new UploadResult(1,"上传图片失败");
            }catch (ServiceProcessException e1){
                e1.printStackTrace();
                return new UploadResult(1,e1.getMessage());
            }
        }
        return new UploadResult(1,"文件不存在");
    }



}
