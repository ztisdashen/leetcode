package com.zt.my26;

import java.util.Arrays;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-10 14:23
 */
public class Test35 {
    public int searchInsert(int[] nums, int target) {
        if (target > nums[nums.length - 1])return nums.length;
        if (target < nums[0])return 0;
        return search(nums,0,nums.length - 1,target);
    }
    private int search(int[] arr, int left, int right, int val) {
        if (left > right)
            return left;
        if (right < 0)return 0;
        if (left >= arr.length)return arr.length;
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
        Test35 t = new Test35();
        //System.out.println(Arrays.toString(t.searchRange(new int[]{5,7,7,8,8,10},8)));
        System.out.println(t.searchInsert(new int[]{1,3,5,6,8},8));
    }
}
