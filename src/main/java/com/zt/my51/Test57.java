package com.zt.my51;

import java.sql.Connection;
import java.util.*;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *  
 *
 * 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-18 20:23
 */
public class Test57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) return new int[][]{newInterval};
        LinkedList<int[]> list = new LinkedList<>(Arrays.asList(intervals));
        list.add(newInterval);
        list.sort(Comparator.comparingInt(o -> o[0]));
        List<int[]> res = new ArrayList<>();
        int[] temp = list.pollFirst();
        res.add(temp);
        while (!list.isEmpty()) {
            int[] peek = list.pollFirst();
            assert temp != null;
            if (peek[0] <= temp[1]) {
                temp[1] = Math.max(temp[1], peek[1]);
            } else {
                res.add(peek);
                temp = peek;
            }
        }
        int[][] r = new int[res.size()][2];
        res.toArray(r);
        return r;
    }
// PASS 80%
    public int[][] insert1(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) return new int[][]{newInterval};
        LinkedList<int[]> list = new LinkedList<>();
        boolean b = true;
        for (int i = 0; i < intervals.length; i++) {
            if (b && newInterval[0] <= intervals[i][1]) {
                b = false;
                if (newInterval[1]<intervals[i][0]){
                    list.add(newInterval);
                    list.add(intervals[i]);
                }else {
                    intervals[i][0] = Math.min(newInterval[0], intervals[i][0]);
                    if (newInterval[1] >= intervals[i][1]) {
                        // 不包含newInterval
                        intervals[i][1] = newInterval[1];
                        int j = i + 1;
                        while (j < intervals.length && intervals[j][0] <= newInterval[1]) {
                            j++;
                        }
                        if (j < intervals.length || newInterval[1] <= intervals[j-1][1]) {
                            intervals[i][1] = Math.max(intervals[i][1],intervals[j-1][1]);
                            list.add(intervals[i]);
                            i = j - 1;
                        } else {
                            list.add(intervals[i]);
                            break;
                        }
                    } else {
                        // 包含newInterval
                        list.add(intervals[i]);
                    }
                }


            } else {
                list.add(intervals[i]);
            }

        }
        if (b) {
            list.add(newInterval);
        }
        return list.toArray(new int[list.size()][2]);
    }

    public static void main(String[] args) {
        Test57 t = new Test57();
        //int[][] matrix = new int[][]{
        //        {1,3},{6,9}
        //};
        //System.out.println(Arrays.deepToString(t.insert1(matrix, new int[]{2,5})));
        //int[][] matrix = new int[][]{
        //        {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}
        //};
        ////System.out.println(Arrays.deepToString(t.insert1(matrix, new int[]{4, 8})));
        //int[][] matrix = new int[][]{
        //        {1,5}
        //};
        //System.out.println(Arrays.deepToString(t.insert1(matrix, new int[]{2,7})));
        //int[][] matrix = new int[][]{
        //        {1,5}
        //};
        //System.out.println(Arrays.deepToString(t.insert1(matrix, new int[]{0,0})));
        int[][] matrix = new int[][]{
                {1,5},{6,8}
        };
        System.out.println(Arrays.deepToString(t.insert1(matrix, new int[]{5,6})));

    }
}
