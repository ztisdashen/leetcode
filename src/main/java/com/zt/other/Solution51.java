package com.zt.other;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-17 16:49
 */
public class Solution51 {
    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> solutions = new ArrayList<>();
        solve(solutions, queens, n, 0, 0, 0, 0);
        return solutions;
    }

    public void solve(List<List<String>> solutions, int[] queens, int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            //可以通过 (2^n-1)~\&~(\sim(\textit{columns} | \textit{diagonals}_1 | \textit{diagonals}_2))(2
            //n−1) & (∼(columns∣diagonals1|diagonals2​)) 得到可以放置皇后的位置（该结果的值为 1的位置表示可以放置皇后的位置），然后遍历这些位置，尝试放置皇后并得到可能的解。
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
            while (availablePositions != 0) {
                int position = availablePositions & (-availablePositions);
                availablePositions = availablePositions & (availablePositions - 1);
                int column = Integer.bitCount(position - 1); // 有多少个 1 
                queens[row] = column;
                solve(solutions, queens, n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
                queens[row] = -1;
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    public static void main(String[] args) {
        int i = 3;
        System.out.println(Integer.bitCount(i));
    }
}
