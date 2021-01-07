package com.lcfc.demo.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data
public class Menu {
    //用户名
    private String userName;
    // 菜单id
    private int id;
    // 菜单名称
    private String menuName;
    // 父菜单id
    private String parentId;
    // 菜单url
    private String path;
    // 菜单图标
    private String icon;
    // 菜单顺序
    private int order;
    // 子菜单
    private List<Menu> childMenus;
// ... 省去getter和setter方法以及toString方法
}
