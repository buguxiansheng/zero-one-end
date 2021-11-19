package test.mybatis;


import com.lcfc.demo.ZeroOneApplication;
import com.lcfc.demo.modules.security.entity.Permissions;
import com.lcfc.demo.modules.security.entity.Role;
import com.lcfc.demo.modules.security.entity.RoleGroup;
import com.lcfc.demo.modules.security.entity.User;
import com.lcfc.demo.modules.security.service.PermissionMapper;
import com.lcfc.demo.modules.security.service.RoleGroupMapper;
import com.lcfc.demo.modules.security.service.RoleMapper;
import com.lcfc.demo.modules.security.service.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZeroOneApplication.class)
public class MybatisTest {

    // 用户
    @Autowired
    UserMapper userMapper;


    // 角色
    @Autowired
    RoleMapper roleMapper;

    //角色组
    @Autowired
    RoleGroupMapper roleGroupMapper;

    // 权限
    @Autowired
    PermissionMapper permissionMapper;


    @Test
    public void test(){
//        //用户
//        User u = new User();
//        u.setUserName("H200NR0");
//        u.setPassWord("123");
//        // 添加user
//        userMapper.insert(u);


// 角色
//        Role r = new Role();
//        r.setRoleName("administrator");
//        roleMapper.insert(r);



        //角色组
//        RoleGroup rg = new RoleGroup();
//        rg.setRoleGroupName("administrator");
//        roleGroupMapper.insert(rg);


        // 权限
//        Permissons p = new Permissons();
//        p.setPermissionName("all");
//
//        permissionMapper.insert(p);


//        // 权限和角色
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("role_name","administrator");
//        Role r = roleMapper.selectByMap(map).get(0);
//        Map<String,Object> map2 = new HashMap<>();
//        map.put("permission_name","all");
//        Permissions p = permissionMapper.selectByMap(map2).get(0);
//        roleMapper.addPermission(r.getId(),p.getId());


//        // 角色 角色组
//        Map<String,Object> map = new HashMap<>();
//        map.put("roleGroup_name","administrator");
//        RoleGroup rg = roleGroupMapper.selectByMap(map).get(0);
//        Map<String,Object> map2 = new HashMap<>();
//        map.put("role_name","administrator");
//        Role r = roleMapper.selectByMap(map2).get(0);
//        roleGroupMapper.addRole(r.getId(),rg.getId());



        // 用户和角色组
        Map<String,Object> map = new HashMap<>();
        map.put("username","will");
        User u = userMapper.selectByMap(map).get(0);
        Map<String,Object> map2 = new HashMap<>();
        map.put("roleGroup_name","administrator");
        RoleGroup rg = roleGroupMapper.selectByMap(map2).get(0);
        userMapper.addRoleGroup(u.getId(),rg.getId());

        // 自动生成时间
    }
}
