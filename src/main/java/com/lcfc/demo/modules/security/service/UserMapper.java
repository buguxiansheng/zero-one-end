package com.lcfc.demo.modules.security.service;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcfc.demo.modules.security.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Insert("insert into sys_user_roleGroup(user_id,roleGroup_id) values (#{user_id},#{roleGroup_id})")
    void addRoleGroup(String user_id,String roleGroup_id);
}
