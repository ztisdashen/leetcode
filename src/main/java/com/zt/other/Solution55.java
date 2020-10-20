package com.zt.other;


/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-18 18:48
 */
public class Solution55 {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) { // 相当于 Test55中reach赶上了nextReach 这是不应该发生的
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution55 solution55 = new Solution55();
        System.out.println(solution55.canJump(new int[]{3,2,1,0,4}));
    }
}
