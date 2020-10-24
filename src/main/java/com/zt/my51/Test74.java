package com.zt.my51;

import java.util.Arrays;

/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 3
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 13
 * 输出：false
 * 示例 3：
 *
 * 输入：matrix = [], target = 0
 * 输出：false
 *  
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 0 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-23 12:19
 */
public class Test74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        try {
            int rowNum = matrix.length;
            int colNum = matrix[0].length;
            int left = 0, right = rowNum * colNum - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int row = mid / colNum;
                int col = mid - (row * colNum);
                if (matrix[row][col] == target) return true;
                else if (matrix[row][col] > target) right = mid - 1;
                else left = mid + 1;
            }
            return false;
        }catch (Exception e) {
            return false;
        }

    }

    public static void main(String[] args) {
        Test74 t = new Test74();
        int[][] matrix = new int[][]{
                {1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}
        };
        System.out.println(t.searchMatrix(matrix,21));
    }
}
