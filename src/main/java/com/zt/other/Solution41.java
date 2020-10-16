package com.zt.other;


/**
 * 显而易见 对于一个长度为N的数组 非正数一定是不用的 他不会影响我们最后输出的结果
 * 长度为N的数组 最后输出的结果显然只能是 [1,N+1] 对于大于N+1的值也不会影响我们的结果
 * 我们可以将数组中<=0的数变为N+1 使得数组内全部都是整数
 * 我们可以将1~N内的值放入一个哈希表中进行标记 由于题目要求只能使用有限的额外空间
 * 因此 我们只能进行原地hash
 * 我们将从[1,N]的数 x 将arr[x - 1]进行标记 鉴于此时数组内已经全部都是正数
 * 因此我们可以标记将标记设为负数 在索引index从0~N-1内 凡是负数 代表数组内出现了index+1;凡是arr[index]>0
 * 代表在1~N内没有出现index+1;
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-11 20:15
 */
public class Solution41 {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) nums[i] = nums.length + 1;
        }
        for (int i = 0; i < nums.length; i++) {
            int abs = Math.abs(nums[i]); // 因为前面的数可能会使后面的处于范围内的数变成负数
            if (abs <= nums.length) {
                nums[abs - 1] = -Math.abs(nums[abs - 1]); // 标记为负数
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return i + 1;
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        Solution41_1 t = new Solution41_1();
        //System.out.println(t.firstMissingPositive(new int[]{1, 2, 0})); // 3
        System.out.println(t.firstMissingPositive(new int[]{3, 4, -1, 1})); // 2
    }
}

/**
 * 置换法 结果一定是从 1~N+1
 * num[i] = x && x ∈ [1,N] 我们就与 num[x-1] 交换位置
 * 交换后 新的num[i] 也可能属于 [1,N] 因此我们需要继续交换 直到 num[i]不属于 [1,N]
 * 单是此过程中可能出现死循环 即 num[num[i] - 1] = num[i] 此时其实也代表代表到了正确的值
 */
class Solution41_1 {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] >0 && nums[i] <= nums.length && nums[nums[i] - 1] !=nums[i]){
                int index = nums[i] - 1;
                int temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1)return i+1;
        }
        return nums.length + 1;
    }
}
