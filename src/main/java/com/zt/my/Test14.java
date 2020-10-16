package com.zt.my;


/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-22 21:57
 */
public class Test14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        int minLength = Integer.MAX_VALUE;
        String minStr = "";
        for (String s : strs) {
            if (s.length() < minLength) {
                minLength = s.length();
                minStr = s;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            char c = minStr.charAt(i);
            for (String s : strs) {
                if (s.charAt(i) != c) {
                    return result.toString();
                }
            }
            result.append(c);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Test14_1 t = new Test14_1();
        System.out.println(t.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }
}

class Test14_1 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        StringBuilder result = new StringBuilder();
        try {
            String minStr = strs[0];
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                char c = minStr.charAt(i);
                for (String s : strs) {
                    if (s.charAt(i) != c) {
                        return result.toString();
                    }
                }
                result.append(c);
            }
        } catch (IndexOutOfBoundsException e) {
            return result.toString();
        }
        return result.toString();
    }

}
