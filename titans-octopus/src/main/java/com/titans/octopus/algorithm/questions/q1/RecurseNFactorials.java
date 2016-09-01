package com.titans.octopus.algorithm.questions.q1;

/**
 * 递归计算N阶乘， N阶乘测试，N过大会导致Long无法存储，得到负数或者0
 * @author vinfai
 * @since 2016/8/18
 */
public class RecurseNFactorials {

    public Long calculateNFactorial(int factorialLevel){
        if(factorialLevel<0||factorialLevel>25){
            throw new RuntimeException("n must gt 0 or n le 25");
        }
        if(factorialLevel==0||factorialLevel==1){
            return 1L;
        }
        Long result = factorialLevel*calculateNFactorial(factorialLevel-1);
        return result;
    }
}
