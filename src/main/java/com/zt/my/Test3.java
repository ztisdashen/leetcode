package com.zt.my;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个题判断的是在一个子串中有没有重复的字符！！！！
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-12 22:20
 */
public class Test3 {
    /**
     * 以这个字符串为例：abcabcbb，当i等于3时，也就是指向了第二个a,
     * 此时我就需要查之前有没有出现过a, 如果出现了是在哪一个位置出现的。
     * 然后通过last[index] 查到等于1, 也就是说，如果start 依然等于0的话，
     * 那么当前窗口就有两个a了，也就是字符串重复了，所以我们需要移动当前窗口的start指针，
     * 移动到什么地方呢？移动到什么地方，窗口内就没有重复元素了呢？
     * 对了，就是a上一次出现的位置的下一个位置，就是1 + 1 = 2。当start == 2,
     * 当前窗口就没有了重复元素，那么以当前字符为结尾的最长无重复子串就是bca,
     * 然后再和之前的res取最大值。然后i指向后面的位置，按照同样思路计算。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        // 初始都没有出现过
        for(int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        // 维护了一个从start ~ i的窗口
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res   = Math.max(res, i - start + 1);
            last[index] = i; //记录位置
        }

        return res;
    }
    public int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0)
            return 0;
        int max = 1;
        int currentLength = 2;
        // max最大就是 95
        while (currentLength <= s.length() && max < 95) {
            System.out.println(currentLength+" "+max);
            int startIndex = 0;
            while (startIndex <= s.length() - currentLength) {
                int endIndex = currentLength + startIndex;
                String currentSubString = s.substring(startIndex, endIndex);
                // 检查currentSubString是否存在相同的字符
                // 如果没有 currentIndex可以++,同时破坏循环break;
                Map<Character,Boolean> map = new HashMap<>(currentSubString.length());
                for (char c : currentSubString.toCharArray()) {
                    map.put(c,true);
                }
                boolean has = map.size() == currentLength;
                if (has){
                    // 不存在重复子串
                    // 当前length不用检查
                    max = currentLength;
                    break;
                }

                startIndex++;
            }
            currentLength++;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
        Test3 a = new Test3();
        int i = a.lengthOfLongestSubstring("abcabcbb");
        System.out.println(i);
        System.out.println(LocalDateTime.now());

    }
}
