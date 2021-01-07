package com.lcfc.demo.common.aspect;

import com.lcfc.demo.common.annotation.LogOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class AopForLogOperation {

    /**
     * @author Will
     * 切点
     */
    @Pointcut("@annotation(com.lcfc.demo.common.annotation.LogOperation)")
    public void log(){}


    @Around("log()")
    public Object record(ProceedingJoinPoint joinPoint) throws Throwable{
        Long beTime = System.currentTimeMillis();

        try{
            // 获取数据库想要的数据
            // 定义一个日志的实体类
            // 继续方法
            Object res = joinPoint.proceed();
            // 方法签名  可获取方法名  方法参数
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();

            // 方法体
            Method method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());

//            joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(),signature.getParameterTypes());

            LogOperation annotation = method.getAnnotation(LogOperation.class);


            // 动作描述
            annotation.value();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Object();
    }






}
