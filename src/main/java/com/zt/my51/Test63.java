package com.zt.my51;

import java.util.Arrays;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-20 08:32
 * 最短路径 out of time
 */

public class Test63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] matrix = new int[n][m];
        for (int i = 0; i < matrix[0].length; i++) {
            if (obstacleGrid[0][i]!=1){
                matrix[0][i] = 1;
            }else break;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[i][0]!=1){
                matrix[i][0] = 1;
            }else break;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[j][i] != 1) {
                    matrix[j][i] = matrix[j][i - 1] + matrix[j - 1][i];
                }
            }
        }
        //for (int[] ints : matrix) {
        //    for (int i : ints) {
        //        System.out.printf("%-7d", i);
        //    }
        //    System.out.println();
        //}
        return matrix[n - 1][m - 1];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1}
                //{0, 0, 0},
                //{0, 1, 0},
                //{0, 0, 0}
        };
        Test63 t = new Test63();
        t.uniquePathsWithObstacles(matrix);
    }
}
