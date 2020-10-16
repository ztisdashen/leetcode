package com.zt.my26;

import java.util.Arrays;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-29 22:28
 */
public class Test34 {
    public int[] searchRange(int[] nums, int target) {
        return searchAllEqual(nums, 0, nums.length - 1, target);
    }
    private int[] searchAllEqual(int[] arr, int left, int right, int val) {

        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        if (left > right)
            return res;
        int mid = (left + right) / 2;
        if (arr[mid] > val) {
            return searchAllEqual(arr, left, mid - 1, val);
        } else if (arr[mid] < val) {
            return searchAllEqual(arr, mid + 1, right, val);
        } else {
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == val) {
                temp--;
            }
            res[0] = temp+1;
            temp = mid + 1;
            while (temp < arr.length && arr[temp] == val) {
                temp++;
            }
            res[1] = temp-1;
            return res;
        }
    }

    public static void main(String[] args) {
        Test34 t = new Test34();
        //System.out.println(Arrays.toString(t.searchRange(new int[]{5,7,7,8,8,10},8)));
        System.out.println(Arrays.toString(t.searchRange(new int[]{5,7,7,8,8,10},6)));
    }
}
