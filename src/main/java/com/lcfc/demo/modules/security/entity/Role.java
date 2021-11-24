package com.lcfc.demo.modules.security.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
