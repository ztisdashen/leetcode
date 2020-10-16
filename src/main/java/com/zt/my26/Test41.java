package com.zt.my26;


import java.util.Arrays;

/**
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-11 18:09
 */
public class Test41 {
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int min = 1;
        for (int num : nums) {
            if (num > min) {
                return min;
            } else if (num == min) min++;
        }
        return min;
    }

    public static void main(String[] args) {
        Test41_1 t = new Test41_1();
        //System.out.println(t.firstMissingPositive(new int[]{1, 2, 0})); // 3
        //System.out.println(t.firstMissingPositive(new int[]{3, 4, -1, 1})); // 2
        //System.out.println(t.firstMissingPositive(new int[]{7, 8, 9, 11, 12})); // 1
        System.out.println(t.firstMissingPositive(new int[]{99,94,96,11,92,5,91,89,57,85,66,63,84,81,79,61,74,78,77,30,64,13,58,18,70,69,51,12,32,34,9,43,39,8,1,38,49,27,21,45,47,44,53,52,48,19,50,59,3,40,31,82,23,56,37,41,16,28,22,33,65,42,54,20,29,25,10,26,4,60,67,83,62,71,24,35,72,55,75,0,2,46,15,80,6,36,14,73,76,86,88,7,17,87,68,90,95,93,97,98})); // 1

    }
}

class Test41_1 {
    // error
    public int firstMissingPositive(int[] nums) {
        int[] hash = new int[10];
        Arrays.fill(hash, Integer.MAX_VALUE);
        for (int i : nums) {
            if (i > 0) {
                int index = i % 10;
                if (i < hash[index]) {
                    hash[index] = i;
                }
            }
        }
        System.out.println(Arrays.toString(hash));
        int min = Integer.MAX_VALUE;
        for (int i : hash) {
            if (min > i) min = i;
        }
        if (min != 1) return 1;
        else {
            while (true) {
                int index = min % 10;
                if (index + 1 >= 10) index = -1;
                if (hash[index + 1] > min + 1) return min + 1;
                min++;
            }
        }
    }
}
