package com.zt.my26;

import java.util.*;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-15 20:40
 */
public class Test49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String temp = new String(chars);
            if (!map.containsKey(temp)) {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(temp, list);
            } else {
                map.get(temp).add(s);
                List<String> list = map.getOrDefault(temp, new ArrayList<>());
                list.add(s);
                map.put(temp, list);
            }
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Test49 t = new Test49();
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println(t.groupAnagrams(strs));
    }
}

class Test49_1 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String temp = new String(chars);

            List<String> list = map.getOrDefault(temp, new ArrayList<>());
            list.add(s);
            map.put(temp, list);

        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Test49 t = new Test49();
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println(t.groupAnagrams(strs));
    }
}
