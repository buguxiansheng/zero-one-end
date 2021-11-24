package com.lcfc.demo.modules.security.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_permissions")
public class Permissions extends BaseEntity{
    @TableId(type = IdType.UUID)
    private String id;
    private String permissionName;
}
