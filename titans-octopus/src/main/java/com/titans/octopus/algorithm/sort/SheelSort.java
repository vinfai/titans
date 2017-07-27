package com.titans.octopus.algorithm.sort;

import java.util.regex.Pattern;

/**
 * 希尔排序：对原有插入排序的优化，通过H有序（最好是递增序列）.
 * 先比较距离远的元素，而不是像简单交换排序算法那样先比较相邻的元素，
 * 这样可以快速减少大量的无序情况，从而减轻后续的工作。被比较的元素之间的距离逐步减少，直到减少为1，这时的排序变成了相邻元素的互换。
 * @author vinfai
 * @since 2017/7/27
 */
public class SheelSort {

    public static void main(String[] args) {
        String str = "sortexampleg1234574567467456sdfa00asdfasdkfalsd";
        String[] strs = Pattern.compile("").split(str);
        sort(strs);
    }
    public static void sort(Comparable[] a) {

        //根据原始的个数，生成一个递增的H
        int factor = 3;
        int len = a.length;
        int h = 1;
        //找到最大的
        while (h < len / factor) {
            h = h * factor + 1;
        }

        System.out.println(h);
        //然后不断减少到1完成排序
        while (h >= 1) {

            for (int i = h; i < len; i++) {
                //和最远的原始比较,相隔H个原始
                for (int j = i; j >= h && less(a[j],a[j-h]); j-=h) {
                    exchange(a, j, j - h);
                }
                show(a);
            }
            h = h/factor;
        }
        show(a);

    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void exchange(Comparable[] a,Integer i ,Integer j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void show(Comparable[] comparables) {
        for (int i = 0; i < comparables.length; i++) {
            System.out.print(comparables[i]);
        }
        System.out.println();
    }
}
