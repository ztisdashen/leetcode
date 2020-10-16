package com.zt.my26;

import java.util.*;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-15 14:39
 */
public class Test47 {
    Set<List<Integer>> res = new HashSet<>();

    boolean[] group;

    public List<List<Integer>> permuteUnique(int[] nums) {
        group = new boolean[nums.length];
        func(new LinkedList<>(), nums);
        return new ArrayList<>(res);
    }

    public void func(LinkedList<Integer> container, int[] nums) {
        if (container.size() == nums.length) {
            res.add(new ArrayList<>(container));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!group[i]) {
                //if (i > 0 && nums[i]!=nums[i-1]){
                container.add(nums[i]);
                group[i] = true;
                func(container, nums);
                container.pollLast();
                group[i] = false;
                //}
            }
        }
    }

    public static void main(String[] args) {
        Test47 t = new Test47();
        System.out.println(t.permuteUnique(new int[]{1, 1, 2}));
    }
}

class Test47_1 {
    List<List<Integer>> res = new ArrayList<>();

    boolean[] group;

    public List<List<Integer>> permuteUnique(int[] nums) {
        group = new boolean[nums.length];
        Arrays.sort(nums);
        func(new LinkedList<>(), nums);
        return res;
    }

    public void func(LinkedList<Integer> container, int[] nums) {
        if (container.size() == nums.length) {
            res.add(new ArrayList<>(container));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!group[i]) {
                // 用于去重 当nums[i] == nums[i-1]时就可能导致重复
                // !group[i-1]用来保证只有前面那个相同的数已经加入container的情况下 下个相同的数可以加入container
                if (i > 0 && nums[i] == nums[i - 1] && !group[i-1]) {
                    continue;
                }
                container.add(nums[i]);
                group[i] = true;
                func(container, nums);
                container.pollLast();
                group[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Test47_1 t = new Test47_1();
        //System.out.println(t.permuteUnique(new int[]{1, 1,1,2}));
        System.out.println(t.permuteUnique(new int[]{3,3,0,3}));
    }
}
