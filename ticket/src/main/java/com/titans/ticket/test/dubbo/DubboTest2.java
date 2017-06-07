package com.titans.ticket.test.dubbo;

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
            System.out.println("result name :"+vo.getNickName());
//            System.out.println(vo.getNickName());
        }else{
            System.out.println("error:"+code.getMsg());
        }

        UserService userService2 = (UserService) ctx.getBean("hessianUserService");
        ResultCode<UserVO> code2 = userService2.getUserById(3L);
        if(code2.isSuccess()){
            UserVO vo = code2.getRetval();
            System.out.println("result name :"+vo.getNickName());
        }else{
            System.out.println("error:"+code.getMsg());
        }


    }
}
