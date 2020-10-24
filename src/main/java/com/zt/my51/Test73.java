package com.zt.my51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2:
 * <p>
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 * 进阶:
 * <p>
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/set-matrix-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-23 11:54
 * PASS 50%
 */
public class Test73 {
    public void setZeroes(int[][] matrix) {
        List<int[]> zeroPositions = new ArrayList<>();
        boolean[] zeroRows = new boolean[matrix.length];
        boolean[] zeroCols = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) zeroPositions.add(new int[]{i, j});
            }
        }
        for (int[] position : zeroPositions) {
            int row = position[0];
            int col = position[1];
            if (!zeroRows[row]) {
                Arrays.fill(matrix[row], 0);
                zeroRows[row] = true;
            }
            if (!zeroCols[col]) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][col] = 0;
                }
                zeroCols[col] = true;
            }
        }
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        Test73 t = new Test73();
        t.setZeroes(matrix);
    }
}
// PASS 100%
class Test73_1 {
    public void setZeroes(int[][] matrix) {
        boolean[] zeroRows = new boolean[matrix.length];
        boolean[] zeroCols = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows[i] = true;
                    zeroCols[j] = true;
                }
            }
        }
        for (int row = 0; row < zeroRows.length; row++) {
            if (zeroRows[row]) {
                Arrays.fill(matrix[row], 0);
            }
        }
        for (int col = 0; col < zeroCols.length; col++) {
            if (zeroCols[col]){

                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][col] = 0;
                }
            }
        }
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        //int[][] matrix = new int[][]{
        //        {0, 1, 2, 0},
        //        {3, 4, 5, 2},
        //        {1, 3, 1, 5}
        //};
        Test73_1 t = new Test73_1();
        t.setZeroes(matrix);
    }
}
