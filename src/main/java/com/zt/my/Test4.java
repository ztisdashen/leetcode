package com.zt.my;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-13 19:59
 */
public class Test4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] total = new int[nums1.length + nums2.length];
        int totalLength = total.length;
        int half = totalLength / 2;
        int index = 0;
        int left = 0;
        int right = 0;
        while (left < nums1.length && right < nums2.length && index <= half) {
            if (nums1[left] < nums2[right]) {
                total[index] = nums1[left];
                index++;
                left++;
            } else {
                total[index] = nums2[right];
                index++;
                right++;
            }
        }
        while (left < nums1.length && index <= half) {
            total[index] = nums1[left];
            index++;
            left++;
        }
        while (right < nums2.length && index <= half) {
            total[index] = nums2[right];
            index++;
            right++;
        }
        if (total.length % 2 == 0) {
            return ((double) total[half] + (double) total[half - 1]) / 2;
        }
        return total[half];
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int half = totalLength / 2;
        int halfNum = -1;
        int lessHalfNum = -1;
        int left = 0;
        int right = 0;
        while (left + right <= half) {
            lessHalfNum = halfNum;
            if (left>= nums1.length){
                halfNum = nums2[right];
                right++;
                continue;
            }
            if (right >= nums2.length){
                halfNum = nums1[left];
                left++;
                continue;
            }
            if (nums1[left] < nums2[right]) {
                halfNum = nums1[left];
                left++;
            } else {
                halfNum = nums2[right];
                right++;
            }
        }
        if (totalLength % 2 == 0) {
            return ((double) halfNum + (double) lessHalfNum) / 2;
        }
        return halfNum;
    }

    public static void main(String[] args) {
        //int[] a = new int[]{5, 6, 11, 15, 17};
        //int[] b = new int[]{2, 4, 6, 8};
        int[] a = new int[]{1, 2};
        int[] b = new int[]{3, 4};
        Test4 t = new Test4();
        double v = t.findMedianSortedArrays2(a, b);
        System.out.println(v);
    }
}
