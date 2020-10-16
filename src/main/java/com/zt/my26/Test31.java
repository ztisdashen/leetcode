package com.zt.my26;

import java.util.Arrays;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 我的：根据题解 就是 例如 nums = {1,2,3} 对应123，我们需要利用里面的数 找到一个略大于123的数 即132
 * 既然是略大于 则对于元素组的变化应该尽量最小 影响一个数的个位对数字变化的影响最小
 * 因此 我们应该从后向前找 若后面若干个元素是降序的 当我们破坏了降序 都会使数值变小
 * 所以若后面若干个元素是降序的，则我们不应该对其做出任何改变
 * 当我们找到一个元素破坏了后面若干个元素的降序性 该子数组就可以被修改 使其变大
 * 假设破坏降序性的元素为 nums[index] = a,则从index+1~nums.length-1都应该是降序的
 * 我们则应该在nums[index+1:nums.length]中找到一个略大于a的元素nums[k] = b(该元素一定是存在的，否则就不会破坏其降序性)
 * 该元素b与a的差值是应该是最小的，这样才可以保证增加的尽量少 与a调换位置后,对nums[index+1:]进行升序排序 使其最小
 * 最终完成查找
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-28 19:12
 */
public class Test31 {
    public void nextPermutation(int[] nums) {
        if (nums.length == 1)return;
        if (nums.length == 2){
            if (nums[1] > nums[0]){
                swap(nums,0,1);
            }
            return;
        }
        int i = nums.length - 2;
        for (;i >= 0; i--) {
            // 找到从后面数第一个破坏降序的元素
            boolean b = isOrder(nums, i);
            if (!b){
                break;
            }
        }
        if (i == -1){ // 代表没有被破坏
            reverseArray(nums,0);
        }else {
            func(nums,i);
        }
    }

    /**
     * 先变大
     * 变小
     * 从 startIndex以后破坏了降序 一定存在nums[x] > nums[starIndex]
     * starIndex+1 ~ length 是降序的
     * @param nums
     * @param startIndex
     */
    public void func(int[]nums, int startIndex){
        int delta = Integer.MAX_VALUE;
        int next = -1;
        for (int i = startIndex +1; i < nums.length; i++) {
            if (nums[i] > nums[startIndex] && nums[i] - nums[startIndex] < delta){
                next = i;
                delta = nums[i] - nums[startIndex];
            }else if(nums[i] <= nums[startIndex]){ // 后面的数肯定更小 不用比较了
                break;
            }
        }
        swap(nums,next, startIndex);
        // 向后面的数变小 将start后面的数降序排就完事了
        Arrays.sort(nums,startIndex+1,nums.length);

    }

    /**
     * 从start以后是降序的
     * @param data
     * @param start
     * @return
     */
    public boolean isOrder(int[] data,int start){
        boolean flag2 = false;
        if (start == data.length - 1)return true;
        //数组是否为降序
        for (int i = start; i < data.length - 1; i++) {
            if (data[i] == Math.max(data[i], data[i + 1])) {
                flag2 = true;
            } else {
                flag2 = false;
                break;
            }
        }
        return flag2;
    }
    public void reverseArray(int[] nums,int start){
        int l = (nums.length - start) / 2;
        for (int i = start; i < start + l; i++) {
            swap(nums,i,nums.length - 1 - i+start);
        }
    }
    public void swap(int[] nums,int a,int b){
        int temp = nums[a];
        nums[a]= nums[b];
        nums[b] = temp;
    }
    public static void main(String[] args) {
        Test31 t = new Test31();
        t.nextPermutation(new int[]{2,1,5,3,2});
        //System.out.println(t.isOrder(new int[]{2,2},0));
        //t.reverseArray(new int[]{2,1,5,3,8},1);
        //t.nextPermutation(new int[]{3,2,1});
        //t.nextPermutation(new int[]{1, 2, 3});
        //t.nextPermutation(new int[]{1,1,5});
        //t.nextPermutation(new int[]{2,3,1}); // 312
        //t.nextPermutation(new int[]{4,2,0,2,3,2,0}); // [4,2,0,3,0,2,2]
        //System.out.println(t.isOrder(new int[]{5,2,1},2));
    }
}
