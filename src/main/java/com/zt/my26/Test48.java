package com.zt.my26;

import java.util.Arrays;

/**
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * <p>
 * 将图像顺时针旋转 90 度。
 * <p>
 * 说明：
 * <p>
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * <p>
 * 示例 1:
 * <p>
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * 示例 2:
 * <p>
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-image
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * PASS
 * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗：38.6 MB, 在所有 Java 提交中击败了97.68%的用户
 * 使用arr数组按顺序储存每一圈的数据
 * direction代表方向 栓转九十度就是把 direction置为 1 代表初始时向下走
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-15 15:09
 */
public class Test48 {
    int count = 0; // 有几个环 距离最右边有几列

    public void rotate(int[][] matrix) {

        int width = matrix.length;
        int start = 1;
        func(matrix, width, start);

    }

    private void func(int[][] matrix, int width, int start) {
        if (width <= 1) return;
        int direction = 0; // 0 向右 1 向下 2 向左 3 向上
        int init = start;
        int len = (width - 1) * 4;
        int[] arr = new int[len];
        int e = matrix.length - count; //该轮环的最右边位于第几列 + 1
        for (int i = 0; i < len; i++) {
            switch (direction % 4) {
                case 0:
                    arr[i] = matrix[init - 1][start];
                    break;
                case 1:
                    arr[i] = matrix[start][e - 1];
                    break;
                case 2:
                    arr[i] = matrix[e - 1][e - 1 - start + count];
                    break;
                case 3:
                    arr[i] = matrix[e - 1 - start + count][init - 1];
            }
            start++;
            if (start >= e) {
                start = init;
                direction++;
            }
        }
        direction = 1;
        for (int i = 0; i < len; i++) {
            int value = arr[i];
            switch (direction % 4) {
                case 0:
                    matrix[init - 1][start] = value;
                    break;
                case 1:
                    matrix[start][e - 1] = value;
                    break;
                case 2:
                    matrix[e - 1][e - 1 - start + count] = value;
                    break;
                case 3:
                    matrix[e - 1 - start + count][init - 1] = value;
            }
            start++;
            if (start >= e) {
                start = init;
                direction++;
            }
        }
        count++;
        func(matrix, width - 2, start + 1);
    }

    public static void main(String[] args) {
        Test48 t = new Test48();
        //int[][] matrix = new int[][]{
        //        {1,2,3},
        //        {4,5,6},
        //        {7,8,9}
        //};
        //int[][] matrix = new int[][]{
        //        {5, 1, 9, 11},
        //        {2, 4, 8, 10},
        //        {13, 3, 6, 7},
        //        {15, 14, 12, 16}
        //};
        int n = 2;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = i * n + j + 1;
            }
        }
        t.rotate(matrix);


    }
}
