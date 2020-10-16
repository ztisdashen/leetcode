package com.zt.my26;

import java.util.Arrays;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-26 16:30
 */
public class Test26 {
    public int removeDuplicates(int[] nums) {
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            size++;
            while (i > 0 && i < nums.length && nums[i] == nums[i - 1]) i++;
            if (i < nums.length) {
                nums[size - 1] = nums[i]; // i 指向下一个不重复的位置
            }else {
                size--;
            }
        }
        return size;
    }

    public static void main(String[] args) {
        Test26 t = new Test26();
        int[] nums = {1, 1};
        //int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(t.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
