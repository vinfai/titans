package com.titans.ticket.test.dubbo;

import com.alibaba.dubbo.common.extension.SPI;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.titans.api.vo.ResultCode;
import com.titans.avatar.api.service.UserService;
import com.titans.avatar.api.vo.UserVO;

/**
 * dubbo consumer 同dubboStart
 * @author vinfai
 * @since 2016/12/5
 */
public class DubboTest {

    public static void main(String[] args) {
        ApplicationConfig app = new ApplicationConfig();
        app.setName("ttd");

        RegistryConfig registry = new RegistryConfig("192.168.8.107:2181,192.168.8.108:2181,192.168.8.109:2181");
        registry.setProtocol("zookeeper");//和xml配置要保持一致
//        registry.setUsername();
//        registry.setProtocol();
        ReferenceConfig<UserService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setRegistry(registry);
        referenceConfig.setApplication(app);
        referenceConfig.setInterface(UserService.class);
        referenceConfig.setVersion("0.1.0");
        referenceConfig.setTimeout(6000);
        UserService service = referenceConfig.get();
        ResultCode<UserVO> code = service.getUserById(1L);
        if(code.isSuccess()){
            UserVO userVO =  code.getRetval();
            System.out.println(userVO.getId()+";"+userVO.getNickName());
        }else{
            System.out.println(code.getMsg());
        }
        //UserVO userVO = service.getUserById(1L);
        //System.out.println(userVO.getId()+";"+userVO.getNickName());
//        ExtensionLoader
//        AbstractConfig
    }
}
