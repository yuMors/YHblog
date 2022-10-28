package com.sangeng.handler.security;

import com.alibaba.fastjson.JSON;
import com.sangeng.config.ResponseResult;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthenticationEntryPoint 认证失败处理器
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    /**
     *
     * InsufficientAuthenticationException 没有资格证书
     * Thrown if an authentication request is rejected because the credentials are not
     *
     * BadCredentialsException 资格证书无效
     * Thrown if an authentication request is rejected because the credentials are invalid
     * authentication 身份验证 credentials 资格证书
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //打印异常信息
        authException.printStackTrace();
        //ResponseResult<?> result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
        ResponseResult<?> result = null;
        if (authException instanceof BadCredentialsException){
            result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(),
                    authException.getMessage());
        } else if (authException instanceof InsufficientAuthenticationException){

            result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        } else {
            result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),
                    "认证或授权失败");
        }
        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));

    }
}
