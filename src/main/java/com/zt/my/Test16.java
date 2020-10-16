package com.zt.my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-24 12:21
 */
public class Test16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int a = target - (nums[j] + nums[k] + nums[i]); // 插值
                int temp = Math.abs(a);
                if (temp < min) {
                    res = nums[j] + nums[k] + nums[i];
                    min = temp;
                }
                if (a < 0) { // 插值 小 这说明向 right大
                    k--;
                } else if (a > 0) {
                    j++;
                } else {
                    return target;
                }
            }

        }
        return res;
    }

    public static void main(String[] args) {
        Test16 t = new Test16();
        System.out.println(t.threeSumClosest(new int[]{0, 2, 1, -3}, 1));
        //System.out.println(t.threeSumClosest(new int[]{1,1,-1,-1,3},-1));
    }
}
