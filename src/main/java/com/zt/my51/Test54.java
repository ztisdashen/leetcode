package com.zt.my51;

import com.zt.my26.Test48;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatCodePointException;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * <p>
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-18 15:11
 */
public class Test54 {
    int count = 0;
    List<Integer> list;

    public List<Integer> spiralOrder(int[][] matrix) {
        list = new ArrayList<>();
        if (matrix.length == 0) return list;
        // matrix.length * matrix[0].length
        int width = matrix[0].length;
        int high = matrix.length;
        func(matrix, width, high, 0);
        //if (width == high && width % 2 != 0) list.add(matrix[high / 2][width / 2]);
        return list;
    }

    void func(int[][] matrix, int width, int high, int start) {
        if (width <= 0 || high <= 0) return;
        int rightEnd = width + count;
        int bottomEnd = high + count;
        if (width == 1) {
            for (int i = start; i < bottomEnd ; i++) {
                list.add(matrix[i][start]);
            }
            return;
        } else if (high == 1) {
            for (int i = start; i < rightEnd; i++) {
                list.add(matrix[start][i]);
            }
            return;
        }
        int direction = 0;
        int len = (width + high) * 2 - 4;
        int tempStart = start;
        for (int i = 0; i < len; i++) {
            switch (direction % 4) {
                case 0:
                    list.add(matrix[tempStart][start]);
                    break;
                case 1:
                    list.add(matrix[start][rightEnd - 1]);
                    break;
                case 2:
                    list.add(matrix[bottomEnd - 1][rightEnd - 1 - start + count]);
                    break;
                case 3:
                    list.add(matrix[bottomEnd - 1 - start + count][tempStart]);
            }
            start++;
            if (direction % 2 == 0 && start == rightEnd - 1) {
                start = tempStart;
                direction++;
            } else if (direction % 2 == 1 && start == bottomEnd - 1) {
                start = tempStart;
                direction++;
            }
        }
        count++;
        func(matrix, width - 2, high - 2, start + 1);
    }

    public static void main(String[] args) {
        Test54 t = new Test54();
        int width = 4;
        int high = 3;
        int[][] matrix = new int[high][width];
        for (int i = 0; i < high; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = i * high + j + 1;
            }
        }
        for (int[] ints : matrix) {
            for (int i : ints) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }
        System.out.println();
       //matrix = new int[][]{
       //         {3}, {2}
       // };
        System.out.println(t.spiralOrder(matrix));

    }
}
