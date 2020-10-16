package com.zt.my26;

import com.zt.my.Test3;

import java.util.HashMap;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-10 14:36
 */
public class Test36 {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    boolean b = doCheck(board[i][j], i, j, board);
                    if (!b){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean doCheck(char num, int row, int col, char[][] data) {
        char[] dataRow = data[row];
        for (int i = 0; i < dataRow.length; i++) {
            if (i == col)continue;
            if (dataRow[i] == num)return false;
        }
        for (int i = 0; i < 9; i++) {
            if (i == row)continue;
            if (data[i][col] == num) return false;
        }
        int subRow = (row / 3) * 3;
        int subCol = (col / 3) * 3;
        for (int i = subRow; i < subRow + 3; i++) {
            for (int j = subCol; j < subCol + 3; j++) {
                if (i == row && j == col)continue;
                if (data[i][j] == num) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Test36 test36 = new Test36();
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
        };
        System.out.println(test36.isValidSudoku(board));
    }
}

/**
 * 分治
 */
class Test36_div{
    public boolean isValidSudoku(char[][] board) {
        return false;
    }
    public boolean checkSub(int number,int row,int col,int[][] data){
        int subRow = (row / 3) * 3;
        int subCol = (col / 3) * 3;
        HashMap<Integer,Boolean> map = new HashMap<>();
        for (int i = subRow; i < subRow + 3; i++) {
            for (int j = subCol; j < subCol + 3; j++) {
                if (i == row && j == col)continue;
                Boolean b = map.getOrDefault(i, false);
                if (b)return false;
                else map.put(i,true);
            }
        }
        return true;
    }
    public boolean doSomething(int num,int[][]data,int size){
        return false;
    }
}
