package com.lcfc.demo.modules.sys.service.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lcfc.demo.modules.sys.dto.UserLoginDto;
import com.lcfc.demo.modules.sys.entity.Menu;
import com.lcfc.demo.modules.sys.entity.User;
import com.lcfc.demo.modules.sys.mapper.UserMapper;
import com.lcfc.demo.modules.sys.service.LoginService;
import com.lcfc.demo.common.util.Result;
import com.lcfc.demo.common.util.TokenStorage;
import com.lcfc.demo.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {



    @Autowired
    UserService userService;


    @Autowired
    TokenStorage tokenStorage;

    @Override
    public Result<Map<String,Object>> verifyUser(UserLoginDto user) {
       Result<Map<String,Object>> result = new Result<>();
       try{
           String password = userService.getUser(user.getUserName());
           System.out.println(password);
           if(password.equals(user.getPassWord())){
               String token = JWT.create().sign(Algorithm.HMAC256(user.getPassWord()));
//               tokenStorage.addToken(token);

               // token   menuLis
               List<Menu> menuList = userService.formatMenuList(user.getUserName());
               Map<String,Object> resultMap = new HashMap<>(2);
               resultMap.put("token",token);
               resultMap.put("menuList",menuList);
               result = new Result<Map<String,Object>>(0,"登录成功",resultMap);
               System.out.println(result);
           }
       }catch(Exception e){
           result = new Result<Map<String,Object>>(0,"用户名或密码错误",null);
       }finally {
            //一定会执行
       }
       return result;
    }
}
