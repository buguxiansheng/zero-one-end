package com.lcfc.demo.modules.security.controller;


import com.lcfc.demo.common.util.Result;
import com.lcfc.demo.modules.security.config.JwtConfig;
import com.lcfc.demo.modules.security.entity.User;
import com.lcfc.demo.modules.security.service.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@ResponseBody
@Slf4j
public class LoginController {

    @Autowired
    UserMapper userMapper;


    @Autowired
    JwtConfig jwtConfig;


    @Autowired
    RedisTemplate redisTemplate;



    // 用户登录
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        // shiro 做校验的
        UsernamePasswordToken utToken = new UsernamePasswordToken(user.getUserName(),user.getPassWord());
        // 密码加密
        Subject sbj = SecurityUtils.getSubject();
        try {
            // shiro  对密码加密
            sbj.login(utToken);

            //
        } catch (AuthenticationException e) {
            return new Result().error(110,"账号或密码错误");
        }

        // 1 根据username 生成token  放在内存中  // 2 可以存放到redis中
        String token = jwtConfig.createToken(user);
        // 存放到redis中
        try {
            redisTemplate.boundValueOps(token).set(token,3, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error("服务器存储token失败:{}",e.getCause());
        }
        // 返回当前用户的菜单及权限
        return new Result<>().ok(token);
    }

    // 清除token
    @PostMapping("/exit")
    public void exit(User user){
        // 清除token  放redis中 设置过期时间
        redisTemplate.delete(user.getUserName());
    }

    //   注册   引入短信验证码
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        // 先查看用户是否存在
        Map<String,Object> qMap =  new HashMap<>();
        qMap.put("username",user.getUserName());
        // 数据库中的用户已存在
        if(userMapper.selectByMap(qMap).size() == 0 || null == userMapper.selectByMap(qMap) ){
            // 盐值
            Object salt = ByteSource.Util.bytes(user.getUserName());
            // 和realm中一致
            SimpleHash sh = new SimpleHash("MD5",user.getPassWord(),salt,1);
            // 更新下密码
            user.setPassWord(sh.toString());
            // 插入数据库
            userMapper.insert(user);
        }else{
            return new Result().error(401,"用户名已存在");
        }
        // 生成密码
        return new Result().ok("用户创建成功");
    }

}
