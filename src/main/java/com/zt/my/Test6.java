package com.zt.my;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * <p>
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-14 21:33
 */
public class Test6 {
    public String convert(String s, int numRows) {
        if (s==null || s.length() == 0){
            return "";
        }
        if (numRows == 1)
            return s;
        /*
         * L
         * E   O
         * E C
         * T
         */
        int round = 2 * numRows - 2;
        int roundWidth = numRows - 1;
        int length = s.length();
        int col; // 列数
        if (length % round == 0) {
            col = (length / round) * roundWidth;
        } else {
            col = ((length / round) + 1) * roundWidth;
        }
        char[][] sMatrix = new char[numRows][col];
        int startRolNum = 0;
        for (int i = 0; i < length; i = i + round){
            String substring;
            if (i + round > length) {
                substring = s.substring(i);
            } else {
                substring = s.substring(i, i + round);
            }
            char[] chars = substring.toCharArray();
            int charIndex = 0;
            // 处理竖列
            for (int j = 0; charIndex < chars.length && j < numRows; j++) {
                sMatrix[j][startRolNum] = chars[charIndex];
                charIndex++;
            }
            startRolNum++;
            // 处理斜的
            int tem = numRows-2;
            while (charIndex < chars.length){
                sMatrix[tem][startRolNum] = chars[charIndex];
                startRolNum++;
                tem--;
                charIndex++;
            }
        }
        StringBuilder res = new StringBuilder();
        for (char[] chars : sMatrix) {
            for (char c : chars) {
                if (c!=0){
                    res.append(c);
                }
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Test6 test6 = new Test6();
        System.out.println(test6.convert("LEETCODEISHIRING",3));
    }
}
