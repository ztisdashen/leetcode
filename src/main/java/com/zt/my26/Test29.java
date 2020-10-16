package com.zt.my26;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-26 20:15
 */
public class Test29 {
    Map<Integer, Double> re = new HashMap<>();

    {
        for (double i = 0; i < 10; i++) {
            for (double j = 1; j < 10; j++) {
                re.put((int) (i * 10 + j), (i / j));
            }
        }
        re.put(0, 0.0);
    }

    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor==-1)return Integer.MAX_VALUE;
        if (divisor == 1)return dividend;
        else if (divisor == -1)return -dividend;

        if (divisor == Integer.MIN_VALUE||divisor == Integer.MAX_VALUE)return dividend / divisor;
        if (dividend == Integer.MAX_VALUE||dividend == Integer.MIN_VALUE)return dividend / divisor;
        if (Math.abs(divisor) >Math.abs(dividend))return 0;
        int sign = 1;
        if (dividend * divisor < 0) sign = -1;
        return (int) func(Math.abs(dividend), Math.abs(divisor)) * sign;
    }

    public double func(double a, double b) {
        if (a < 10 && b < 10) {
            int key = (int) (a * 10 + b);
            if (b == 0) key = 0;
            //System.out.println(key);
            return re.get(key);
        } else {
            String aStr = "" + (int) a;
            String bStr = "" + (int) b;
            int i = aStr.length() / 2;
            int aStr1;
            int aStr2;
            if (i == 0) {
                aStr1 = Integer.parseInt(aStr);
                aStr2 = 0;
            } else {
                aStr1 = Integer.parseInt(aStr.substring(0, i));
                aStr2 = Integer.parseInt(aStr.substring(i));
                i = aStr.length() - i;
            }

            int j = bStr.length() / 2;
            int bStr1;
            int bStr2;
            if (j == 0) {
                bStr1 = Integer.parseInt(bStr);
                bStr2 = 0;
            } else {
                bStr1 = Integer.parseInt(bStr.substring(0, j));
                bStr2 = Integer.parseInt(bStr.substring(j));
                j = bStr.length() - j;
            }
            double value = func(aStr1, bStr1) * Math.pow(10, i - j) +
                    func(aStr1, bStr2) * Math.pow(10, i) +
                    func(aStr2, bStr2) +
                    func(aStr2, bStr1) * Math.pow(10, -j);
            return value;
        }
    }

    public double doSomething(double a, double b) {
        if (b == 1) return a;
        else if (a == b) return 1;
        else if (a == 3 && b == 2) return 1.5;
        else if (a == 4 && b == 2) return 2;
        else if (a == 4 && b == 3) return 1.333;
        else if (a == 5 && b == 2) return 2.5;
        else if (a == 5 && b == 3) return 1.66667;
        Map<Integer, Double> re = new HashMap<>();
        re.put(19, 0.11111111);
        re.put(18, 0.125);
        re.put(17, 0.1428571);
        re.put(16, 0.11111111);
        return a;
    }
    public static void main(String[] args) {
        Test29 t = new Test29();
        //System.out.println(t.re);
        System.out.println(t.divide(
                -1021989372,
                        -82778243));
         //2147483647 2147483647
        //System.out.println(Integer.MAX_VALUE);
        //System.out.println(
        //        2147483647/ 2);
    }

}
