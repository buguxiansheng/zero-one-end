package com.lcfc.demo.modules.security.service;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.lcfc.demo.modules.security.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    // 建立 角色  权限 关联
    @Insert("insert into sys_role_permissions(role_id,permission_id) values(#{role_id},#{permission_id})")
    void addPermission(String role_id,String permission_id);
}
