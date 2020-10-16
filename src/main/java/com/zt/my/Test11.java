package com.zt.my;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai)
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-19 17:53
 */
public class Test11 {
    public int maxarea(int[] height) {
        int gap = 1;
        int max = 0;
        while (gap < height.length) {
            for (int i = 0; i < height.length - gap; i++) {
                int temp = Math.min(height[i], height[i + gap]) * gap;
                if (temp > max) max = temp;
            }
            gap++;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] height= new int[]{1,8,6,2,5,4,8,3,7};
        Test11 test11 = new Test11();
        System.out.println(test11.maxarea(height));
    }
}
