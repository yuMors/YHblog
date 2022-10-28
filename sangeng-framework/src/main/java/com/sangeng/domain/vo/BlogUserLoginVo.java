package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogUserLoginVo {
    private String token;
    //private UserInfoVo userInfoVo;
    /**
     * 好像在redis中定义了些什么，userInfoVo前端数据无法展示 虽然postman可以查到数据
     * 但改用userInfo就可以了
     */
    private UserInfoVo userInfo;
}
