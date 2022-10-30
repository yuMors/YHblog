package com.sangeng.service;

import com.sangeng.config.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    /**
     * 上传头像
     */
    ResponseResult<?> uploadImg(MultipartFile img);
}
