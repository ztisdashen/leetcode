package com.zt.other;

/**
 * 该算法是开始于对chars的遍历
 * 在遍历过程中 从当前的index出发，
 * 通过下面的代码
 * while (high < str.length - 1 && str[high + 1] == str[low]) {
 * high++;
 * }
 * 向后遍历寻找类似于aaaaa、bbbb这种连续的结构，这种显然是一种回文，是符合提议的
 * 在这个过程中改变了high 确保指向相同字符串的最后一个的索引
 * 同时上面这个代码也解决了奇偶的问题
 * 当回文是偶数时中间部分显然满足类似于aa的结构
 * 当是奇数是当回文中间是aaa的结构 显然能够处理
 * 当中间是一个单a的结构时，假设指向a的索引为 10 显然当结束上面的代码时 low = high = 10
 * 下面的代码就会从low - 1 = 9 和high + 1 = 11开始判断
 * while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
 *      low--;
 *      high++;
 * }
 *
 * 在 findLongest 不能直接返回 high 当遇到 "bananas"时 就会跳过中间 a
 * 在寻找回文的时候不能跳跃着找 但是当遇到类似于 aaaa 的结构时 可以跳跃 将其看成一个字母
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-14 19:29
 */
public class Solution5 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        //保存起始位置，储存回文的low和high
        int[] range = new int[2];
        char[] str = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            // 把回文看成中间的部分全是同一字符，左右部分相对称
            // 找到下一个与当前字符不同的字符
            i = findLongest(str, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }

    public static int findLongest(char[] str, int low, int[] range) {
        // 查找中间部分
        int high = low;
        while (high < str.length - 1 && str[high + 1] == str[low]) {
            high++;
        }
        // 定位中间部分的最后一个字符
        int ans = high;
        // 从中间向左右扩散
        while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
            low--;
            high++;
        }
        //  记录最大长度
        if (high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution5 s = new Solution5();
        System.out.println(s.longestPalindrome("bananas"));
    }
}


