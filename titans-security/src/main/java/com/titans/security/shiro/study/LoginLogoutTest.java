package com.titans.security.shiro.study;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * 使用身份/凭证进行身份认证(username/password)<br/>
 * Subject:
 * @author vinfai
 * @since 2017/6/19
 */
public class LoginLogoutTest {

    public static void main(String[] args) {
        //全局初始化SecurityManager.
        //ini配置方式将使用org.apache.shiro.realm.text.IniRealm。
        Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager securityManager = securityManagerFactory.getInstance();
        //得到SecurityManager实例 并绑定给SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        //得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin1", "admin123");

        //验证失败会抛出
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        //IncorrectCredentialsException

        System.out.println(subject.isAuthenticated());

        subject.logout();
    }
}
