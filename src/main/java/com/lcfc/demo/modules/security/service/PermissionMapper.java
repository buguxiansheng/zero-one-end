package com.lcfc.demo.modules.security.service;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcfc.demo.modules.security.entity.Permissions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

@Mapper
public interface PermissionMapper extends BaseMapper<Permissions> {


}
