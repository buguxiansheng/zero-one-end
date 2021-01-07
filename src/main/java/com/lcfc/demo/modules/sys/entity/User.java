package com.lcfc.demo.modules.sys.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userName;
    private String passWord;
    private List<Menu> menuList;

}
