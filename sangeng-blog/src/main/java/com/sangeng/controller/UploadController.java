package com.sangeng.controller;

import com.sangeng.config.ResponseResult;
import com.sangeng.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件控制类
 */
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 上传头像
     */
    @PostMapping("/upload")
    public ResponseResult<?> uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }


}
