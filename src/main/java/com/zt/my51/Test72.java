package com.zt.my51;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 * <p>
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-22 08:57
 */
public class Test72 {
    public int minDistance(String source, String target) {
        int[][] dp = new int[source.length() + 1][target.length() + 1];
        // 初始化 dp[0][j] 将长度为 0 的source经过 i 次增加操作变成了 target[1:i]
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        // 初始化 dp[0][j] 将长度为 j的source经过j次删除 为空串的target
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= source.length() ; i++) {
            for (int j = 1; j <= target.length(); j++) {
                int delete = dp[i - 1][j] + 1;
                int add = dp[i][j - 1] + 1;
                int replace = dp[i - 1][j - 1];
                if (source.charAt(i - 1) != target.charAt(j - 1))
                    replace++;
                dp[i][j] = Math.min(delete, Math.min(add, replace));
            }
        }
        return dp[source.length()][target.length()];
    }

    public static void main(String[] args) {
        Test72 t = new Test72();
        System.out.println(t.minDistance("horse","ros"));
        System.out.println(t.minDistance("horse","rose"));
        System.out.println(t.minDistance("intention","execution"));
    }
}
