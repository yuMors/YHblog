package com.sangeng.service.impl;

import com.sangeng.config.ResponseResult;
import com.sangeng.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements UploadService {

    /**
     * 上传头像
     */
    @Override
    public ResponseResult<?> uploadImg(MultipartFile img) {
        /*判断文件*/
        return null;
    }
}
