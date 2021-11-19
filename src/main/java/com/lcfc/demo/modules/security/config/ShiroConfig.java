package com.lcfc.demo.modules.security.config;

import com.lcfc.demo.modules.security.shiro.CustomRealm;

import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {



    //将自己的验证方式加入容器  可以有多个realm
    @Bean
    public CustomRealm myShiroRealm() {
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }



    @Bean(value = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }




    // 拦截器  过滤链
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){

        //
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        // filterChain
        Map<String,String> filterMap = new HashMap<>();



        filterMap.put("/login","anon");
        filterMap.put("/**","authc");

        // 过滤链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }









    //权限管理，配置主要是Realm的管理认证





    //




}
