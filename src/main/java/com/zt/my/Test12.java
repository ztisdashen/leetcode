package com.zt.my;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。
 * 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 * 同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 3
 * 输出: "III"
 * 示例 2:
 * <p>
 * 输入: 4
 * 输出: "IV"
 * 示例 3:
 * <p>
 * 输入: 9
 * 输出: "IX"
 * 示例 4:
 * <p>
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * 示例 5:
 * <p>
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-20 20:17
 * <p>
 * 1~10：{1:I,2:II,3:III,4:IV,5:V,6:VI,7:VII,8:VIII,9:IX,10:X}
 * 10~50
 * 50~100
 * 100~500
 * 500~1000
 * 1000~3999
 */
public class Test12 {
    public String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        if (num == 0) {
        } else if (num < 5) {
            if (num == 4) res.append("IV");
            else {
                for (int i = 0; i < num; i++) {
                    res.append("I");
                }
            }
        } else if (num < 10) {
            if (num == 9) res.append("IX");
            else {
                res.append("V").append(intToRoman(num - 5));
            }
        } else if (num < 50) {
            if (num >= 40) {
                res.append("XL").append(intToRoman(num - 40));

            } else {
                int i = 10;
                for (; i <= num; i += 10) {
                    res.append("X");
                }
                res.append(intToRoman(num - (i - 10)));
            }

        } else if (num < 100) {
            if (num >= 90) {
                res.append("XC").append(intToRoman(num - 90));
            } else {
                res.append("L").append(intToRoman(num - 50));
            }

        } else if (num < 500) {
            if (num >= 400) {
                res.append("CD").append(intToRoman(num - 400));
            } else {
                int i = 100;
                for (; i <= num; i += 100) {
                    res.append("C");
                }
                res.append(intToRoman(num - (i - 100)));
            }
        } else if (num < 1000) {
            if (num >= 900) {
                res.append("CM").append(intToRoman(num - 900));
            } else {
                res.append("D").append(intToRoman(num - 500));
            }
        } else {
            int i = 1000;
            for (; i <= num; i += 1000) {
                res.append("M");
            }
            res.append(intToRoman(num - (i - 1000)));

        }
        return res.toString();
    }

    public static void main(String[] args) {
        Test12_1 r = new Test12_1();
        System.out.println(r.intToRoman( 1994));
    }
}

class Test12_1 {
    String[] gap = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    int[] ints = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (num > 0) {
            while (num >= ints[i]) {
                result.append(gap[i]);
                num -=  ints[i];
            }
            i++;
        }
        
        return result.toString();
    }

}
