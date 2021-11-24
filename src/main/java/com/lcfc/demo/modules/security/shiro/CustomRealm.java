package com.lcfc.demo.modules.security.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lcfc.demo.modules.security.entity.Permissions;
import com.lcfc.demo.modules.security.entity.Role;
import com.lcfc.demo.modules.security.entity.RoleGroup;
import com.lcfc.demo.modules.security.entity.User;
import com.lcfc.demo.modules.security.service.PermissionMapper;
import com.lcfc.demo.modules.security.service.RoleGroupMapper;
import com.lcfc.demo.modules.security.service.RoleMapper;
import com.lcfc.demo.modules.security.service.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class CustomRealm extends AuthorizingRealm {


    // 从数据库中取用户信息
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleGroupMapper roleGroupMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 封装 role
        SimpleAuthorizationInfo az = new SimpleAuthorizationInfo();
        // 在哪封装的
        String username = (String) principalCollection.getPrimaryPrincipal();
        // 获取用户
        Map qMap = new HashMap<>();
        qMap.put("username",username);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User userDetail = userMapper.selectList(queryWrapper).get(0);

        // 根据用户id获取角色组
        Set<String> roleGroupIds = userMapper.getRoleGroupByUserId(userDetail.getId());
        Set<String> roleIds = new HashSet<>();
        Set<String> permissionIds = new HashSet<>();
        roleGroupIds.stream().forEach(v->{
            // 根据用户组获取角色
            Set<String> roleId = roleGroupMapper.getRoleByRoleGroup(v);
            roleIds.addAll(roleId);
        });
        roleIds.stream().forEach(v->{
            // 根据角色获取权限
            Set<String> permissionId = roleMapper.getPermissionByRoleId(v);
            permissionIds.addAll(permissionId);
        });

        // 当前用户所有的角色组
        List<RoleGroup> roleGroups = roleGroupMapper.selectBatchIds(roleGroupIds);
        // 当前用户所有的角色
        List<Role> roles = roleMapper.selectBatchIds(roleIds);
        // 所有的权限
        List<Permissions> permissions = permissionMapper.selectBatchIds(permissionIds);
        // role_names
        Set<String> roleNames = new HashSet<>();
        roles.stream().forEach(v->{
            roleNames.add(v.getRoleName());
        });

        // permissions
        Set<String> permissionsNames = new HashSet<>();
        permissions.stream().forEach(v->{
            permissionsNames.add(v.getPermissionName());
        });
        az.setRoles(roleNames);
        az.setStringPermissions(permissionsNames);
        return az;
    }


    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // token不能为空
        // 用户名
        String username = (String) authenticationToken.getPrincipal();
        Map qMap = new HashMap<>();
        qMap.put("username",username);
        User userDetail = (User) userMapper.selectByMap(qMap).get(0);
        // 对密码加密
        ByteSource salt = ByteSource.Util.bytes(username);
        // 与数据库信息做比对
        SimpleAuthenticationInfo ac = new SimpleAuthenticationInfo(username,userDetail.getPassWord(),salt,getName());
        return ac;
    }
}
