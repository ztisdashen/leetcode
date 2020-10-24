package com.zt.my51;

import java.util.Arrays;
import java.util.Stack;
import java.util.UnknownFormatConversionException;

/**
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 * <p>
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 * <p>
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * 示例 2：
 * <p>
 * 输入："/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 * 示例 3：
 * <p>
 * 输入："/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * 示例 4：
 * <p>
 * 输入："/a/./b/../../c/"
 * 输出："/c"
 * 示例 5：
 * <p>
 * 输入："/a/../../b/../c//.//"
 * 输出："/c"
 * 示例 6：
 * <p>
 * 输入："/a//b////c/d//././/.."
 * 输出："/a/b/c"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/simplify-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-21 21:53
 *
 * 很麻烦
 */
public class Test71 {
    public String simplifyPath(String path) {
        if (path.length() == 1)return path;
        //if (path.startsWith("/..."))return "/...";
        StringBuilder s = new StringBuilder(path);
        s = deleteSinglePoint(s);
        s = filterCon(s);
        s = deleteLast(s);
        s = deleteDoublePoint(s);
        return s.toString();
    }

    /**
     * @param s 删除最后一个 /
     * @return
     */
    public StringBuilder deleteLast(StringBuilder s) {
        while (s.length()>1 && s.charAt(s.length() - 1) == '/') {
            s.deleteCharAt(s.length() - 1);
            //if (s.length() == 1)break;
        }
        return s;
    }

    public StringBuilder deleteSinglePoint(StringBuilder s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '.') {
                if (i + 1 < s.length() && s.charAt(i + 1) == '.') {
                    result.append(c);
                    result.append(c);
                    i++;
                    // 三个点
                    if (i+1<s.length() && s.charAt(i+1) == '.'){
                        result.append(c);i++;
                    }
                }
            } else result.append(c);
        }
        return result;
    }

    public StringBuilder deleteDoublePoint(StringBuilder s) {
        Stack<String> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        result.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '/') continue;
            else if (c == '.') {
                // TODO: 2020/10/22 连续的三个... 或者两个..
                if (i+2<s.length() && s.charAt(i+2) == '.'){
                    // 三个点
                    i+=2;
                    stack.push("...");
                }else {
                    if (!stack.isEmpty()) stack.pop();
                    i++;
                }
            } else {
                // c is a letter;
                int j = i + 1;
                while (j < s.length() && (s.charAt(j) >= 'a' && s.charAt(j) <= 'z')) j++;
                stack.push(s.substring(i, j));
                i = j - 1;
            }
        }
        stack.forEach(str->{
            result.append(str).append("/");
        });
        if (result.length() > 1){
            result.deleteCharAt(result.length() - 1);
        }
        return result;
    }

    /**
     * @param s 过滤连续的 /
     * @return
     */
    public StringBuilder filterCon(StringBuilder s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            result.append(c);
            if (c == '/') {
                int j = i + 1;
                while (j < s.length() && s.charAt(j) == '/') j++;
                i = j - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Test71 t = new Test71();
        //System.out.println(t.simplifyPath("/a//b////c/d//././/.."));
        //System.out.println(t.simplifyPath("/../"));
        //System.out.println(t.simplifyPath("/a/../../b/../c//.//"));
        //System.out.println(t.simplifyPath("/a/./b/../../c/"));
        //System.out.println(t.simplifyPath("/home//foo/"));
        System.out.println(t.simplifyPath("/abc/..."));
        //System.out.println(t.filterCon(new StringBuilder("/a//b////c/d//././/..")));
    }
}
// PASS
class Test71_1 {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        StringBuilder result = new StringBuilder("/");
        String[] strings = path.split("/");
        //System.out.println(Arrays.toString(strings));
        for (String s : strings) {
            if (s.length()>0){
                switch (s) {
                    case ".":
                        break;
                    case "..":
                        if (!stack.isEmpty()) {
                            stack.pop();
                        }
                        break;
                    default:
                        stack.push(s);
                        break;
                }
            }
        }
        stack.forEach(str->{
            result.append(str).append("/");
        });
        if (result.length() > 1){
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Test71_1 t = new Test71_1();
        System.out.println(t.simplifyPath("/a//b////c/d//././/.."));
        System.out.println(t.simplifyPath("/../"));
        System.out.println(t.simplifyPath("/a/../../b/../c//.//"));
        System.out.println(t.simplifyPath("/a/./b/../../c/"));
        System.out.println(t.simplifyPath("/home//foo/"));
        System.out.println(t.simplifyPath("/abc/..."));
    }
}
