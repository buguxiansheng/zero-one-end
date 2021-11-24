package com.lcfc.demo.modules.security.config;

import com.lcfc.demo.modules.security.filter.TokenFilter;
import com.lcfc.demo.modules.security.shiro.CustomRealm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro的配置中心
 */
@Configuration
public class ShiroConfig {

    @Autowired
    TokenFilter tokenFilter;

    //将自己的验证方式加入容器  可以有多个realm
    @Bean("myShiroRealm")
    public CustomRealm myShiroRealm() {
        CustomRealm customRealm = new CustomRealm();
        //  凭证加密
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        // ??
        return customRealm;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("myShiroRealm") CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        return securityManager;
    }


    // 拦截器  过滤链
    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/login");

        // 所有的路径都会校验
//        Map<String, Filter> filters = new LinkedHashMap();
//        filters.put("client",tokenFilter);
//        shiroFilterFactoryBean.setFilters(filters);

        // filterChain 路径是否需要认证
        Map<String,String> filterMap = new HashMap<>();

        filterMap.put("/login","anon");
        filterMap.put("/register","anon");
        filterMap.put("/**","authc");
//        filterMap.put("/**","client");

        // 过滤链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //
    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
//
//
//
    // 使得权限控制
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
