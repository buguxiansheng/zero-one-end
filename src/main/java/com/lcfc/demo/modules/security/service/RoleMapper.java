package com.lcfc.demo.modules.security.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.lcfc.demo.modules.security.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    // 建立 角色  权限 关联
    @Insert("insert into sys_role_permissions(role_id,permission_id) values(#{role_id},#{permission_id})")
    void addPermission(String role_id,String permission_id);

    // 根据角色id 查找权限
    @Select("select a.permission_id from sys_role_permissions a where role_id = #{roleId}" )
    Set<String> getPermissionByRoleId(String roleId);

}
