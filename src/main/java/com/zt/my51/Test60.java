package com.zt.my51;


import java.util.*;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 *
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 *
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 *
 * 输入: n = 4, k = 9
 * 输出: "2314"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-18 22:09
 * PASS
 */
public class Test60 {
    public String getPermutation(int n, int k) {
        List<Integer> list = new ArrayList<>();
        calcN(n);
        for (int i = 0; i < n; i++) {
            list.add(i + 1);
        }
        double kk = k * 1.0;
        StringBuilder result = new StringBuilder();
        func(result,kk,list,n-1);
        return result.toString();
    }

    Map<Integer, Integer> nMap = new HashMap<>();

    int calcN(int n) {
        if (n == 1) {
            nMap.put(1, 1);
            return 1;
        }
        int res = n * calcN(n - 1);
        nMap.put(n, res);
        return res;
    }
    void func(StringBuilder result,double kk,List<Integer> list,int n){
        if (list.size() == 1){
            result.append(list.get(0));
            return;
        }
        int n1 =(int) Math.ceil(kk / nMap.get(n)) - 1;
        result.append(list.remove(n1));

        kk = kk - (nMap.get(n) * n1);
        func(result,kk,list,n-1);
    }
    public static void main(String[] args) {
        Test60 t = new Test60();
        //System.out.println(t.getPermutation(3, 3));
        System.out.println(t.getPermutation(4, 9));
    }
}
// 思想是一样的 细节不太一样
class Test60_1_other{
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; ++i) {
            factorial[i] = factorial[i - 1] * i;
        }

        --k;
        StringBuilder ans = new StringBuilder();
        int[] valid = new int[n + 1];
        Arrays.fill(valid, 1);
        for (int i = 1; i <= n; ++i) {
            int order = k / factorial[n - i] + 1;
            for (int j = 1; j <= n; ++j) {
                order -= valid[j];
                if (order == 0) {
                    ans.append(j);
                    valid[j] = 0;
                    break;
                }
            }
            k %= factorial[n - i];
        }
        return ans.toString();
    }
}
