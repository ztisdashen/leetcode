package com.zt.my51;

/**
 * 验证给定的字符串是否可以解释为十进制数字。
 * <p>
 * 例如:
 * <p>
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 * <p>
 * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
 * <p>
 * 数字 0-9
 * 指数 - "e"
 * 正/负号 - "+"/"-"
 * 小数点 - "."
 * 当然，在输入中，这些字符的上下文也很重要。
 * <p>
 * 更新于 2015-02-10:
 * C++函数的形式已经更新了。如果你仍然看见你的函数接收 const char * 类型的参数，请点击重载按钮重置你的代码。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-20 11:05
 * PASS 99%
 */
public class Test65 {
    public boolean isNumber(String s) {
        s = s.trim();
        if (s.length() == 0)return false;
        boolean positiveSign = false; // 是否出现正负号
        boolean eSign = false; // 是否出现 e
        boolean pointSign = false; // 是否出现 .
        boolean numberSign = false; // 是否出现数字符号

        char[] array = s.toCharArray();
        for (int i = 0;i<array.length;i++) {
            char c = array[i];
            if (eSign) {
                if (c >= '0' && c <= '9' ) continue;
                else if (c == '+' || c == '-'){
                    if (i+1 <array.length && array[i+1]>='0' && array[i+1]<='9')continue;
                    else return false;
                }
                else return false;
            }
            if (c == 'e' || c == 'E') {
                if (  i == array.length - 1 ||!numberSign)return false;
                eSign = true;
            }else
            if (c == '+' || c == '-') {
                if (positiveSign || i == array.length - 1 || numberSign || pointSign) return false;
                positiveSign = true;
            }else
            if (c == '.') {
                if (pointSign) return false;
                pointSign = true;
            }else
            if ((c >= 'a' && c < 'e') || (c > 'e' && c <= 'z') || c == ' ' || (c >= 'A' && c < 'E') || (c > 'E' && c <= 'Z') ) return false;
            else numberSign = true;
        }
        return numberSign;
    }

    public static void main(String[] args) {
        //String s = " ssss  ";
        //System.out.println(s.length() + " "+ s.trim().length());
        Test65 t = new Test65();
        System.out.println(t.isNumber(
                ".-4"));
        double a = 3E7;
    }

}
