package com.zt.my51;

import java.util.*;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-18 19:30
 */
public class Test56 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <=1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        LinkedList<int[]> list = new LinkedList<>(Arrays.asList(intervals));
        List<int[]> res = new ArrayList<>();
        int[] temp = list.pollFirst();
        res.add(temp);
        while (!list.isEmpty()) {
            int[] peek = list.pollFirst();
            assert temp != null;
            if (peek[0] <= temp[1]) {
                temp[1] = Math.max(temp[1],peek[1]);
            }else {
                res.add(peek);
                temp = peek;
            }
        }
        int[][] r = new int[res.size()][2];
        res.toArray(r);
        return r;
    }

    public static void main(String[] args) {
        Test56 t = new Test56();
        //int[][] matrix = new int[][]{
        //        {1,3},{2,6},{8,10},{15,18}
        //};
        int[][] matrix = new int[][]{
                {0,1},{0,3}
        };
        System.out.println(Arrays.deepToString(t.merge(matrix)));

    }
}
