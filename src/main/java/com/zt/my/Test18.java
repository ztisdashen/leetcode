package com.zt.my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-24 15:35
 */
public class Test18 {
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        dfs(nums, target, 0);
        return list;
    }

    private void dfs(int[] candidates, int target, int index) {
        if (path.size() == 4) {
            if (target == 0) {
                list.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            dfs(candidates, target - candidates[i], i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Test18_2 t = new Test18_2();
        //System.out.println(t.fourSum(new int[]{-1,0,1,2,-1,-4}, -1)); // [[-4,0,1,2],[-1,-1,0,1]]
        //System.out.println(t.fourSum(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 0));
        //System.out.println(t.fourSum(new int[]{-4,-3,-2,-1,0,0,1,2,3,4}, 0));
        System.out.println(t.fourSum(new int[]{-9,-2,7,6,-8,5,8,3,-10,-7,8,-8,0,0,1,-8,7}, 4));
        //System.out.println(t.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0)); //[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
    }
}

// PASS
class Test18_1 {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> nlist = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {


            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int m = i + 1; m < nums.length - 2; m++) {
                if (m > i + 1 && nums[m] == nums[m - 1]) {
                    continue;
                }
                int j = m + 1;
                int k = nums.length - 1;
                while (j < k) {

                    if (nums[j] + nums[k] + nums[i] + nums[m] < target) {
                        j++;

                    } else if (nums[j] + nums[k] + nums[i] + nums[m] > target) {
                        k--;
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[m]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        nlist.add(list);
                        j++;
                        k--;
                        while (j < k && nums[j] == nums[j - 1]) {
                            j++;
                        }

                        while (j < k && k != nums.length - 1 && nums[k] == nums[k + 1]) {
                            k--;
                        }
                    }
                }
            }
        }

        return nlist;
    }

}
// [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
class Test18_2 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        HashMap<Long, Boolean> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                for (int k = j + 1; k < nums.length - 1; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            List<Integer> list = new ArrayList<>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[k]);
                            list.add(nums[l]);
                            long ll = changeNum(nums[i]) * 7 + changeNum(nums[j]) * (-19) + changeNum(nums[k]) * 37+ changeNum(nums[l]) * (-111);
                            Boolean aDefault = hashMap.getOrDefault(ll, false);
                            if (!aDefault) {
                                hashMap.put(ll, true);
                                res.add(list);
                            }
                        }
                        while (l+1 < nums.length && nums[l+1] == nums[l])l++;
                    }
                    while (k+1 < nums.length-1 && nums[k+1] == nums[k])k++;
                }
            }
        }
        return res;
    }

    private int changeNum(int num) {
        if (num == 0) return 23;
        else return num;
    }
}
//[[-10, -2, 8, 8], [-10, 0, 6, 8], [-10, 0, 7, 7], [-10, 1, 5, 8], [-10, 1, 6, 7], [-10, 3, 5, 6], [-9, -2, 7, 8], [-9, 0, 5, 8], [-9, 0, 6, 7], [-9, 1, 5, 7], [-8, -2, 6, 8], [-8, -2, 7, 7], [-8, 0, 5, 7], [-8, 1, 3, 8], [-8, 1, 5, 6], [-7, -2, 5, 8], [-7, -2, 6, 7], [-7, 0, 3, 8], [-7, 0, 5, 6], [-7, 1, 3, 7], [-2, 0, 0, 6], [-2, 0, 1, 5], [0, 0, 1, 3]]
//[[-10,-2,8,8],[-10,0,6,8],[-10,0,7,7],[-10,1,5,8],[-10,1,6,7],[-10,3,5,6],[-9,-2,7,8],[-9,0,5,8],[-9,0,6,7],[-9,1,5,7],[-8,-2,6,8],[-8,-2,7,7],[-8,0,5,7],[-8,1,3,8],[-8,1,5,6],[-7,-2,5,8],[-7,-2,6,7],[-7,0,3,8],[-7,0,5,6],[-7,1,3,7],[-2,0,0,6],[-2,0,1,5],[0,0,1,3]]
