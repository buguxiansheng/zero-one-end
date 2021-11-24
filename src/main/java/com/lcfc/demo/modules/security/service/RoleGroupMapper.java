package com.lcfc.demo.modules.security.service;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcfc.demo.modules.security.entity.RoleGroup;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;


@Mapper
public interface RoleGroupMapper extends BaseMapper<RoleGroup> {
    // 添加角色
    @Insert("insert into sys_roleGroup_role(role_id,roleGroup_id) values(#{role_id},#{roleGroup_id})")
    void addRole(String role_id,String roleGroup_id);


    // 根据角色组查找角色
    @Select("select a.role_id from sys_roleGroup_role a where a.roleGroup_id = #{roleGroupId}")
    Set<String> getRoleByRoleGroup(String roleGroupId);




}
