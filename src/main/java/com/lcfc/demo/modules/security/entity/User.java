package com.lcfc.demo.modules.security.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User extends BaseEntity{
    @TableId(type = IdType.UUID)
    private String id;

    // 用户名
    @TableField("username")
    private String userName;
    // 密码
    @TableField("password")
    private String passWord;
    /**
     * 角色组
     */
    @TableField(exist = false)
    private Set<Role> roles;

    @TableField(exist = false)
    private Set<Permissions> permissions;
}
