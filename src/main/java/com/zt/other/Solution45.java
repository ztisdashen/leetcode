package com.zt.other;


/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-14 21:00
 */
public class Solution45 {
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        // 在更新end = maxPosition;
        // 2,3,1,2,4,2,3
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
