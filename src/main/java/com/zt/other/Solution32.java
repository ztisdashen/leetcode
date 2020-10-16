package com.zt.other;

import java.util.Map;

/**
 * 方法一：动态规划<br/>
 * 思路和算法<br/>
 * 使用dp[i]代表以 chars[i]结尾的最长有效括号的长度。初始化chars全为0<br/>
 * 由题意得 最长有效括号的结尾一定是')'，不可能是'('。<br/>
 * 因此 '('不可能成为最长有效括号的结尾 所以 if chars[i] = '(' => dp[i] = 0;<br/>
 * 其次分为两种基本情况:<br/>
 * 1. chars[i] = ')' && chars[i-1] = '(' <br/>
 * 所以 dp[i] = dp[i-2] + 2;  <br/>
 * chars[i]和chars[i-1]构成有效的"()" 代表公式中的 2 ;  <br/>
 * dp[i-2]指dp[i]还受与其相邻的最长有效括号长度的影响;
 * 2. chars[i] = ')' && chars[i-1] = ')'
 * dp[i] = dp[i-1] + 2 + dp[i - dp[i-1] - 2];  <br/>
 * 以下面为例：
 * chars = (,),(,(,),)
 * index = 0,1,2,3,4,5
 * dp    = 0,2,0,0,2,6
 * dp[i-1] 就是指 chars[i] 收到了 chars[i - 1] 的影响
 * 2 = i - dp[i-1] - 1 chars[2]是与chars[i]配对的另一个 '('
 * 公式中的 "+2"就是代表 chars[i] 和 chars[i - dp[i-1] - 1]
 * dp[i - dp[i-1] - 2] 代表与其相邻的最长有效括号
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-29 21:47
 */
public class Solution32 {
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        char[] chars = s.toCharArray();
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i > 0 && chars[i] == ')' && chars[i - 1] == '(') {
                dp[i] = 2;
                if (i - 2 >= 0) dp[i] += dp[i - 2];
            } else if (i > 0 && chars[i] == ')' && chars[i - 1] == ')' && i - dp[i - 1] - 1 >= 0) {
                dp[i] = dp[i - 1] + 2;
                if (i - dp[i - 1] - 2 >= 0) dp[i] += dp[i - dp[i - 1] - 2];
            }
            max = Math.max(dp[i],max);
        }
        return max;
    }
}
