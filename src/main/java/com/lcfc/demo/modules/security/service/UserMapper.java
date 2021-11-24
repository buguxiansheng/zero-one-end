package com.lcfc.demo.modules.security.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcfc.demo.modules.security.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;


@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Insert("insert into sys_user_roleGroup(user_id,roleGroup_id) values (#{user_id},#{roleGroup_id})")
    void addRoleGroup(String user_id,String roleGroup_id);



    // 根据用户id查询用户组
    @Select("select a.roleGroup_id from sys_user_roleGroup a where user_id = #{userId}")
    Set<String> getRoleGroupByUserId(String userId);
    // 查询当前用户的角色和权限

}
