package com.sweet.controller.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author 宋德能
 * @date 2019年12月26日---下午 3:15
 */
public class UserRealm extends AuthorizingRealm {
    //执行授权罗辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权罗辑");
        return null;
    }

    //执行认证罗辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证罗辑");
        String name = "sweet";
        String password = "123456";
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        return null;
    }
}
