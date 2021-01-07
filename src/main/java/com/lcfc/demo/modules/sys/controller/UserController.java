package com.lcfc.demo.modules.sys.controller;

import com.lcfc.demo.common.annotation.AopForApi;
import com.lcfc.demo.modules.sys.dto.UserDto;
import com.lcfc.demo.modules.sys.dto.UserLoginDto;
import com.lcfc.demo.modules.sys.entity.User;
import com.lcfc.demo.modules.sys.service.UserService;
import com.lcfc.demo.common.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin("*")
@RestController
public class UserController {
    @Autowired
    UserService userService;



    @GetMapping("/getUserList")
    @AopForApi
    public Result<UserLoginDto> getUserList(@RequestParam("userName") String userName){
        List<String> names = new ArrayList<>();
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassWord(userService.getUser(userName));
        userLoginDto.setUserName(userName);
        userService.getUser(userName);
        return new Result<UserLoginDto>(0,"请求成功",userLoginDto);
    }

    @PostMapping("/modifyUserInfo")
    @AopForApi
    public Result<String> modify(@RequestBody UserDto userDto){
        User user = new User();
        user.setUserName(userDto.getUserNameNew());
        user.setPassWord(userDto.getPassWordNew());
        userService.modifyUserInfo(user,userDto.getUserNameOld());
        return new Result<String>(0,"修改成功",null);
    }
}
