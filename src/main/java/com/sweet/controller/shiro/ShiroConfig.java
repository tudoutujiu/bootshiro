package com.sweet.controller.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.codehaus.groovy.util.ListHashMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author 宋德能
 * @date 2019年12月26日---下午 3:12
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建一个ShiroFilterfactoryBean
     */
    @Bean(name = "ShiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterfactoryBean(@Qualifier("DefaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro内置过滤器
        /**
         * 1  anon:  无需认证
         * 2  authc:  必须认证才可以访问
         * 3  user:  使用rememberMe的功能可以直接访问
         * 4  perms:  该资源必须得到权限资源才可访问
         * 5  role:  该资源必须得到角色资源才可访问
         */
        Map<String,String> filterMap = new ListHashMap<String,String>();
        /*filterMap.put("/add","authc");
        filterMap.put("/update","authc");*/
        filterMap.put("/*","authc");
        filterMap.put("/testThymeleaf","anon");
        filterMap.put("/login","anon");
        //修改调整的登陆的路径
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建一个DefaultWebSecurityManager
     */
    @Bean(name = "DefaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("UserRealm")UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联realm
        defaultWebSecurityManager.setRealm(userRealm);

        return defaultWebSecurityManager;
    }

    /**
     * 创建一个Realm
     */
    @Bean(name = "UserRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
