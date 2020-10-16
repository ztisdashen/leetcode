package com.zt.my51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-15 23:02
 */
public class Test52 {
    int count = 0;
    int[] arr;
    int max;
    public int totalNQueens(int n) {
        arr = new int[n];
        max = n;
        put(0);
        return count;
    }
    private boolean check(int n) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    public void put(int n) {
        // n是从0开始的。当迭代到8时，说明8个皇后已经完毕
        if (n == max) {
            count++;
            return;
        }
        for (int i = 0; i < max; i++) {
            arr[n] = i;
            if (check(n)) {
                put(n + 1);
            }
        }
    }
    public static void main(String[] args) {
        Test52 t = new Test52();
        System.out.println(t.totalNQueens(4));
    }
}
