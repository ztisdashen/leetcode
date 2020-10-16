package com.zt.my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class Test17 {
    List<String> result = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.equals(""))
            return result;
        char[] chars = digits.toCharArray();
        add(chars,0,new StringBuilder());
        return result;
    }

    private void add(char[] chars, int index, StringBuilder str) {
        if (index >= chars.length) {
            result.add(str.toString());
            return;
        }
        char[] search = search(chars[index]);
        for (char c : search) {
            add(chars, index + 1, str.append(c));
            str.deleteCharAt(str.length() - 1); // 回溯
        }
    }


    private char[] search(char num) {
        switch (num) {
            case '2':
                return new char[]{'a', 'b', 'c'};
            case '3':
                return new char[]{'d', 'e', 'f'};
            case '4':
                return new char[]{'g', 'h', 'i'};
            case '5':
                return new char[]{'j', 'k', 'l'};
            case '6':
                return new char[]{'m', 'n', 'o'};
            case '7':
                return new char[]{'p', 'q', 'r', 's'};
            case '8':
                return new char[]{'t', 'u', 'v'};
            case '9':
                return new char[]{'w', 'x', 'y', 'z'};
        }
        return new char[0];
    }

    public static void main(String[] args) {
        Test17 t = new Test17();
        System.out.println(t.letterCombinations("23"));
    }
}
