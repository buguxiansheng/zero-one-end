package com.lcfc.demo.modules.sys.controller;

import com.lcfc.demo.modules.sys.dto.UserLoginDto;
import com.lcfc.demo.modules.sys.entity.User;
import com.lcfc.demo.modules.sys.mapper.UserMapper;
import com.lcfc.demo.modules.sys.service.LoginService;
import com.lcfc.demo.common.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;



    @PostMapping("/login")
    Result login(@RequestBody UserLoginDto userLoginDto){

        return loginService.verifyUser(userLoginDto);
    }
}
