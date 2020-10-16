package com.zt.my26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-12 21:30
 */
public class Test43 {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        if (num1.equals("1") || num2.equals("1")) return num1.equals("1") ? num2 : num1;
        char[] array = num2.toCharArray();
        int maxLen = 0;
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            StringBuilder s = func(num1, array[i], array.length - i - 1);
            if (s.length() > maxLen) maxLen = s.length();
            list.add(s);
        }
        return add(list, maxLen);
        //return "0";
    }

    public String add(List<StringBuilder> list, int maxLen) {
        //
        list.forEach(s -> {
            if (s.length() < maxLen) {
                int len = s.length();
                for (int i = 0; i < maxLen - len; i++) {
                    s.insert(0, "0");
                }
            }
        });
        int jinwei = 0;
        StringBuilder res = new StringBuilder();
        for (int i = maxLen - 1; i >= 0; i--) {
            int total = jinwei;
            for (StringBuilder stringBuilder : list) {
                total += (stringBuilder.charAt(i) - 48);
            }
            jinwei = total / 10;
            res.append(total % 10);
        }
        if (jinwei > 0) res.append(jinwei);
        //System.out.println(list);
        return res.reverse().toString();
    }

    public StringBuilder func(String str, char num, int count) { // count  0的个数
        if (num == 48) return new StringBuilder("0");
        int number = num - 48;
        char[] chars = new StringBuilder(str).reverse().toString().toCharArray();
        StringBuilder res = new StringBuilder();
        if (num == '1') {
            res.append(str);
        } else {
            int jinwei = 0; // 进位
            for (char c : chars) {
                int curr = c - 48;
                int subRes = curr * number + jinwei;
                jinwei = subRes / 10;
                int b = subRes % 10;
                res.insert(0, b);
            }
            if (jinwei > 0) res.insert(0, jinwei);
        }
        for (int i = 0; i < count; i++) {
            res.append("0");
        }
        //res.append("0".repeat(Math.max(0, count)));
        return res;
    }

    public static void main(String[] args) {
        String num = "123";
        Test43 v = new Test43();
        System.out.println(v.multiply("1232323234245254234", "45688888252525"));
        //System.out.println(v.func(num,'3'));
        //for (int i = 0; i < 10; i++) {
        //    System.out.printf("%d %s\n",123 * i,v.func(num, (char) (i+48),1));
        //}\

    }

}

class Test43_1 {
    public String multiply(String num1, String num2) {
        return fun(num1,num2);
    }

    public String fun(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))return "0";
        if (num1.length() == 1 && num2.length() == 1) {
            return (num1.charAt(0) - 48) * (num2.charAt(0) - 48) + "";
        }
        if (num1.length() == 0 || num2.length() == 0) return "0";
        if(num1.equals("1") || num2.equals("1"))return num1.equals("1")?num2:num1;
        int len1 = num1.length();
        int len2 = num2.length();
        String numA, numB, numC, numD;
        numA = num1.substring(0, len1 / 2);
        numB = num1.substring(len1 / 2);
        numC = num2.substring(0, len2 / 2);
        numD = num2.substring(len2 / 2);
        List<StringBuilder> list =new ArrayList<>();
        StringBuilder res1 = new StringBuilder(fun(numA, numC));
        if (!res1.toString().equals("0")){
            addZero(res1, (len1 - len1 / 2) + (len2 - len2 / 2) );
            list.add(res1);
        }
        StringBuilder res2 = new StringBuilder(fun(numA, numD));
        if (!res2.toString().equals("0")){
            addZero(res2, len1 - len1 / 2 );
            list.add(res2);
        }
        StringBuilder res3 = new StringBuilder(fun(numB, numC));
        if (!res3.toString().equals("0")){
            addZero(res3, len2 - len2 / 2 );
            list.add(res3);
        }
        StringBuilder res4 = new StringBuilder(fun(numB, numD));
        if (!res4.toString().equals("0")){
            list.add(res4);
        }
        return add(list);
    }
    public String add(List<StringBuilder> list) {
        //
        int maxLen = 0;
        for (StringBuilder builder : list) {
            if (builder.length() > maxLen) maxLen = builder.length();
        }
        for (StringBuilder s : list) {
            if (s.length() < maxLen) {
                int len = s.length();
                for (int i = 0; i < maxLen - len; i++) {
                    s.insert(0, "0");
                }
            }
        }
        int jinwei = 0;
        StringBuilder res = new StringBuilder();
        for (int i = maxLen - 1; i >= 0; i--) {
            int total = jinwei;
            for (StringBuilder stringBuilder : list) {
                total += (stringBuilder.charAt(i) - 48);
            }
            jinwei = total / 10;
            res.append(total % 10);
        }
        if (jinwei > 0) res.append(jinwei);
        return res.reverse().toString();
    }
    void addZero(StringBuilder res, int count) {
        for (int i = 0; i < count; i++) {
            res.append("0");
        }
    }

    public static void main(String[] args) {
        Test43_1 t = new Test43_1();
        System.out.println("Test43_1.main");
        System.out.println("t.multiply(\"123\",\"123\") = " + t.multiply("3211111111","1232234223"));
    }
}
