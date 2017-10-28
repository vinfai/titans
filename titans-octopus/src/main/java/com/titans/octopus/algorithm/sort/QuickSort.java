package com.titans.octopus.algorithm.sort;

import java.util.regex.Pattern;

/**
 * 思想：将一个数组拆分成2个独立的数组，进行单独排序。
 * 条件：找到一个基准点，使得拆分后的2个数据，pivot 前的元素都不大于 a[pivot],pivot 后的元素都不小于a[pivot]
 *
 * @author vinfai
 * @since 2017/7/31
 */
public class QuickSort {

    public static void main(String[] args) {
        String str = "9876543210";
        String[] strs = Pattern.compile("").split(str);
        System.out.println(strs.length);
        sort(strs, 0, strs.length-1);
    }

    public static void sort(Comparable[] a, int low, int high) {
        //跳出递归循环，排序结束条件
        if (high <= low) {
            return ;
        }
        int i = low;
        int j = high;
        //1.默认基准点为最初的位置.
        int pivot = low;
        while (i != j) {
            //从右边开始，找到比a[pivot]小的元素，停止
            while (less(a[pivot],a[j]) && i < j) j--;
            //从左侧开始,找到比a[pivot]大的元素，停止
            while (less(a[i], a[pivot]) && i < j) i++;

            if (i < j) {//2个元素进行交换,然后让 2边继续走直到相遇(i==j)
                exchange(a, i, j);
            }
        }
        System.out.println(i+"-->"+j);
        //交换基准点 i=j
        exchange(a, low, j);

        sort(a, 0, j - 1);
        sort(a,j+1,high);
        show(a);
    }


    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) <= 0;
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
