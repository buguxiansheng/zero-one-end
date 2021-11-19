package com.lcfc.demo.modules.security.service;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lcfc.demo.modules.security.entity.Permissions;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper extends BaseMapper<Permissions> {
}
