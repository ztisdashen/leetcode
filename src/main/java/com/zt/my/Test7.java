package com.zt.my;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 *  示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * 注意:
 * <p>
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-15 08:48
 */
public class Test7 {
    public int reverse(int x) {
        int sign = 1;
        String xVal = x + "";
        if (x == 0) {
            return 0;
        }
        if (x < 0) {
            sign = -1;
            xVal = xVal.substring(1);
        }
        String reverse = new StringBuilder(xVal).reverse().toString();
        long aLong = Long.parseLong(reverse) * sign;
        if (sign < 0 && aLong < Integer.MIN_VALUE)
            return 0;
        if (sign > 0 && aLong > Integer.MAX_VALUE)
            return 0;
        return (int) aLong;
    }

    public static void main(String[] args) {
        Test7_3 t = new Test7_3();
        System.out.println(t.reverse(Integer.MAX_VALUE));
    }
}

class Test7_2 {
    public int reverse(int x) {
        if (x == 0)
            return x;
        long max = 1;
        int sign = x / Math.abs(x);
        x = Math.abs(x);
        Queue<Integer> queue = new LinkedList<>();
        while (x > 0) {
            int last = x % 10;
            x /= 10;
            max *= 10;
            queue.add(last);
        }
        max /= 10;
        long res = 0;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            res += (poll * max);
            max /= 10;
        }
        res = res * sign;
        if (sign < 0 && res < Integer.MIN_VALUE)
            return 0;
        if (sign > 0 && res > Integer.MAX_VALUE)
            return 0;
        return (int) res;
    }
}

class Test7_3 {
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = x % 10 + res * 10;
            x /= 10;
        }
        int i = (int) res;
        return i == res ? i : 0;
    }
}
