package com.lcfc.demo.modules.security.controller;


import com.lcfc.demo.common.util.Result;
import com.lcfc.demo.modules.security.config.JwtConfig;
import com.lcfc.demo.modules.security.entity.User;
import com.lcfc.demo.modules.security.service.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
public class LoginController {

    @Autowired
    UserMapper userMapper;


    @Autowired
    JwtConfig jwtConfig;



    // 用户登录

    @RequestMapping("/login")
    public Result login(String username,String password){
        // 校验验证码 TODO
        // shiro 做校验的
        UsernamePasswordToken utToken = new UsernamePasswordToken(username,password);
        Subject sbj = SecurityUtils.getSubject();
        try {
            sbj.login(utToken);
        } catch (AuthenticationException e) {
            return new Result().error(110,"账号或密码错误");
        }

        // 1 根据username 生成token  放在内存中  // 2 可以存放到redis中
        String token = jwtConfig.createToken(utToken);


        // 将claim放入即可
        sbj.getSession().setAttribute("token",token);
        // 返回当前用户的菜单及权限
        return new Result<>().ok(token);
    }


    //   注册   引入短信验证码
    @RequestMapping("/register")
    public Result register(String username,String password){
        // 生成密码
        return null;
    }




}
