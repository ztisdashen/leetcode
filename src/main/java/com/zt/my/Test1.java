package com.zt.my;

import java.util.*;

/**
 *
 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

 candidates 中的每个数字在每个组合中只能使用一次。

 说明：

 所有数字（包括目标数）都是正整数。
 解集不能包含重复的组合。
 示例 1:

 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 所求解集为:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]
 示例 2:

 输入: candidates = [2,5,2,1,2], target = 5,
 所求解集为:
 [
 [1,2,2],
 [5]
 ]
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-10 22:37
 */
public class Test1 {
    private List<List<Integer>> res = new ArrayList<>();

    // 递归回溯
    // 回溯 target > list.sum 或者没有candidate 或者target = list.sum
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (int i = 0; i < candidates.length; i++) {
            compute(candidates, i, new ArrayList<>(), target, 0);
        }
        return res;
    }

    private boolean compute(int[] arr, int currIndex, List<Integer> list, int target, int lastSum) {
        if (currIndex >= arr.length) {
            return false;
        }
        int sum = lastSum + arr[currIndex];
        // 和大于sum

        list.add(arr[currIndex]);
        if (target == sum) {
            if (!res.contains(list)) {
                res.add(list);
            }

            return true;
        }
        // sum < target
        // 当前的index >= arr.length
        for (int i = currIndex + 1; i < arr.length; i++) {
            //if (arr[i] == arr[i - 1]) {
            //    continue;
            //}
            List<Integer> temp = new ArrayList<>(list);
            compute(arr, i, temp, target, sum);

        }
        return false;
    }

    public static void main(String[] args) {
        //int[] a = new int[]{34,55,79,28,46,33,2,48,31,-3,84,71,52,-3,93,15,21,-43,57,-6,86,56,94,74,83,-14,28,-66,46,-49,62,-11,43,65,77,12,47,61,26,1,13,29,55,-82,76,26,15,-29,36,-29,10,-70,69,17,49};
        int[] a = new int[]{3, 1, 1, 2, 6, 1,-3};
        Solution test1 = new Solution();
        List<List<Integer>> lists = test1.combinationSum2(a, 3);
        System.out.println(lists);
    }
}

//10,1,2,7,6,1,5
//1,1,2,5,6,7,10
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return list;
    }

    private void dfs(int[] candidates, int target, int index) {
        if (target == 0) {
            list.add(new ArrayList<>(path));
            return;
        }
        // 我和他不同的是 他把currentIndex放入循环一起考虑
        // 我是把currentIndex单摘出来
        for (int i = index; i < candidates.length; i++) {
            // 但是不同考虑负数情况吗？？
            // 如果才存在负数 直接删除下面的if

            if (candidates[i] <= target) {
                // 这样可以避免相同的情况筛选两次
                // 在对candidates排序后 如果产生重复的原因就是存在连续的数字导致
                if (i > index && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                path.add(candidates[i]);
                dfs(candidates, target - candidates[i], i + 1);
                path.remove(path.size() - 1); // 不管是否和为target 都需要把上次递归加入的进行删除进行回溯
            }
        }
    }
}
