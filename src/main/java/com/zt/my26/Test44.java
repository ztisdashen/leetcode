package com.zt.my26;

import java.util.Arrays;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 * <p>
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 * <p>
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 * <p>
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wildcard-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * PASS
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-14 15:32
 */
public class Test44 {
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) return true;
        if (p.equals("*")) return true;
        if (s.length() == 0) return false;
        boolean[][] matrix = new boolean[p.length() + 1][s.length()];
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '*') {
                if (i == 0) {
                    matrix[i + 1][0] = true;
                } else {
                    matrix[i + 1][0] = matrix[i][0];
                }
            } else {
                if ((c == '?' || c == s.charAt(0))) {
                    matrix[i + 1][0] = true;
                    for (int j = 0; j < i; j++) {
                        if (p.charAt(j) != '*') {
                            matrix[i + 1][0] = false;
                            break;
                        }
                    }
                }
            }
        }
        for (int i = 1; i <= p.length(); i++) {
            char c = p.charAt(i - 1);
            for (int j = 1; j < s.length(); j++) {
                // 下面的if注释也被优化
                //if (c >= 97 && c <= 122) {
                //    matrix[i][j] = s.charAt(j) == c && matrix[i - 1][j - 1];
                //} else
                    if (c == '*') {
                    // 下面被注释是旧版本
                    //if (matrix[i][j - 1]) matrix[i][j] = true;
                    //else {
                    //    for (int k = j; k >= 0; k--) {
                    //        if (matrix[i - 1][k]) {
                    //            matrix[i][j] = true;
                    //            break;
                    //        }
                    //    }
                    //}
                    // 根据官网给定的优化版本 PASS 80%
                    // matrix[i - 1][j] 代表 * 匹配 0个字符串
                    // matrix[i][j - 1] 代表 * 匹配多个字符串
                    matrix[i][j] = matrix[i][j - 1] || matrix[i - 1][j];
                } else if (c == '?' || c == s.charAt(j)) {
                    matrix[i][j] = matrix[i - 1][j - 1];
                }
            }

        }
        System.out.println("=============");
        for (boolean[] booleans : matrix) {
            System.out.println(Arrays.toString(booleans));
        }
        return matrix[p.length()][s.length() - 1];
    }

    public static void main(String[] args) {
        Test44 t = new Test44();
        //t.isMatch("acdcb", "a*c?b");
        //System.out.println();
        t.isMatch("aba", "a*");
        //t.isMatch("aa", "*");
        //t.isMatch("cd","?s");
        //System.out.println((int)'z');
    }
}

class Test44_1 {
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) return true;
        if (p.equals("*")) return true;
        if (s.length() == 0) return false;
        boolean[][] matrix = new boolean[p.length() + 1][s.length()];
        int bb = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '*') {
                if (i == 0) {
                    matrix[i + 1][0] = true;
                } else {
                    matrix[i + 1][0] = matrix[i][0];
                }
            } else {
                if ((c == '?' || c == s.charAt(0)) && bb == 0) {
                    matrix[i + 1][0] = true;
                }
                bb++;
            }
        }
        for (int i = 1; i <= p.length(); i++) {
            char c = p.charAt(i - 1);
            for (int j = 1; j < s.length(); j++) {
                if (c >= 97 && c <= 122) {
                    matrix[i][j] = s.charAt(j) == c && matrix[i - 1][j - 1];
                } else if (c == '*') {
                    if (matrix[i][j - 1]) matrix[i][j] = true;
                    else {
                        for (int k = j; k >= 0; k--) {
                            if (matrix[i - 1][k]) {
                                matrix[i][j] = true;
                                break;
                            }
                        }
                    }
                } else if (c == '?') {
                    matrix[i][j] = matrix[i - 1][j - 1];
                }
            }

        }
        for (boolean[] booleans : matrix) {
            System.out.println(Arrays.toString(booleans));
        }
        return matrix[p.length()][s.length() - 1];
    }

    public static void main(String[] args) {
        Test44_1 t = new Test44_1();
        //t.isMatch("aab","c*a*b");
        t.isMatch(
                "mississippi",
                "m??*ss*?i*pi");
    }
}
