package com.titans.security.shiro.test;

import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 权限授权测试
 * @author vinfai
 * @since 2017/6/19
 */
public class ShiroAuthorizationTest {

    @Test
    public void testIsPermitted() {
        login("classpath:shiro-permission.ini", "admin", "123");
        //getSubject().checkPermission("user:create");
        Assert.assertTrue(getSubject().isPermitted("user:create"));
        Assert.assertFalse(getSubject().isPermitted("user:view"));
//        WildcardPermission
    }



    @After
    public void tearDown() {
        ThreadContext.unbindSubject();
    }

    protected void login(String configFile, String username, String password) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
    }

    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }
}
