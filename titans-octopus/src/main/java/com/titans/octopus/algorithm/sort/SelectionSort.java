package com.titans.octopus.algorithm.sort;

import java.util.regex.Pattern;

/**
 * 选择排序<br/>
 * Selection sort uses ~ n*n/2 compares and n exchanges to sort an array of length n.
 * @author vinfai
 * @since 2017/7/26
 */
public class SelectionSort {

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j],a[min])) {
                    min = j;
                }
            }
            exchange(a, i, min);
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
        /*Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(Pattern.compile("\\A"));
        String next = scanner.next();

        String[] str = Pattern.compile("").split(next);*/
        String str = "sortexample";
        String[] strs = Pattern.compile("").split(str);
        sort(strs);

    }
}
