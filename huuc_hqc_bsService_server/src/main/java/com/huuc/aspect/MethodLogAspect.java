package com.huuc.aspect;


import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * 请求调用日志切面
 */
@Aspect
@Component
@Slf4j
public class MethodLogAspect {

    @Pointcut("execution(public * com.huuc.controller.*.*.*(..))")
    public void controller() {
    }

    @Around("controller()")
    public Object aroundDbService(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        log.info("方法 : {} ; 参数 : {} ; 返回 : {}", joinPoint.getSignature().toShortString(),
                safeToJson(joinPoint.getArgs()), safeToJson(proceed));
        return proceed;
    }

    @AfterThrowing(value = "controller()", throwing = "e")
    public void afterDbServiceThrow(JoinPoint joinPoint, Exception e) {
        log.error("方法 : {} ; 参数：{} ; EXCEPTION : {}", joinPoint.getSignature().toShortString(),
                safeToJson(joinPoint.getArgs()), e.getMessage());
    }

    private String safeToJson(Object o) {
        if (o == null) {
            return "null";
        }
        String json = null;
        try {
            json = JSONUtil.toJsonStr(o);
        } catch (Exception e) {
            json = JSONUtil.toJsonStr(o);
        } finally {
            if (json == null) {
                json = o.toString();
            }
        }
        return json;
    }
}
