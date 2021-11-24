package com.lcfc.demo.modules.sdv.controller;


import com.lcfc.demo.common.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@ResponseBody
public class TestController {

    @RequestMapping(value = "/fly",method = RequestMethod.GET)
    @RequiresPermissions("all-test") // 权限  如何生效
    public Result fly(){
        return new Result().ok("我没有token,不能被访问");
    }
}
