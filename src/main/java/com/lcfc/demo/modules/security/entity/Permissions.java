package com.lcfc.demo.modules.security.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

@Data
@TableName("sys_permissions")
public class Permissions extends BaseEntity{
    @TableId(type = IdType.UUID)
    private String id;
    private String permissionName;
}
