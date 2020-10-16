package com.zt.my26;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-15 20:50
 */
public class Test50 {
    double init;

    public double myPow(double x, int n) {
        //return Math.pow(x,n);
        if (n == 0) return 1;
        if (x == 0 || x == 1) return x;
        if (x == -1) {
            if (n % 2 == 0) return 1;
            return -1;
        }
        init = x;
        return func(n);
    }

    double func(int n) {
        if (n == 0) return 1;
        if (n == 1) return init;
        if (n == -1) return 1 / init;
        int left = n / 2;
        int right = n - left;
        double res = func(left);
        if (res != 0) {
            return res * func(right);
        }
        return 0;
    }

    public static void main(String[] args) {
        Test50 t = new Test50();
        //System.out.println(t.myPow(2f, Integer.MIN_VALUE));
        System.out.println(t.myPow(0.00001, Integer.MAX_VALUE));
        //System.out.println(t.myPow(2f,-2));
        //System.out.println(Integer.MIN_VALUE);
        //System.out.println(t.myPow(2.00000,10));
        //System.out.println(Math.pow(0.00001,Integer.MAX_VALUE));
    }
}

/**
 * 其实不用两边left和right都算 算一边就行
 */
class Test50_1 {
    double init;

    public double myPow(double x, int n) {
        //return Math.pow(x,n);
        if (n == 0) return 1;
        if (x == 0 || x == 1) return x;
        if (x == -1) {
            if (n % 2 == 0) return 1;
            return -1;
        }
        init = x;
        return func(n);
    }

    double func(int n) {
        if (n == 0) return 1;
        if (n == 1) return init;
        if (n == -1) return 1 / init;
        int left = n / 2;

        double res = func(left);

        if (n % 2 == 0)
            return res * res;
        else return res * res * func(left < 0 ? -1 : 1);
    }

    public static void main(String[] args) {
        Test50 t = new Test50();
        //System.out.println(t.myPow(2f, Integer.MIN_VALUE));
        System.out.println(t.myPow(0.00001, Integer.MAX_VALUE));
        //System.out.println(t.myPow(2f,-2));
        //System.out.println(Integer.MIN_VALUE);
        //System.out.println(t.myPow(2.00000,10));
        //System.out.println(Math.pow(0.00001,Integer.MAX_VALUE));
    }
}
