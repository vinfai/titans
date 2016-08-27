package com.titans.octopus.patterns.signleton;

/**
 * 单例测试
 * @author vinfai
 * @since 2016-08-27
 */
public class SignletonTest {
    public static void main(String[] args) throws Exception {
        Signleton s1 = Signleton.getInstance();
        Signleton s2 = Signleton.getInstance();
        System.out.println(s1==s2);
        //clone 对象不一致
        //System.out.println(s1==(Signleton)s1.clone());

        SignletonEnum instance = SignletonEnum.INSTANCE;
        System.out.println(instance.doSth());
    }
}
