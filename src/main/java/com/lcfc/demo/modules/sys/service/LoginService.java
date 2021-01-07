package com.lcfc.demo.modules.sys.service;

import com.lcfc.demo.modules.sys.dto.UserLoginDto;
import com.lcfc.demo.modules.sys.entity.User;
import com.lcfc.demo.common.util.Result;


public interface LoginService {
    /**
     * @Author will
     * @param user
     * @return 返回前端的数据
     */
    Result verifyUser(UserLoginDto user);
}
