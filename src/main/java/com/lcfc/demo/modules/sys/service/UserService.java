package com.lcfc.demo.modules.sys.service;

import com.lcfc.demo.modules.sys.dto.UserLoginDto;
import com.lcfc.demo.modules.sys.entity.Menu;
import com.lcfc.demo.modules.sys.entity.User;

import java.util.List;

public interface UserService {
    /**
     *
     * @param userName
     * @return
     */
    public String getUser(String userName);


    /**
     *
     * @param names
     * @return
     */
    public List<UserLoginDto> getUserList(List<String> names);

    /**
     *
     * @param user
     * @param userName
     */
    public void modifyUserInfo( User user, String userName);

    /**
     * 对菜单进行整理
     * @param userName 用户名
     * @return menus modified
     */
    public List<Menu> formatMenuList(String userName);


    /**
     *
     * @param userName
     * @return menus
     */
    public List<Menu> getMenuList(String userName);


    /**
     *
     * @return 子菜单
     */
    public List<Menu> getChildren(int id,List<Menu> rootMenus);

}
