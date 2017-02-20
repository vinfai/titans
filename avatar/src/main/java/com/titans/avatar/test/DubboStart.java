package com.titans.avatar.test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper;
import com.titans.avatar.api.service.UserService;
import com.titans.avatar.service.UserServiceImpl;

import java.io.IOException;

/**
 * dubbo provider 编程写法
 * @author vinfai
 * @since 2016/12/8
 */
public class DubboStart {
    public static void main(String[] args) {
//        ProtocolFilterWrapper
        ApplicationConfig config = new ApplicationConfig();
        config.setName("dubbo-demo-server");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("192.168.8.107:2181,192.168.8.108:2181,192.168.8.109:2181");

        //远程调用层
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(11111);
        //protocolConfig.setThreads(20);

        UserService userService = new UserServiceImpl();
        ServiceConfig<UserService> serviceConfig = new ServiceConfig();
        serviceConfig.setApplication(config);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setTimeout(150000);

        serviceConfig.setInterface(UserService.class);
        serviceConfig.setRef(userService);
        serviceConfig.setVersion("0.1.0");
        // 暴露及注册服务
        serviceConfig.export();
        /*ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/appcontext-dubbo.xml");
        ctx.start();*/

        //避免主线程退出,任意键退出
       /* try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
            DubboStart.class.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
