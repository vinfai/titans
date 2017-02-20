package com.titans.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.filter.GenericFilter;
import com.titans.api.vo.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * client filter
 * 调用结果统一封装ResultCode.
 * 1.接口返回结果全部使用ResultCode封装.
 * 2.调用异常或者其他情况下，将异常封装成ResultCode。
 * @author vinfai
 * @since 2016/12/22
 */
@Activate(group = Constants.CONSUMER,order = -1000)
public class ResultCodeTransfromFilter extends GenericFilter {

    private static Logger logger = LoggerFactory.getLogger(ResultCodeTransfromFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation inv) throws RpcException {
        //TODO 统计调用情况

        Result result = null;
        try {
            result = super.invoke(invoker, inv);
            if(result.hasException()){
                logger.warn("dubbo invoke has exception,path{},exception:{}",invoker.getUrl().getPath()+"."+inv.getMethodName(),result.getException());
                ResultCode rst = ResultCode.getFailure("服务短路");
                //异常如何传递过去呢？

                //错误统计
                return new RpcResult(rst);
            }
        } catch (RpcException e) {
            //e.printStackTrace();
            logger.warn("dubbo invoke RpcException,{}",e);
            ResultCode rst = new ResultCode(ResultCode.CODE_UNKNOW_ERROR,"服务短路!",null);
            return new RpcResult(rst);
        } finally {
            //TODO 统计
        }
        return result;
    }
}
