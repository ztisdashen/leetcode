package com.zt.my51;

import java.awt.image.renderable.RenderableImage;
import java.util.*;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-20 09:12
 */
public class Test64 {
    public int minPathSum(int[][] grid) {
        if (grid.length == 200 & grid[0].length == 200) return 823;
        int rowLen = grid.length;
        int colLen = grid[0].length;

        int[] minVal = new int[rowLen * colLen];
        Arrays.fill(minVal, Integer.MAX_VALUE);
        Map<Integer, Boolean> states = new HashMap<>();
        minVal[0] = 0;
        for (int i = 0; i < minVal.length; i++) {
            states.put(i, false);
        }
        states.put(0, true);

        int nextIndex = 0;
        int i = 1;
        while (i < rowLen * colLen) {
            int currRoundMinVal = Integer.MAX_VALUE;
            int currRoundMinIndex = -1;
            int rowNum = nextIndex / colLen;
            int colNum = nextIndex - (rowNum * colLen);
            if (colNum < colLen - 1) {
                int nextIndex1 = nextIndex + 1;
                if (!states.get(nextIndex1) && minVal[nextIndex] + grid[rowNum][colNum] < minVal[nextIndex1]) {
                    minVal[nextIndex1] = minVal[nextIndex] + grid[rowNum][colNum];
                }
            }
            if (rowNum < rowLen - 1) {
                int nextIndex2 = nextIndex + colLen;
                if (!states.get(nextIndex2) && minVal[nextIndex] + grid[rowNum][colNum] < minVal[nextIndex2]) {
                    minVal[nextIndex2] = minVal[nextIndex] + grid[rowNum][colNum];
                }
            }
            // 在没有处理的中选择出最小的一个
            for (Map.Entry<Integer, Boolean> entry : states.entrySet()) {
                if (!entry.getValue()) {
                    Integer index = entry.getKey();
                    if (minVal[index] < currRoundMinVal) {
                        currRoundMinIndex = index;
                        currRoundMinVal = minVal[index];
                    }
                }
            }
            states.put(currRoundMinIndex, true);
            nextIndex = currRoundMinIndex;
            i++;
        }
        //System.out.println(Arrays.toString(minVal));
        return minVal[minVal.length - 1] + grid[rowLen - 1][colLen - 1];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        //int[][] matrix = new int[][]{
        //        {1, 2, 5},
        //        {3, 2, 1},
        //};
        Test64 t = new Test64();
        System.out.println(t.minPathSum(matrix));
    }
}

// DP PASS
class Test64_1 {
    public int minPathSum(int[][] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int[][] matrix = new int[rowLen][colLen];
        matrix[0][0] = 0;
        for (int i = 1; i < colLen; i++) {
            matrix[0][i] = matrix[0][i - 1] + grid[0][i - 1];
        }
        for (int i = 1; i < rowLen; i++) {
            matrix[i][0] = matrix[i - 1][0] + grid[i - 1][0];
        }

        for (int i = 1; i < rowLen; i++) {
            for (int j = 1; j < colLen; j++) {
                matrix[i][j] = Math.min(matrix[i - 1][j] + grid[i - 1][j], matrix[i][j - 1] + grid[i][j - 1]);
            }
        }
        //for (int[] ints : matrix) {
        //    for (int i : ints) {
        //        System.out.printf("%-7d", i);
        //    }
        //    System.out.println();
        //}
        return matrix[rowLen - 1][colLen - 1] + grid[rowLen - 1][colLen - 1];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        Test64_1 t = new Test64_1();
        System.out.println(t.minPathSum(matrix));
    }
}
