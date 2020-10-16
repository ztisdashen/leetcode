package com.zt.my26;

import com.zt.other.Solution32;

import java.awt.*;
import java.util.Arrays;

/**
 * 利用并查集
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-29 13:59
 */
public class Test32 {
    public int longestValidParentheses(String s) {
        if (s.length() <= 1) return 0;
        int[] groups = new int[s.length()];
        char[] chars = s.toCharArray();
        Arrays.fill(groups, -1);
        int[] ints = func(chars, 0, groups);
        if (ints == null) return 0;
        System.out.println(Arrays.toString(groups));
        // 处理 ((()()))这种情况
        while (true){
            int[] next = next(-1, groups);
            if (next!=null){
                boolean b = doSomething(next[0], next[1], chars, groups);
                if (!b)break;
            }else {
                break;
            }
        }
        int count = 0;

        for (int i = 0; i < groups.length; i++) {
            if (groups[i] > -1) {
                int c = 0;
                for (; i < groups.length; i++) {
                    if (groups[i] > -1) {
                        c++;
                    } else break;
                }
                if (c > count) count = c;
            }
        }
        return count;
    }

    public int[] func(char[] chars, int starIndex, int[] groups) {
        if (starIndex >= chars.length - 1) return null;
        int begin = -1;
        for (int i = starIndex; i < chars.length - 1; i++) {
            if (chars[i] == '(' && chars[i + 1] == ')') {
                begin = i;
                break;
            }
        }
        if (begin == -1) return null; // 没有找到成对的括号
        int left = begin;
        int right = begin + 1;
        groups[left] = 0;
        groups[right] = left;
        while (left - 1 >= 0 && right + 1 < chars.length && chars[left - 1] == '(' && chars[right + 1] == ')') {
            left--;
            right++;
            groups[left] = left + 1;
            groups[right] = right - 1;
        }

        int[] ints = func(chars, right + 1, groups);
        int[] res = new int[2];
        res[0] = left;
        res[1] = right;
        if (ints != null && ints[0] == right + 1) {
            int tem = right + 1;
            while (tem < chars.length && groups[tem] != 0) {
                tem = groups[tem];
            }
            groups[tem] = groups[right];
            res[1] = ints[1];
        }
        return res;
    }


    public boolean doSomething(int left, int right, char[] chars, int[] groups) {
        if (right >= chars.length || left <0)return false;
        boolean change = false;
        int[] ints = way1(left, right, chars, groups);
        if (ints[1] != right) {
            //代表向两侧发成了扩展
            // 可能会与右侧发生union
            int[] way2 = way2(ints[0], ints[1], chars, groups);
            left = way2[0];
            right = way2[1];
            change = true;
        }
        int[] next = next(right, groups);
        boolean nextChange = false;
        if (next != null){
            // 存在下一个组
            nextChange = doSomething(next[0],next[1],chars,groups);
        }
        return nextChange || change;
    }

    public int[] way1(int left, int right, char[] chars, int[] groups) {
        while (left - 1 >= 0 && right + 1 < chars.length && chars[left - 1] == '(' && chars[right + 1] == ')') {
            left--;
            right++;
            groups[left] = left + 1;
            groups[right] = right - 1;
        }
        return new int[]{left, right};
    }

    /**
     * 从 right + 1 扩展
     *
     * @param left
     * @param right
     * @param chars
     * @param groups
     * @return
     */
    public int[] way2(int left, int right, char[] chars, int[] groups) {
        if (right + 1 < chars.length) {
            if (groups[right + 1] != -1) {
                int tem = right + 1;
                while (tem < chars.length && groups[tem] != 0) {
                    tem = groups[tem];
                }
                groups[tem] = groups[right];
                for (int i = right + 3; i < chars.length; i++) {
                    if (groups[i] < 0) {
                        right = i - 1;
                        break;
                    }
                }
            }
        }
        return new int[]{left, right};
    }

    /**
     * 找到下一个 group 没有 则返回null;
     *
     * @param lastRight
     * @param groups
     * @return
     */
    public int[] next(int lastRight, int[] groups) {
        int t = lastRight;
        int left = lastRight;
        for (int i = lastRight + 1; i < groups.length; i++) {
            if (groups[i] >= 0) {
                left = i;
                for (int j = left + 2; j < groups.length; j++) {
                    if (groups[j] < 0) {
                        lastRight = j - 1;
                        break;
                    }
                }
                break;
            }
        }
        if (lastRight == t) return null; // 代表没有下一个group
        return new int[]{left, lastRight};
    }

    public static void main(String[] args) {
        Solution32 t = new Solution32();
        //System.out.println(t.longestValidParentheses(")()())"));
        //System.out.println(t.longestValidParentheses("(()(()))"));
        //System.out.println(t.longestValidParentheses(")("));
        //System.out.println(t.longestValidParentheses("(()())()"));
        //System.out.println(t.longestValidParentheses(")(((((()())()()))()(()))("));//22
        //System.out.println(t.longestValidParentheses(")(())(()()))("));//10
        //System.out.println(t.longestValidParentheses("()(((()(()))))"));//14
        System.out.println(
                t.longestValidParentheses(
                        ")(()(()(((())(((((()()))((((()()(()()())())())()))()()()())(())()()(((()))))()((()))(((())()((()()())((())))(())))())((()())()()((()((())))))((()(((((()((()))(()()(())))((()))()))())"));//10

    }
}
