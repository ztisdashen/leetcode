package com.zt.my51;



/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-21 19:58
 */
public class Test69 {
    public int mySqrt(int x) {
        return (int) Math.sqrt(x);
    }

    public int mySqrt3(int x) {
        return (int) Math.pow(x, 0.5);
    }

    public int mySqrt2(int x) {

        for (long i = 0; i <= x; i++) {
            long k = i * i;
            if (k == x) return (int) i;
            else if (k > x) return (int) (i - 1);
        }
        return 0;
    }
    // 二分查找
    public int mySqrt4(int x) {
            int l = 0, r = x, ans = -1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if ((long) mid * mid <= x) {
                    ans = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return ans;
    }

    public static void main(String[] args) {
        Test69 t = new Test69();
        System.out.println(t.mySqrt4(2147395599));
    }
}
