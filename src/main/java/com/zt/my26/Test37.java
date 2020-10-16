package com.zt.my26;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-10 15:34
 */
public class Test37 {
    HashMap<Character, Integer>[] rows = new HashMap[9];
    HashMap<Character, Integer>[] cols = new HashMap[9];
    HashMap<Character, Integer>[] subs = new HashMap[9];
    public void solveSudoku(char[][] board) {
        init(board);
        func(0,0,board);
    }

    private void init(char[][] board) {
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<>();
            cols[i] = new HashMap<>();
            subs[i] = new HashMap<>();
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    rows[i].put(board[i][j], 1);
                    cols[j].put(board[i][j], 1);
                    subs[get(i, j)].put(board[i][j], 1);
                }
            }
        }
    }

    public boolean func(int row, int col, char[][] data) {
        if (row >= 9) return true;
        int nextCol = col + 1;
        int nextRow = row;
        if (col == 8) {
            nextCol = 0;
            nextRow = row + 1;
        }
        if (data[row][col] != '.') {
            return func(nextRow, nextCol, data);
        } else {
            for (int i = 1; i < 10; i++) {
                data[row][col] = (char)(i+48);
                boolean b = checkAndPut(row, col, data);
                if (b){
                    boolean b1 = func(nextRow, nextCol, data);
                    if (b1)return true;
                    else {
                        cancel(row, col, data);
                    }
                }else data[row][col] = '.';
            }
        }
        return false;
    }
    boolean checkAndPut(int row, int col, char[][] data){
        char c = data[row][col];
        int e1 = rows[row].getOrDefault(c, 0);
        if (e1 > 0)return false;
        int e2 = cols[col].getOrDefault(c,0);
        if (e2 > 0)return false;
        int e3 = subs[get(row,col)].getOrDefault(c,0);
        if (e3 > 0)return false;
        rows[row].put(c, 1);
        cols[col].put(c, 1);
        subs[get(row, col)].put(c, 1);
        return true;
    }
    void cancel(int row, int col, char[][] data){
        rows[row].put(data[row][col], rows[row].getOrDefault(data[row][col],1) - 1);
        cols[col].put(data[row][col], cols[col].getOrDefault(data[row][col],1) - 1);
        subs[get(row, col)].put(data[row][col], subs[get(row, col)].getOrDefault(data[row][col],1) - 1);
        data[row][col] = '.';
        //rows[row].put(data[row][col], rows[row].getOrDefault(data[row][col],1) - 1);
        //cols[col].put(data[row][col], cols[col].getOrDefault(data[row][col],1) - 1);
        //subs[get(row, col)].put(data[row][col], subs[get(row, col)].getOrDefault(data[row][col],1) - 1);
    }
    private int get(int row, int col) {
        int subRow = (row / 3);
        int subCol = (col / 3);
        if (subRow == 0 && subCol == 0) return 0;
        if (subRow == 0 && subCol == 1) return 1;
        if (subRow == 0 && subCol == 2) return 2;
        if (subRow == 1 && subCol == 0) return 3;
        if (subRow == 1 && subCol == 1) return 4;
        if (subRow == 1 && subCol == 2) return 5;
        if (subRow == 2 && subCol == 0) return 6;
        if (subRow == 2 && subCol == 1) return 7;
        if (subRow == 2 && subCol == 2) return 8;
        return -1;
    }

    public static void main(String[] args) {
        Test37_1 test37 = new Test37_1();
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
        test37.solveSudoku(board);
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }
}
class Test37_1 {
    HashMap<Character, Integer>[] rows = new HashMap[9];
    HashMap<Character, Integer>[] cols = new HashMap[9];
    HashMap<Character, Integer>[][] subs = new HashMap[3][3];
    public void solveSudoku(char[][] board) {
        init(board);
        func(0,0,board);
    }

    private void init(char[][] board) {
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<>();
            cols[i] = new HashMap<>();

        }
        for (int i = 0; i < subs.length; i++) {
            for (int j = 0; j < subs[i].length; j++) {
                subs[i][j] = new HashMap<>();
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    rows[i].put(board[i][j], 1);
                    cols[j].put(board[i][j], 1);
                    subs[i/3][j/3].put(board[i][j], 1);
                }
            }
        }
    }

    public boolean func(int row, int col, char[][] data) {
        if (row >= 9) return true;
        int nextCol = col + 1;
        int nextRow = row;
        if (col == 8) {
            nextCol = 0;
            nextRow = row + 1;
        }
        if (data[row][col] != '.') {
            return func(nextRow, nextCol, data);
        } else {
            for (int i = 1; i < 10; i++) {
                data[row][col] = (char)(i+48);
                boolean b = checkAndPut(row, col, data);
                if (b){
                    boolean b1 = func(nextRow, nextCol, data);
                    if (b1)return true;
                    else {
                        cancel(row, col, data);
                    }
                }else data[row][col] = '.';
            }
        }
        return false;
    }
    boolean checkAndPut(int row, int col, char[][] data){
        char c = data[row][col];
        int e1 = rows[row].getOrDefault(c, 0);
        if (e1 > 0)return false;
        int e2 = cols[col].getOrDefault(c,0);
        if (e2 > 0)return false;
        int e3 = subs[row/3][col / 3].getOrDefault(c,0);
        if (e3 > 0)return false;
        rows[row].put(c, 1);
        cols[col].put(c, 1);
        subs[row/3][col / 3].put(c, 1);
        return true;
    }
    void cancel(int row, int col, char[][] data){
        rows[row].put(data[row][col], rows[row].getOrDefault(data[row][col],1) - 1);
        cols[col].put(data[row][col], cols[col].getOrDefault(data[row][col],1) - 1);
        subs[row/3][col / 3].put(data[row][col], subs[row/3][col / 3].getOrDefault(data[row][col],1) - 1);
        data[row][col] = '.';
        //rows[row].put(data[row][col], rows[row].getOrDefault(data[row][col],1) - 1);
        //cols[col].put(data[row][col], cols[col].getOrDefault(data[row][col],1) - 1);
        //subs[get(row, col)].put(data[row][col], subs[get(row, col)].getOrDefault(data[row][col],1) - 1);
    }

}
