package com.carl.springmvc.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.carl.springmvc.shiro.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description shiro配置文件
 * @Author carl.he
 * @Date 2019/6/25 10:21
 * @Version 1.0
 **/
@Configuration
public class ShiroConfig {

    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher =
                new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * @Author carl.he
     * @Description 配置filter
     * @Date 2019/6/25 10:28
     * @param securityManager {@link org.apache.shiro.mgt.SecurityManager} 安全管理
     * @return {@link ShiroFilterFactoryBean}
     **/
    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager")
                                                         DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.html"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/templates/html/login.html");
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/templates/html/login.html");

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //游客，开发权限
        filterChainDefinitionMap.put("/guest/**", "anon");
        //用户，需要角色权限 “user”
        filterChainDefinitionMap.put("/user/**", "anon");
        //管理员，需要角色权限 “admin”
        filterChainDefinitionMap.put("/admin/**", "anon");
        //开放登陆接口
        filterChainDefinitionMap.put("/login", "anon");
        //静态文件无需权限
        filterChainDefinitionMap.put("/static/**", "anon");
        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterChainDefinitionMap.put("/**", "user");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     * 注入 securityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(HashedCredentialsMatcher hashedCredentialsMatcher) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(userRealm(hashedCredentialsMatcher));
        return securityManager;
    }

    /**
     * 自定义身份认证 realm;
     * <p>
     * 必须写这个类，并加上 @Bean 注解，目的是注入 UserRealm，
     * 否则会影响 UserRealm类 中其他类的依赖注入
     */
    @Bean("userRealm")
    public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher")
                                           HashedCredentialsMatcher matcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
