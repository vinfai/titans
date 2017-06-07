package com.titans.dubbo.filter;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.filter.GenericFilter;
import com.titans.api.vo.ResultCode;
import com.titans.avatar.api.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * dubbo 白名单过滤器!
 * @author vinfai
 * @since 2016/12/21
 */
public class CheckAuthFilter extends GenericFilter{
    private static final Logger logger = LoggerFactory.getLogger(CheckAuthFilter.class);

    private static List<String> officeIps = new ArrayList<>();
    static{
        officeIps.add("192.168.8.152");
        officeIps.add("169.254.7.91");

    }
    @Override
    public Result invoke(Invoker<?> invoker, Invocation inv) throws RpcException {

        String clientIp = RpcContext.getContext().getRemoteHost();
        logger.info("client ip :{}", clientIp);
        if(!officeIps.contains(clientIp)){
            logger.warn("authority refused");
            return new RpcResult(ResultCode.getFailure("不在白名单中，没有权限!!"+clientIp));
        }
        return super.invoke(invoker, inv);
    }
}
