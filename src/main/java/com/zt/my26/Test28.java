package com.zt.my26;

import java.beans.beancontext.BeanContext;
import java.util.Arrays;

/**实现 strStr() 函数。

 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

 示例 1:

 输入: haystack = "hello", needle = "ll"
 输出: 2
 示例 2:

 输入: haystack = "aaaaa", needle = "bba"
 输出: -1
 说明:

 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/implement-strstr
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-26 18:16
 */
public class Test28 {

    public static int[] next(String pattern) {
        int length = 0;
        int[] next = new int[pattern.length()];
        next[0] = 0;
        char[] chars = pattern.toCharArray();
        int i = 1;
        while (i < chars.length) {
            if (chars[i] == chars[length]) {
                next[i++] = ++length;
            } else {
                if (length > 0) {
                    length = next[length - 1];
                } else {
                    next[i] = 0;
                    i++;
                }
            }
        }
        //System.out.println(Arrays.toString(next));
        return next;
    }

    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (needle.length() > haystack.length()) return -1;
        int length = needle.length();
        String pattern = needle + "-" + haystack;
        int[] next = next(pattern);
        int index = -1;
        for (int i = length; i < next.length; i++) {
            if (next[i] == length) {
                index = i - 2 * length;
                break;
            }
        }
        return index;
    }

    public static void moveNext(int[] next) {
        if (next.length - 1 >= 0) System.arraycopy(next, 0, next, 1, next.length - 1);
        next[0] = -1;
    }

    public static void main(String[] args) {
        // "llhello"
        //System.out.println(strStr("hello","ll"));
        //System.out.println(strStr("aaa","aa"));
        //System.out.println(strStr("mississippi", "a"));
        Test28_1_KMP t = new Test28_1_KMP();
        System.out.println(t.strStr("aaa","aaa"));

    }
}

class Test28_1_KMP {
    public void moveNext(int[] next) {
        if (next.length - 1 >= 0) System.arraycopy(next, 0, next, 1, next.length - 1);
        next[0] = -1;
    }
    public  int[] next(String pattern) {
        int length = 0;
        int[] next = new int[pattern.length()];
        next[0] = 0;
        char[] chars = pattern.toCharArray();
        int i = 1;
        while (i < chars.length) {
            if (chars[i] == chars[length]) {
                next[i++] = ++length;
            } else {
                if (length > 0) {
                    length = next[length - 1];
                } else {
                    next[i] = 0;
                    i++;
                }
            }
        }
        //System.out.println(Arrays.toString(next));
        return next;
    }
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (needle.length() > haystack.length()) return -1;
        int[] next = next(needle);
        moveNext(next);
        char[] chars = haystack.toCharArray();
        char[] array = needle.toCharArray();
        int length = chars.length;
        int i = 0;
        int j = 0;
        while (i < length){
            if (j == array.length - 1 && chars[i] == array[j]){
                return i-j;
            }
            if (array[j] == chars[i]){
                i++;
                j++;
            }else {
                j = next[j];
                if (j == -1){
                    i++;
                    j++;
                }
            }
        }
        return -1;
    }
}
