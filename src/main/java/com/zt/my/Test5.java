package com.zt.my;



/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-14 08:23
 */
public class Test5 {
    public String longestPalindrome(String s) {
        if (s.length() == 0)
            return "";
        if (s.length() == 1)
            return s;
        //if (s.length() == 2){
        //
        //}

        int left = 0;
        int right = s.length() - 1;
        int max = 1;
        int minLeft = 0;
        int maxRight = 0;
        char[] chars = s.toCharArray();
        while (right >= 0 && left < s.length() && left <= right) {
            while (right >= 0 && chars[left] != chars[right]) {
                right--;
            }
            //String substring = s.substring(left, right + 1);
            // 如果长度小于max 就根本不用判断
            if (right + 1 - left > max) {
                boolean b = check(chars, left, right + 1);
                // 是回文字串
                if (b) {
                    max = right + 1 - left;
                    maxRight = right + 1;
                    minLeft = left;
                }
            }
            if (left == right) {
                right = s.length() - 1;
                left++;
            } else {
                right--;
            }

        }
        if (minLeft == 0 && maxRight == 0) maxRight = minLeft + max;
        return s.substring(minLeft, maxRight);
    }

    /**
     * 用字符串原生的equals
     *
     * @param sub 检查的子串
     * @return
     */
    private boolean check(String sub) {
        int len = sub.length();
        int mid = len / 2;
        String left;
        String rightReverse;
        if (len % 2 == 0) {
            left = sub.substring(0, len / 2);
            String right = sub.substring(len / 2);
            rightReverse = new StringBuilder(right).reverse().toString();

        } else {
            left = sub.substring(0, mid);
            String right = sub.substring(mid + 1);
            rightReverse = new StringBuilder(right).reverse().toString();
        }
        if (left.equals(rightReverse)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 从两边向中间找
     *
     * @param s 待查找的字符串
     * @return
     */
    public String longestPalindrome2(String s) {
        if (s.length() == 0)
            return "";
        if (s.length() == 1)
            return s;
        int max = s.length();
        int len = s.length();
        char[] chars = s.toCharArray();
        while (max >= 2) {
            int startIndex = 0;
            while (startIndex + max <= len) {
                boolean check = check(chars, startIndex, startIndex + max);
                if (check) {
                    return s.substring(startIndex, startIndex + max);
                }
                startIndex++;
            }
            max--;
        }
        return s.substring(0, 1);
    }

    private boolean check(char[] chars, int left, int right) {
        int mid = (left + right) / 2;
        //int len = right - left;
        int l = left;
        int total = 0;
        int t = 0;
        while (l < mid) {
            int i = chars[l] - chars[right - t - 1];
            total += Math.abs(i);
            l++;
            t++;
        }
        return total == 0;
    }

    public static void main(String[] args) {
        Test5_2 t = new Test5_2();
        System.out.println(t.longestPalindrome("aaabaaaa"));
    }
}

/**
 * 基本思想就是当一个子串不是回文字串时 则凡是该子串占据中间位置的字符串一定不是回文
 * 只有不会回文的字串在另一半时才可能是回文
 * 例如 gkabcdcbafn 显然gk一定不是回文 则只有在gka或者gkab这样的才可能出现回文
 * 当非回文子串中 sub.len = 2 下一次在 sub.len = 3 或者在sub.len = 4中查找
 * 当sub.len = 3 时 则在sub.len = 5 或者 sub.len = 6 中查找
 *
 * @author zt648
 */
class Test5_2 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return "";
        if (s.length() == 1)
            return s;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            doSomeThing(chars, i, i+2);
        }
        return s.substring(minLeft,maxRight);
    }

    /**
     * @param chars
     * @param left  左边的索引
     * @param right 最右边的索引 + 1 right - left 就是子串的总长度
     */
    private void doSomeThing(char[] chars, int left, int right) {
        if (right > chars.length)
            return;
        if (!check(chars, left, right)) {
            doSomeThing(chars, left, (right - left) * 2 + left - 1);
            doSomeThing(chars, left, (right - left) * 2 + left);
        } else {
            more(chars, left, right);
        }
    }

    /**
     * 检查是不是回文
     *
     * @return
     */
    private boolean check(char[] chars, int left, int right) {
        int r = right - 1;
        int l = left;
        while (l <= r) {
            if (chars[l] != chars[r])
                return false;
            l++;
            r--;
        }
        return true;
    }

    int minLeft = 0;
    int maxRight = 1;

    /**
     * 当检查到是回文子串时 向外扩展
     */
    private void more(char[] chars, int left, int right) {
        //while (left > 0 && chars[left-1] == chars[left])left--;
        //while (right < chars.length && chars[right] == chars[right-1])right++;
        if (right - left == 2 && chars[left] == chars[right-1]){
            while (left > 0 && chars[left-1] == chars[left])left--;
            while (right < chars.length && chars[right] == chars[right-1])right++;
        }
        while (left > 0 && right < chars.length && chars[left - 1] == chars[right]) {
            left--;
            right++;
        }
        if (right - left > maxRight - minLeft) {
            maxRight = right;
            minLeft = left;
        }
    }
}
