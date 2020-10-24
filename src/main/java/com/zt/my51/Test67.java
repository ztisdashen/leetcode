package com.zt.my51;

/**
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * <p>
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *  
 * <p>
 * 提示：
 * <p>
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-20 15:19
 * PASS
 */
public class Test67 {
    public String addBinary(String a, String b) {
        if (a.equals("0") || b.equals("b")) return a.equals("0") ? b : a;
        if (a.length() < b.length()) {
            String temp = b;
            b = a;
            a = temp;
        }
        StringBuilder aB = new StringBuilder(a).reverse();
        StringBuilder bB = new StringBuilder(b).reverse();
        StringBuilder result = new StringBuilder();
        int jinwei = 0;
        for (int i = 0; i < a.length(); i++) {
            int t;
            if (i < b.length()) {
                t = aB.charAt(i) - '0' + bB.charAt(i) - '0' + jinwei;
            }else {
                t = aB.charAt(i) - '0' + jinwei;
            }
            jinwei = t / 2;
            t = t % 2;
            result.append(t);
        }
        if (jinwei!=0)result.append(jinwei);
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        Test67 t = new Test67();
        System.out.println(t.addBinary("1010","1011"));
    }
}
