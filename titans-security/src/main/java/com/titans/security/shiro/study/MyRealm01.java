package com.titans.security.shiro.study;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * 自定义的Realm:<BR/>
 * shiro 从从Realm获取安全数据（如用户、角色、权限）
 * @author vinfai
 * @since 2017/6/19
 */
public class    MyRealm01 implements Realm {
    @Override
    public String getName() {
        return "myrealm01";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        if (token instanceof UsernamePasswordToken) {
            return true;
        }
        return false;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String)token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        if (!"admin".equals(principal)) {
            throw new UnknownAccountException("账号不存在");
        }
        if (!"admin123".equals(password)) {
            throw new IncorrectCredentialsException();
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(principal, password, getName());
    }
}
