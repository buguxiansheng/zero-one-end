package com.lcfc.demo.modules.security.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_roleGroup")
public class RoleGroup extends BaseEntity{
    @TableId(type = IdType.UUID)
    private String id;

    @TableField("roleGroup_name")
    private String roleGroupName;
}
