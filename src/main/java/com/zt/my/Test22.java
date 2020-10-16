package com.zt.my;


import java.util.*;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：n = 3
 * 输出：[
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * 通过次数182,48
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-25 13:32
 */
public class Test22 {
    Map<Integer, Set<String>> total = new HashMap<>();

    public List<String> generateParenthesis(int n) {
        if (n == 0) return new ArrayList<>();
        doString(n);
        return new ArrayList<>(total.get(n));
    }

    public void doString(int n) {
        if (n == 1) {
            Set<String> list = new HashSet<>();
            list.add("()");
            total.put(1, list);
            return;
        } else {
            doString(n - 1);
            Set<String> list = total.get(n - 1);
            Set<String> curr = new HashSet<>();
            for (String s : list) {
                char[] chars = s.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    String add = add(s, i);
                    curr.add(add);
                }
            }
            total.put(n, curr);
        }
    }

    public String add(String s, int index) {
        StringBuilder stringBuilder = new StringBuilder(s);
        stringBuilder.insert(index + 1, "()");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Test21_2_other t = new Test21_2_other();
        List<String> x = t.generateParenthesis(5);
        System.out.println(x);
        System.out.println(x.size());
    }
}

class Test21_1_other {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        generate(res, "", 0, 0, n);

        return res;
    }

    //count1统计“(”的个数，count2统计“)”的个数
    public void generate(List<String> res, String ans, int count1, int count2, int n) {

        if (count1 > n || count2 > n) return;

        if (count1 == n && count2 == n) res.add(ans);


        if (count1 >= count2) {
            generate(res, ans + "(", count1 + 1, count2, n);
            generate(res, ans + ")", count1, count2 + 1, n);

        }
    }
}
// DFS+少量的剪枝，剪枝的条件为：左括号的数目一旦小于右括号的数目，以及，左括号的数目和右括号数目均小于n。
class Test21_2_other {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        func(res, "", 0, 0, n);
        return res;
    }

    void func(List<String> res, String str, int l, int r, int n) {
        if (l > n || r > n || r > l) return; // 这里是剪枝
        if (l == n && r == n) {
            res.add(str);
            return;
        }
        func(res, str + '(', l + 1, r, n); // 如果是回溯的话 应该是传递一个 string builder 再deleteChatAt(k)
        func(res, str + ')', l, r + 1, n);
    }
}
