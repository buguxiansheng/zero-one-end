package com.lcfc.demo.modules.security.filter;

import com.alibaba.fastjson.JSON;
import com.lcfc.demo.common.util.Result;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.Constant;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class TokenFilter extends AuthenticatingFilter {
    @Autowired
    RedisTemplate redisTemplate;



    // 封装下token shiro认识的
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest hsrq = (HttpServletRequest)servletRequest;
        HttpServletResponse hsrp = (HttpServletResponse)servletResponse;

        String token = getRequestToken(hsrq);
        // token 不存在
        if(StringUtils.isBlank(token)) {
            return null;
        }
        //
        if(!redisTemplate.hasKey(token)){
            return null;
        }

        return new BearerToken(token);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String token = getRequestToken((HttpServletRequest)servletRequest);
        if(StringUtils.isBlank(token) || !redisTemplate.hasKey(token)){
            // 对象转json对象
            String json = JSON.toJSONString(new Result<>().error(401,"您没得权限"));
            httpResponse.getWriter().print(json);
            return false;
        }
        return executeLogin(servletRequest, servletResponse);
    }


    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest){
        //从header中获取token
        String token = httpRequest.getHeader("token");

        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = httpRequest.getParameter("token");
        }
        return token;
    }
}
