package com.zt.my;

import java.sql.SQLOutput;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * <p>
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 * <p>
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 * <p>
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 * <p>
 * 提示：
 * <p>
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−231) 。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-15 20:32
 */
public class Test8 {
    public int myAtoi(String str) {
        char space = ' ';
        int firstNotSpace = 0;
        char[] chars = str.toCharArray();
        long res = 0;
        int end = 0;
        int sign = 1;
        boolean signSure = false;
        boolean numStart = false;
        boolean signStart = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == space) {
                if (numStart) break;
                if (signStart) break;
                firstNotSpace++;
                continue;
            }
            // 一定是数字
            if ((chars[i] >= '0' && chars[i] <= '9') || chars[i] == '+' || chars[i] == '-') {
                end = i;
                signStart = true;
                if (chars[i] == '-') {
                    if (numStart) break;
                    if (!signSure) {
                        signSure = true;
                        sign = -1;
                    } else {
                        break;
                    }
                } else if (chars[i] == '+') {
                    if (numStart) break;
                    if (!signSure) {
                        signSure = true;
                    } else {
                        break;
                    }
                } else {
                    numStart = true;
                    res = res * 10L + (chars[i] - 48L);
                    if (res * sign < Integer.MIN_VALUE)
                        return Integer.MIN_VALUE;
                    else if (res * sign > Integer.MAX_VALUE)
                        return Integer.MAX_VALUE;
                }
                end++;
                continue;
            } else {
                // 当出现字符时直接break
                break;
            }
        }
        if (end == firstNotSpace) return 0;
        else {
            res *= sign;
            return (int) res;
        }
    }

    public static void main(String[] args) {
        Test8_2 s = new Test8_2();
        System.out.println(s.myAtoi("   +0 123"));
    }
}

class Test8_2 {
    public int myAtoi(String str) {
        char[] array = str.toCharArray();
        int  index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != ' '){
                index = i;
                break;
            }
        }
        int sign = 1;
        String s = str.substring(index);

        if (s.length() == 0)
            return 0;
        char[] chars = s.toCharArray();
        if (!((chars[0] >= '0' && chars[0] <= '9') || chars[0] == '+' || chars[0] == '-')) return 0;
        long res = 0;
        for (int i = 0; i < chars.length; i++) {
            boolean b = i + 1 < chars.length && chars[i + 1] >= '0' && chars[i + 1] <= '9';
            if (chars[i] == '-') {
                if (b){
                    sign = -1;
                }else {
                    return 0;
                }
            } else if (chars[i] == '+') {
                if (b){
                }else {
                    return 0;
                }
            } else {
                    res = res * 10L + (chars[i]- 48L);
                    if (res * sign < Integer.MIN_VALUE)
                        return Integer.MIN_VALUE;
                    else if (res * sign > Integer.MAX_VALUE)
                        return Integer.MAX_VALUE;
                    if (!b)break;

            }
        }
        return (int) (res * sign);
    }
}
