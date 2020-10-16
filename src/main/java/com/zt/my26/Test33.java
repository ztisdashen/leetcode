package com.zt.my26;

import java.util.Arrays;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-29 22:50
 */
public class Test33 {
    public int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target)return i;
        }
        return -1;
    }
    public int search1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] < nums[i-1]){
                int k = -1;
                if (target<=nums[i-1]){
                    k = search(nums,0,i-1,target);
                    if (k!=-1)return k;
                }else if (target >= nums[i]){
                    k = search(nums,i,nums.length-1,target);
                    return k;
                }
            }
        }
        return -1;
    }
    private int search(int[] arr, int left, int right, int val) {
        if (left > right || right < 0 || left >= arr.length)
            return -1;
        int mid = (left + right) / 2;
        if (arr[mid] > val) {
            return search(arr, left, mid - 1, val);
        } else if (arr[mid] < val) {
            return search(arr, mid + 1, right, val);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        Test33 t = new Test33();
        System.out.println(t.search1(new int[]{4,5,6,7,0,1,2},0));
        //System.out.println(Arrays.toString(t.searchRange(new int[]{5,7,7,8,8,10},8)));
        //System.out.println(Arrays.toString(t.searchRange(new int[]{5,7,7,8,8,10},6)));
    }
}
