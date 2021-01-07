package com.lcfc.demo.common.Inerceptors;


import com.lcfc.demo.common.util.TokenStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    public TokenStorage tokenStorage;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        Boolean pass = true;
        try{
            if("OPTIONS".equals(request.getMethod().toString())) {
                return true;
            }else{
                if(tokenStorage.isTokenExsit(request.getHeader("accessToken"))){
                    pass = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {}
        return pass;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
