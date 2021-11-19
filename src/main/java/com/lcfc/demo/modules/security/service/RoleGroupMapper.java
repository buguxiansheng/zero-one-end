package com.lcfc.demo.modules.security.service;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcfc.demo.modules.security.entity.RoleGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface RoleGroupMapper extends BaseMapper<RoleGroup> {
    // 添加角色
    @Select("insert into sys_roleGroup_role(role_id,roleGroup_id) values(#{role_id},#{roleGroup_id})")
    void addRole(String role_id,String roleGroup_id);
}
