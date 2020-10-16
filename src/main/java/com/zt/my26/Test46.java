package com.zt.my26;

import java.sql.Connection;
import java.util.*;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-14 21:25
 */
public class Test46 {
    List<List<Integer>> res = new ArrayList<>();
    int size = 0;

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 1) {
            List<Integer> p = new ArrayList<>();
            p.add(nums[0]);
            res.add(p);
            return res;
        }
        size = nums.length;
        // container -> stack
        // temp -> queue
        List<Integer> rest = new ArrayList<>();
        for (int i : nums) {
            rest.add(i);
        }
        doSomething(new ArrayList<>(), rest);
        return res;
    }

    public void doSomething(List<Integer> container, List<Integer> rest) {
        if (container.size() == size) {
            res.add(new ArrayList<>(container));
            return;
        }
        List<Integer> temp = new ArrayList<>(rest);
        if (temp.size() == 2) {
            container.addAll(temp);
            doSomething(container, temp);
            container.remove(container.size() - 1);
            container.remove(container.size() - 1);
            Collections.swap(temp, 0, 1);
            container.addAll(temp);
            doSomething(container, temp);
            container.remove(container.size() - 1);
            container.remove(container.size() - 1);
        } else {
            for (Integer integer : rest) {
                container.add(integer);
                temp.remove(0);
                doSomething(container, temp);
                container.remove(container.size() - 1);
                temp.add(integer);
            }
        }
    }

    public static void main(String[] args) {
        Test46 t = new Test46();
        System.out.println(t.permute(new int[]{1, 2, 3}));
    }
}

class Test46_1 {
    List<List<Integer>> res = new ArrayList<>();
    int size = 0;

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 1) {
            List<Integer> p = new ArrayList<>();
            p.add(nums[0]);
            res.add(p);
            return res;
        }
        size = nums.length;
        // container -> stack
        // temp -> queue
        LinkedList<Integer> rest = new LinkedList<>();
        for (int i : nums) {
            rest.add(i);
        }
        doSomething(new LinkedList<>(), rest);
        return res;
    }

    public void doSomething(LinkedList<Integer> container, LinkedList<Integer> rest) {
        if (container.size() == size) {
            res.add(new ArrayList<>(container));
            return;
        }
        LinkedList<Integer> temp = new LinkedList<>(rest);
        if (temp.size() == 2) {
            container.addAll(temp);
            doSomething(container, temp);
            container.pollLast();
            container.pollLast();
            Collections.swap(temp, 0, 1);
            container.addAll(temp);
            doSomething(container, temp);
            container.pollLast();
            container.pollLast();
        } else {
            for (Integer integer : rest) {
                container.add(integer);
                temp.removeFirst();
                doSomething(container, temp);
                container.pollLast();
                temp.add(integer);
            }
        }
    }

    public static void main(String[] args) {
        Test46 t = new Test46();
        System.out.println(t.permute(new int[]{1, 2, 3}));
    }
}
// PASS 90%
class Test46_2 {
    List<List<Integer>> res = new ArrayList<>();

    boolean[] group; // 通过group 进行分组

    public List<List<Integer>> permute(int[] nums) {
        group = new boolean[nums.length];
        func(new LinkedList<>(),nums);
        return res;
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
                    func(container,nums);
                    container.pollLast();
                    group[i] = false;
                //}
            }
        }
    }

    public static void main(String[] args) {
        Test46_2 t = new Test46_2();
        System.out.println(t.permute(new int[]{1,1,2}));
    }
}
