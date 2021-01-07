package com.lcfc.demo.modules.sys.service.Impl;

import com.lcfc.demo.modules.sys.dto.UserLoginDto;
import com.lcfc.demo.modules.sys.entity.Menu;
import com.lcfc.demo.modules.sys.entity.User;
import com.lcfc.demo.modules.sys.mapper.UserMapper;
import com.lcfc.demo.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public String getUser(String userName) {
        return userMapper.getUser(userName);
    }

    @Override
    public List<UserLoginDto> getUserList(List<String> names) {
        Thread t1 = new Thread(

        );
        return userMapper.getUserList(names);
    }

    @Override
    public void modifyUserInfo(User user, String userName) {
        userMapper.modifyUserInfo(user,userName);
    }

    @Override
    public List<Menu> formatMenuList(String userName) {
        //所有的菜单
        List<Menu> menus = getMenuList(userName);
        List<Menu> menuList = new ArrayList<>();
        //遍历找出第一层menu
        for(Menu menu : menus){
            if(menu.getParentId() == null){
                menuList.add(menu);
            }
        }

        //  id 匹配子菜单
        for(Menu menu : menuList){
           menu.setChildMenus(getChildren(menu.getId(),menus));
        }
        return menuList;
    }



    @Override
    public List<Menu> getMenuList(String userName) {
        return userMapper.getMenuList(userName);
    }



    @Override
    public List<Menu> getChildren(int id,List<Menu> menus){
        List<Menu> children = new ArrayList<>();

        //
        for(Menu menu : menus){
            if(menu.getParentId()==null){
                continue;
            }
            if(Integer.parseInt(menu.getParentId()) == id){
                children.add(menu);
            }
        }

        for(Menu menu : children){
            if(menu.getPath() == null ){
                menu.setChildMenus(getChildren(menu.getId(),menus));
            }
        }
        return children;
    }


}
