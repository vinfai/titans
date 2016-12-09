package com.titans.ticket.test.dubbo;

import com.alibaba.dubbo.config.AbstractConfig;
import com.alibaba.dubbo.rpc.cluster.configurator.AbstractConfigurator;
import com.titans.avatar.api.service.UserService;
import com.titans.avatar.api.vo.UserVO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过xml 调用，常用方法
 * @author vinfai
 * @since 2016/12/5
 */
public class DubboTest2 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/appcontext-dubbo.xml");
        UserService userService = (UserService)ctx.getBean("userService");
        UserVO vo = userService.getUserById(2L);
        System.out.println(vo.getId()+vo.getNickName());
    }
}
