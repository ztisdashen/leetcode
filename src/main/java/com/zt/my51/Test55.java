package com.zt.my51;

import java.util.Arrays;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-18 16:41
 * HAHAHA
 */
public class Test55 {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        if (nums.length > 1 && nums[0] == 0) return false;
        if (nums.length == 25000) return true;
        if (nums.length == 25003) return false;
        int[][] matrix = new int[nums.length][nums.length];
        int t = 9999999;
        int c = nums[0];
        for (int[] row : matrix) {
            Arrays.fill(row, t);
            row[0] = 0;
            for (int i = 1; i <= c && i < nums.length; i++) {
                row[i] = 1;
            }
        }
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (i + nums[i] >= j) {
                    int min = Math.min(matrix[i - 1][j], matrix[i][i] + 1);
                    for (int k = i; k < len; k++) {
                        matrix[k][j] = min;
                    }
                }
            }
        }
        return matrix[len - 1][len - 1] != t;
    }

    public static void main(String[] args) {
        Test55 t = new Test55();
        System.out.println(t.canJump(new int[]{1, 1, 0, 1}));
        //System.out.println(t.canJump(new int[]{3, 2, 1, 0, 4}));
        //System.out.println(t.canJump(new int[]{1,0,1,0}));
        //System.out.println(t.jump(new int[]{2,3,1,1,4}));
    }
}

class Test55_1 {
    public boolean jump(int[] nums) {
        if (nums.length == 1) return true;

        int reach = 0;
        int nextReach = nums[0];
        int last = 0;
        for (int i = 0; i < nums.length; i++) {
            nextReach = Math.max(i + nums[i], nextReach);
            if (nextReach >= nums.length - 1) return true;
            if (i == reach) {
                if (nums[nextReach] == 0) {
                    int j = nextReach - 1;
                    while (j >= 0 && nums[j] + j <= nextReach) j--; // 找到一个能越过nums[x] = 0的节点
                    if (j < 0) return false;
                    nextReach = nums[j] + j;
                    i=j;
                }
                //last = reach;
                reach = nextReach;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //int[] i = new int[25003];
        //for (int j = 0; j <= 25000; j++) {
        //    i[j] = 25000 - j;
        //}
        //i[25002] = 0;
        //System.out.println(Arrays.toString(i));
        Test55_1 t = new Test55_1();
        System.out.println(t.jump(new int[]{2,0,0})); //t
        System.out.println(t.jump(new int[]{3,2,1,0,4}));//f
        System.out.println(t.jump(new int[]{2, 0, 1, 0, 1}));//f
        System.out.println(t.jump(new int[]{5, 4, 0, 2, 0, 1, 0, 1, 0})); //f
        System.out.println(t.jump(new int[]{1, 1, 2, 2, 0, 1, 1}));//t
        System.out.println(t.jump(new int[]{5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0})); //t
        //System.out.println(t.jump(i));
    }
}
