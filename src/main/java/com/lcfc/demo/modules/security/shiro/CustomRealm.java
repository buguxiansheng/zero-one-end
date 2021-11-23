package com.lcfc.demo.modules.security.shiro;

import com.lcfc.demo.modules.security.entity.Permissions;
import com.lcfc.demo.modules.security.entity.Role;
import com.lcfc.demo.modules.security.entity.User;
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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomRealm extends AuthorizingRealm {


    // 从数据库中取用户信息
    @Autowired
    UserMapper userMapper;


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

        Map qMap = new HashMap<>();
        qMap.put("username",username);
        User userDetail = (User) userMapper.selectByMap(qMap).get(0);

        // 根据用户名获取roleName

        // 添加角色 角色从那里来
        userDetail.getRoles().stream().forEach(a->az.addRole(a.getRoleName()));
        // 添加权限
        userDetail.getRoles().stream().forEach(a->{
            for(Permissions p : a.getPermissions()){
                az.addStringPermission(p.getPermissionName());
            }
        });
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
