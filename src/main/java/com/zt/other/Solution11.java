package com.zt.other;

import com.zt.my.Test11;

/**
 * 这里用到了动态规划，基本的表达式: area = min(height[i], height[j]) * (j - i)
 * 使用两个指针，值小的指针向内移动，这样就减小了搜索空间 因为面积取
 * 决于指针的距离与值小的值乘积，如果值大的值向内移动，距离一定减小，
 * 而求面积的另外一个乘数一定小于等于值小的值，因此面积一定减小，
 * 而我们要求最大的面积，因此值大的指针不动，而值小的指针向内移动遍历
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-19 18:11
 */
public class Solution11 {
    public int maxarea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (right > left){
            max = Math.max(max,Math.min(height[left],height[right]) * (right - left));
            if (height[left] > height[right]) right--;else left++;
        }

        return max;
    }

    public static void main(String[] args) {
        int[] height= new int[]{1,8,6,2,5,4,8,3,7};
        Solution11 test11 = new Solution11();
        System.out.println(test11.maxarea(height));
    }
}
