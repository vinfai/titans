package com.titans.ticket.test.dubbo;

import com.alibaba.dubbo.config.AbstractConfig;
import com.alibaba.dubbo.rpc.cluster.configurator.AbstractConfigurator;
import com.titans.api.vo.ResultCode;
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
        ResultCode<UserVO> code = userService.getUserById(2L);
        if(code.isSuccess()){
            UserVO vo = code.getRetval();
//            System.out.println(vo.getNickName());
        }else{
//            System.out.println(code.getMsg());
        }

    }
}
