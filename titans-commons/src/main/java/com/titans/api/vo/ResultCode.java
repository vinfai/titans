package com.titans.api.vo;

import java.io.Serializable;

/**
 * 结果的封装
 * @author vinfai
 * @since 2016/12/22
 */
public class ResultCode<T> implements Serializable {

    public static final String CODE_SUCCESS = "0000";
    public static final String CODE_UNKNOW_ERROR = "9999";

    private String code;

    private String msg;

    private T retval;//返回结果

    private Throwable exception;//rpc异常情况封装!!不做它用

    public ResultCode(String code, String msg, T retval) {
        this.code = code;
        this.msg = msg;
        this.retval = retval;
    }

    /**
     * 封装成功对象
     * @param retval 返回值
     * @param <T> 返回类型
     * @return ResultCode
     */
    public static <T> ResultCode<T> getSuccessReturn(T retval){
        return new ResultCode(CODE_SUCCESS,"操作成功!",retval);
    }

    public static ResultCode getFailure(String msg){
        return new ResultCode(CODE_UNKNOW_ERROR,msg,null);
    }

    public static ResultCode getFailure(String code,String msg){
        return new ResultCode(code,msg,null);
    }


    public void setException(Throwable exception){
        this.exception = exception;
    }

    //快捷判断
    public boolean isSuccess(){
        return CODE_SUCCESS.equals(this.code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getRetval() {
        return retval;
    }

    public void setRetval(T retval) {
        this.retval = retval;
    }
}
