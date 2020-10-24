package com.zt.my51;

import java.io.BufferedReader;
import java.util.Arrays;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-23 12:39
 */
public class Test75 {
    public void sortColors(int[] nums) {
        int redCount = 0;
        int blueCount = 0;
        int whiteCount = 0;
        for (int color : nums) {
            switch (color){
                case 0:redCount++;break;
                case 1:whiteCount++;break;
                case 2:blueCount++;
            }
        }
        int index = 0;
        for (int i = 0; i < redCount; i++) {
            nums[index++] = 0;
        }
        for (int i = 0; i < whiteCount; i++) {
            nums[index++] = 1;
        }
        for (int i = 0; i < blueCount; i++) {
            nums[index++] = 2;
        }
    }

    public static void main(String[] args) {
        Test75 t = new Test75();
        int[] nums = {2, 0, 2, 1, 1, 0};
        t.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
