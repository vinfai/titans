package com.titans.octopus.algorithm.sort;

import java.util.regex.Pattern;

/**
 * 插入排序
 * 对于i位置的数据，前面i个元素时有序的，a[i]和分别前面a[i-1],a[i-2],a[i-3]个元素比较，插入到合适的位置（交换和移动）
 * @author vinfai
 * @since 2017/7/26
 */
public class InsertionSort {

    public static void sort(Comparable[] a) {

        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exchange(a, j, j - 1);
                }
            }
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
    }

    public static void main(String[] args) {
        String str = "sortexample";
        String[] strs = Pattern.compile("").split(str);
        sort(strs);

    }
}
