package com.zt.my51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。

 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

 你可以假设除了整数 0 之外，这个整数不会以零开头。

 示例 1:

 输入: [1,2,3]
 输出: [1,2,4]
 解释: 输入数组表示数字 123。
 示例 2:

 输入: [4,3,2,1]
 输出: [4,3,2,2]
 解释: 输入数组表示数字 4321。
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-20 14:53
 * PASS 5%
 */
public class Test66 {
    public int[] plusOne(int[] digits) {
        StringBuilder result = new StringBuilder();

        int jinwei = 0;
        int num = digits[digits.length - 1] + 1;
        result.append(num % 10);
        jinwei = num / 10;
        for (int i = digits.length - 2; i >= 0; i--) {
            if (jinwei!=0){
                int temp = digits[i] + jinwei;
                jinwei = temp / 10;
                result.append(temp % 10);
            }else {
                result.append(digits[i]);
            }
        }
        if (jinwei != 0) result.append(jinwei);
        int[] r = new int[result.length()];
        for (int i = 0; i < r.length; i++) {
            r[i] = result.charAt(result.length() - i - 1) - '0';
        }
        return r;
    }

    public static void main(String[] args) {
        Test66 t = new Test66();
        //System.out.println(Arrays.toString(t.plusOne(new int[]{9,9,9})));
        System.out.println(Arrays.toString(t.plusOne(new int[]{9,9,6})));
    }
}
// 不行 会超范围
class Test66_1 {
    public int[] plusOne(int[] digits) {
        long res = 0;
        long t = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            res+=digits[i] * t;
            t *=10;
        }
        res++;
        StringBuilder s = new StringBuilder().append(res);
        int[] result = new int[s.length()];
        for (int i = 0; i < result.length; i++) {
            result[i] = s.charAt(i) - '0';
        }
        return result;
    }
    public static void main(String[] args) {
        Test66_1 t = new Test66_1();
        //System.out.println(Arrays.toString(t.plusOne(new int[]{9,9,9})));
        System.out.println(Arrays.toString(t.plusOne(new int[]{9,8,7,6,5,4,3,2,1,0})));
    }
}
class Tests66_other{
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
