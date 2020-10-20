package com.zt.my51;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 *
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-18 21:55
 * PASS 100%
 */
public class Test59 {
    int count = 0;
    public int[][] generateMatrix(int n) {
        if (n == 1)return new int[][]{{1}};
        int[][] matrix = new int[n][n];
        func(matrix,n,0,1);

        return matrix;
    }

    private void func(int[][] matrix, int size, int start, int startNum) {
        if (size <=0)return;
        int len = (size - 1) * 4;
        if (len == 0){
            matrix[start][start] = startNum;
            return;
        }
        int rightEnd = size + count;
        int direction = 0;
        int intStart = start;
        for (int i = 0; i < len; i++) {
            switch (direction % 4) {
                case 0:
                    matrix[intStart][start] = startNum++;
                    break;
                case 1:
                    matrix[start][rightEnd - 1]= startNum++;
                    break;
                case 2:
                    matrix[rightEnd - 1][rightEnd - 1 - start + count]= startNum++;
                    break;
                case 3:
                    matrix[rightEnd - 1 - start + count][intStart]= startNum++;
            }
            start++;
            if (start >= rightEnd-1) {
                start = intStart;
                direction++;
            }
        }
        count++;
        func(matrix,size - 2,start+1,startNum);
    }

    public static void main(String[] args) {
        Test59 t = new Test59();
        int[][] matrix = t.generateMatrix(3);
        for (int[] ints : matrix) {
            for (int i : ints) {
                System.out.printf("%d\t",i);
            }
            System.out.println();
        }
    }
}
