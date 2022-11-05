package com.sangeng.aspect;

import com.alibaba.fastjson.JSON;
import com.sangeng.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面类
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.sangeng.annotation.SystemLog)")
    public void pt() {
    }

    /**
     * 定义通知方法
     */
    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //选中代码 ctrl alt t
        Object ret;
        //异常不在catch抛出 加在方法后面
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } finally {
            //结束后执行
            //System.lineSeparator() 换行 不受windows和linux限制
            //系统不一样 换行实际不一样
            log.info("===****finallyEND****===" + System.lineSeparator());
        }
        return ret;
    }


    private void handleAfter(Object ret) {
        // 打印出参
        log.info("Response       : {}", JSON.toJSONString(ret));
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();

        //获取被增强方法上的注解对象
        SystemLog systemLog = getSystemLog(joinPoint);
        log.info("=======Start=======");
        // 打印请求 URL

        log.info("URL            : {}", request.getRequestURL());
        //log.info("URL            : {}", "URL request is null");

        // 打印描述信息
        log.info("BusinessName   : {}", systemLog.businessName());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
                ((MethodSignature)joinPoint.getSignature()).getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", joinPoint.getArgs());
        // 打印出参
        log.info("Response       : {}", JSON.toJSONString(joinPoint.getArgs()));
        // 结束后换行
        log.info("=======handleBeforeEnd=======" + System.lineSeparator());
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        //获取到签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        /*SystemLog systemLog = methodSignature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;*/
        return methodSignature.getMethod().getAnnotation(SystemLog.class);
    }
}
