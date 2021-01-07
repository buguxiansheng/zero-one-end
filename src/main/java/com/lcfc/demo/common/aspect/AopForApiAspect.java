package com.lcfc.demo.common.aspect;

import com.alibaba.fastjson.JSON;
import com.lcfc.demo.common.annotation.AopForApi;
import com.lcfc.demo.common.util.Result;
import com.lcfc.demo.common.util.TokenStorage;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.lang.reflect.Method;


@Aspect
@Component
public class AopForApiAspect {

    @Autowired
    TokenStorage tokenStorage;

    @Pointcut("@annotation(com.lcfc.demo.common.annotation.AopForApi)")
    public void aopForApi(){}


    /**
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("aopForApi()")
    public Object aroundApi(ProceedingJoinPoint  point ) throws Throwable {
        Object object = point.proceed();//
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 默认值

//        if(!tokenStorage.isTokenExsit(attributes.getRequest().getHeader("accessToken"))){
//            try {
//                object = new Result<>(0,"权限不够",null);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }finally {
//            }
//        }

        // 获取注解的value值
//        MethodSignature signature = (MethodSignature) point.getSignature();
//        Method method = point.getTarget().getClass().getDeclaredMethod(signature.getName(),signature.getParameterTypes());
//        AopForApi aopForApi = method.getAnnotation(AopForApi.class);
//
//        //  根据user获取权限   看当前用户是否有当前操作的权限
//        aopForApi.value();
        return object;
    }
}
