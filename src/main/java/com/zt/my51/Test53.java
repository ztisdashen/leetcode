package com.zt.my51;


import java.sql.ResultSet;
import java.util.Arrays;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-18 10:09
 * ERROR
 */
public class Test53 {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int start = 0;
        int end = 0;
        int curr = 0;
        int min = Integer.MIN_VALUE;
        while (start < nums.length && nums[start] < 0) {
            if (nums[start] > min) min = nums[start];
            start++;
            end++;
        }
        if (start >= nums.length) return min;
        int sum = nums[start];
        int lastNum = sum;
        for (int i = start + 1; i < nums.length; i++) {
            int val = nums[i];
            if (val < 0) {
                int p = 0;
                int j = i + 1;
                for (; j < nums.length && p + val < 0; j++) {
                    p += nums[j];
                }
                if (j < nums.length || p + val >= 0) {
                    j--;
                    if (p < -val) { // 说明没有去掉这个负数的影响
                        lastNum = Math.max(lastNum, sum);
                        sum = 0;
                        start = i;
                        end = i;
                    } else { // 说明抵消掉了这个负数的影响
                        if (sum + val <= 0) {
                            start = i + 1;
                            for (; start <= j && nums[start] < 0; start++) {
                                p -= nums[start];
                            }
                            sum = p;
                        } else {
                            sum += (p + val);
                            if (nums[start] < 0) {
                                sum -= nums[start];
                                start++;
                            }
                        }
                        end = j;
                        i = j;
                    }
                } else {
                    if (i + 1 < nums.length) {
                        start = i + 1;
                        while (start < nums.length && nums[start] < 0) {
                            start++;
                        }
                        if (start < nums.length) {
                            end = start;
                            lastNum = Math.max(lastNum, sum);
                            sum = nums[start];
                            i = start;
                        } else {
                            return Math.max(lastNum, sum);
                        }
                    }
                }

            } else {
                sum += nums[i];
                end++;
            }
        }
        return Math.max(lastNum, sum);
    }

    public static void main(String[] args) {
        Test53 t = new Test53();
        //System.out.println(t.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        //System.out.println(t.maxSubArray(new int[]{8, -19, 5, -4, 20}));
        //System.out.println(t.maxSubArray(new int[]{-3,1,0,-1,-2,3,2,-1}));
        //System.out.println(t.maxSubArray(new int[]{2,-2,-2,0,-2,2,2}));
        //System.out.println(t.maxSubArray(new int[]{-1,0,-2}));
        //System.out.println(t.maxSubArray(new int[]{0, -3, -2, -3, -2, 2, -3, 0, 1, -1}));
        System.out.println(t.maxSubArray(new int[]{-1, 1, -3, -2, 2, -1, -2, 1, 2, -3}));
        //System.out.println(t.maxSubArray(new int[]{-2,-1}));
        //System.out.println(t.maxSubArray(new int[]{1,-1,-2}));
    }
}
// Out of time
class Test53_1 {
    public int maxSubArray(int[] nums) {

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) sum += nums[k];
                if (sum > max)max=sum;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Test53_1 t = new Test53_1();
        System.out.println(t.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
    }
}
class Test53_2 {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1)return nums[0];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum > max)max=sum;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Test53_2 t = new Test53_2();
        System.out.println(t.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
    }
}
class Test53_3 {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1)return nums[0];
        // 处理全都是负数的情况
        boolean b = true;
        int min = Integer.MIN_VALUE;
        for (int i : nums) {
            if (i >= 0){
                b = false;
                break;
            }
            else if (i > min)min = i;
        }
        if (b)return min;

        int maxSum = 0,thisSum = 0;
        for (int num : nums) {
            thisSum += num;
            if (thisSum > maxSum) maxSum = thisSum;
            else if (thisSum < 0){
                // thisSum < 0 代表入不敷出 正数补不了负数 直接清零从新的数开始求和即可
                // 这种情况一般发生在某一个子序前面全都是负数的情况
               thisSum = 0;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Test53_3 t = new Test53_3();
        //System.out.println(t.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
        //System.out.println(t.maxSubArray(new int[]{-2,1})); // 6
        System.out.println(t.maxSubArray(new int[]{-2,-1})); // 6
    }
}

/**
 * 最大子序要么出现在左边
 * 要么出现在右边
 * 要么从中间开始
 */
class Test53_4 {
    public int maxSubArray(int[] nums) {
        return func(nums,0,nums.length - 1);
    }

    private int func(int[] nums, int left, int right) {
        if (left == right)return nums[left];
        int mid = (left + right) / 2;
        int maxFromLeft = func(nums,left,mid); // 左边的最大子序和
        int maxFromRight = func(nums,mid+1,right); // 右边的最大子序和
        // 计算中间的最大子序和
        int maxLeft = Integer.MIN_VALUE,leftSum = 0;
        for (int i = mid; i >=left ; i--) {
            leftSum+=nums[i];
            if (leftSum > maxLeft)maxLeft = leftSum;
        }
        int maxRight = Integer.MIN_VALUE,rightSum = 0;

        for (int i = mid+1; i <=right ; i++) {
            rightSum+=nums[i];
            if (rightSum > maxRight)maxRight = rightSum;
        }
        return Math.max(Math.max(maxFromLeft,maxFromRight),maxRight+maxLeft);
    }
    public static void main(String[] args) {
        Test53_4 t = new Test53_4();
        //System.out.println(t.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
        //System.out.println(t.maxSubArray(new int[]{-2,1})); // 6
        System.out.println(t.maxSubArray(new int[]{-2,-1})); // 6
    }
}
