package com.lcfc.demo.modules.sys.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userNameNew;
    private String userNameOld;
    private String passWordNew;
}
