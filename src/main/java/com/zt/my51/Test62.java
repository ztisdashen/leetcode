package com.zt.my51;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-19 20:08
 * Out Of Time
 */
public class Test62 {
    int cols, rows;
    int count;

    public int uniquePaths(int m, int n) {
        cols = m;
        rows = n;
        func(1, 1);
        return count;
    }

    public void func(int startX, int startY) {
        if (startY == rows || startX == cols) {
            count++;
            return;
        }
        func(startX, startY + 1);
        func(startX + 1, startY);
    }

    public static void main(String[] args) {
        Test62 t = new Test62();
        System.out.println(t.uniquePaths(5, 3));
    }
}

/**
 * DP算法 起点S 终点D要么是从上面来A 要么是从左边B来
 * 而从A到D只有一条 从B到D只有一条
 * 因此到D的路径数就是S到A的路径数 + S到B的路径数
 */
class Test62_1 {
    public int uniquePaths(int m, int n) {
        int[][] matrix = new int[n][m];
        Arrays.fill(matrix[0], 1);
        for (int i = 0; i < n; i++) {
            matrix[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[j][i] = matrix[j][i - 1] + matrix[j - 1][i];
            }
        }
        for (int[] ints : matrix) {
            for (int i : ints) {
                System.out.printf("%-7d", i);
            }
            System.out.println();
        }
        return matrix[n - 1][m - 1];
    }

    public static void main(String[] args) {
        Test62_1 t = new Test62_1();
        t.uniquePaths(10, 10);
    }
}

// m + n - 2步 挑出m - 1步向右走
class Test62_2 {
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;
        long[] p = new long[m + n - 1];
        p[0] = 1;
        calcN(m + n - 2, p);

        return (int) (p[m + n - 2] / (p[m - 1] * p[n - 1]));
    }

    long calcN(int n, long[] arr) {
        if (n == 1) {
            arr[n] = 1;
            return 1;
        }
        long res = n * calcN(n - 1, arr);
        arr[n] = res;
        return res;
    }

    public static void main(String[] args) {
        Test62_2 t = new Test62_2();
        System.out.println(t.uniquePaths(10, 10));
    }
}
