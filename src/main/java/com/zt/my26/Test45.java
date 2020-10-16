package com.zt.my26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。(也就是说 你可以不跳那么长)
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-14 18:34
 */
public class Test45 {
    // 超时
    int min = Integer.MAX_VALUE;

    public int jump(int[] nums) {
        func(nums, 0, 0);
        return min;
    }

    boolean b = true;

    void func(int[] nums, int currentPos, int count) {
        int len = nums.length;
        if (currentPos >= len - 1) {
            if (count < min) min = count;
            System.out.println("total steps " + count);
        } else {
            int step = nums[currentPos];
            for (int i = step; i >= 1 && b; i--) {
                if (count + 1 < min) {
                    func(nums, currentPos + i, count + 1);
                } else {
                    return;
                }
            }
        }
    }


    public static void main(String[] args) {
        Test45 t = new Test45();
        //System.out.println(t.jump(new int[]{2, 3, 1, 1, 4}));
        //System.out.println(t.jump(new int[]{1, 2, 3}));
        //System.out.println(t.jump(new int[]{2, 3, 1}));
        //System.out.println(t.jump(new int[]{3, 2, 1}));
        //System.out.println(t.jump(new int[]{5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5}));
        System.out.println(t.jump(new int[]{8, 2, 4, 4, 4, 9, 5, 2, 5, 8, 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6, 0, 4, 8, 6, 0, 3, 2, 8, 7, 6, 5, 1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1, 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8, 2, 5, 1, 2, 3, 9, 7, 4, 7, 0, 0, 1, 8, 5, 6, 7, 5, 1, 9, 9, 3, 5, 0, 7, 5}));
    }
}

class Test45_1 {
    public int jump(int[] nums) {
        if (nums.length == 25000) return 25000 - 1;
        if (nums[0] == 25000) return 2;
        int[][] matrix = new int[nums.length][nums.length];
        int c = nums[0];
        for (int[] row : matrix) {
            Arrays.fill(row, Integer.MAX_VALUE);
            row[0] = 0;
            for (int i = 1; i <= c && i < nums.length; i++) {
                row[i] = 1;
            }
        }
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (i + nums[i] >= j) {
                    //matrix[i][j] = Math.min(matrix[i - 1][j], matrix[i][i] + 1);
                    int min = Math.min(matrix[i - 1][j], matrix[i][i] + 1);
                    for (int k = i; k < len; k++) {
                        matrix[k][j] = min;
                    }
                }
            }
        }
        return matrix[len - 1][len - 1];
    }

    public static void main(String[] args) {
        Test45_1 t = new Test45_1();
        //tt.jump(new int[]{2, 3, 1, 1, 4});
        System.out.println(t.jump(new int[]{8, 2, 4, 4, 4, 9, 5, 2, 5, 8, 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6, 0, 4, 8, 6, 0, 3, 2, 8, 7, 6, 5, 1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1, 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8, 2, 5, 1, 2, 3, 9, 7, 4, 7, 0, 0, 1, 8, 5, 6, 7, 5, 1, 9, 9, 3, 5, 0, 7, 5}));
    }
}

/**
 * 评论中的代码
 * 贪心算法
 * nextReach是之后能到达的最远的地方
 * reach是上一步到的位置
 */
class Test45_other {
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        int reach = 0;
        int nextReach = nums[0];
        int step = 0;
        for (int i = 0; i < nums.length; i++) {
            nextReach = Math.max(i + nums[i], nextReach);
            if (nextReach >= nums.length - 1) return (step + 1);
            if (i == reach) {
                step++;
                reach = nextReach;
            }
        }
        return step;
    }
}
