package com.titans.octopus.algorithm.questions.q1;

/**
 * N阶乘测试，N过大会导致Long无法存储，得到负数或者0
 * @author vinfai
 * @since 2016/8/18
 */
public class RecurseNFactorialsTest {

    public static void main(String[] args) {
        RecurseNFactorials recurseNFactorials = new RecurseNFactorials();
        Long result = recurseNFactorials.calculateNFactorial(26);
        System.out.println(result);
    }
}
