package com.titans.octopus.patterns.signleton;

/**
 * @author vinfai
 * @since 2016-08-27
 */
enum SignletonEnum {
    INSTANCE;
    public String doSth(){
        return "this is a signleton class";
    }
}
