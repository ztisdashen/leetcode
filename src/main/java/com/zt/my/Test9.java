package com.zt.my;

import java.util.SortedMap;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 * <p>
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-15 21:06
 */
public class Test9 {
    public boolean isPalindrome(int x) {
        String s = x + "";
        char[] chars = s.toCharArray();
        return check(chars, 0, chars.length);
    }

    /**
     * 检查是不是回文
     *
     * @return
     */
    private boolean check(char[] chars, int left, int right) {
        int r = right - 1;
        int l = left;
        while (l <= r) {
            if (chars[l] != chars[r])
                return false;
            l++;
            r--;
        }
        return true;
    }

    public static void main(String[] args) {
        Test9_2 t = new Test9_2();
        boolean b = t.isPalindrome(1874994781);
        System.out.println(b);
    }
}

class Test9_1 {
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        if (x == 0)
            return true;
        int reverse = 0;
        int temp = x;
        while (temp > 0) {
            reverse = temp % 10 + reverse * 10;
            temp /= 10;
        }
        return reverse == x;
    }
}

class Test9_2 {
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        if (x == 0)
            return true;
        if (x < 10)
            return true;
        if (x % 10 == 0)return false;
        long i = 1;
        int j = 1;
        long temp = x;
        while (i * 10 < x) i *= 10;
        System.out.println(i);
        while (i > j) {
            long left = temp / i;
            int right = (x / j) % 10;
            if (left == right) {
                temp = temp - i * left;
                i /= 10;
                j *= 10;
            } else {
                return false;
            }
        }
        return true;
    }
}
