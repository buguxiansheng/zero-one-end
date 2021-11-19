package com.lcfc.demo.modules.security.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Set;

@Data
@TableName("sys_role")
public class Role extends BaseEntity{
    @TableId(type = IdType.UUID)
    private String id;

    private String roleName;
    /**
     * 角色对应权限集合
     */
    @TableField(exist = false)
    private Set<Permissions> permissions;
}
