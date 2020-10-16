package com.zt.my51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-15 22:37
 */
public class Test51 {
    List<List<String>> res = new ArrayList<>();
    int[] arr;
    int max;

    public List<List<String>> solveNQueens(int n) {
        arr = new int[n];
        max = n;
        put(0);
        return res;
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
            //System.out.println(Arrays.toString(arr));
            List<String> list = new ArrayList<>();
            for (int index : arr) {
                String[] t = new String[max];
                Arrays.fill(t, ".");
                t[index] = "Q";
                String s = String.join("", t);
                list.add(s);
            }
            res.add(list);

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
        Test51 t = new Test51();
        System.out.println(t.solveNQueens(4));
    }
}
