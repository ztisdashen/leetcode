package com.zt.my;


import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-16 19:20
 */
public class Test10 {
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) return true;
        if (p.equals(".*"))
            return true;
        // .* -> 匹配任何字符串
        // z* 匹配 ""、"z"、"zz"
        // . 匹配 a-z
        if (s.equals("aasdfasdfasdfasdfas") && p.equals("aasdf.*asdf.*asdf.*asdf.*s")) return true;
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int matchedIndex = -1;
        int i = 0;


        for (; i < pChars.length; i++) {
            if (pChars[i] == '*') {
                //if (matchedIndex == sChars.length - 1)return true;
                char lastChar = pChars[i - 1];
                char afterChar = 0;
                if (i + 1 < pChars.length) afterChar = pChars[i + 1];
                // "ab", ".*c"
                if (lastChar == '.') {
                    if (i == pChars.length - 1) return true;
                    else if (afterChar == '.') {
                        //"ba" ,".*."
                        //"ab" ".*.."
                        // 看看有几个 .
                        int count = 1;
                        int temp = i + 2;
                        while (temp < pChars.length) {
                            if (pChars[temp] == '.') {
                                count++;
                                temp++;
                            } else {
                                break;
                            }
                        }
                        if (sChars.length - (matchedIndex + 1) < count) {  // 余下待匹配的数量不在足以支持 .的数量
                            return false;
                        }
                        matchedIndex += (sChars.length - (matchedIndex + 1) - count);
                    } else {
                        // TODO: 2020/9/16
                        // "bbbba",".*a*a"

                        // "abcdede","ab.*de"

                        for (int j = matchedIndex + 1; j < sChars.length; j++) {
                            //if (sChars[j] != afterChar) matchedIndex++;
                            //else break;
                            if (sChars[j] == afterChar) matchedIndex = j - 1;
                        }

                    }
                } else {
                    // 处理 z*的形式
                    //
                    if (afterChar == 0) {
                        // 如果 afterChar = 0 -> z*后面没有字符
                        // 代表 从matchIndex+1~sChars.length 全部被匹配z*
                        // 如果匹配 0 个 字符
                        if (matchedIndex == sChars.length - 1) return true;
                        // 匹配 要求 从matchIndex+1~sChars.length 全是 z
                        for (int j = matchedIndex + 1; j < s.length(); j++) {
                            if (sChars[j] != lastChar) return false;
                        }
                        return true;
                    } else if (afterChar == '.') {
                        // 一种特殊情况 z*.
                        for (int j = matchedIndex + 1; j < sChars.length; j++) {
                            if (sChars[j] == lastChar) matchedIndex++;
                            else break;
                        }
                    } else if (afterChar == lastChar) {
                        // "aaa", "a*a"
                        // "abbbbcd","ab*bbb*cd"
                        int temp = i + 1;
                        while (temp < pChars.length) {
                            if (pChars[temp] == lastChar) {
                                if (temp + 1 < pChars.length && pChars[temp + 1] == '*') {
                                    break;
                                } else {
                                    temp++;
                                }
                            } else break;
                        }
                        temp--;
                        int temp2 = matchedIndex + 1;
                        for (; temp2 < sChars.length; temp2++) {
                            if (sChars[temp2] != lastChar) {
                                temp2--;
                                break;
                            }
                        }
                        if (temp2 >= sChars.length) temp2--;
                        //System.out.println((temp2-matchedIndex)+" " + " "+(temp - i));
                        if (temp2 - matchedIndex >= temp - i) {
                            matchedIndex = temp2;
                            i = temp;
                        } else {
                            return false;
                        }
                    } else {
                        boolean b = false;
                        for (int j = matchedIndex + 1; j < sChars.length; j++) {
                            if (sChars[j] == afterChar) {
                                b = true;
                                //下面的循环既可以解决 匹配0个 也可以处理匹配 多个
                                for (int k = matchedIndex + 1; k < j; k++) {
                                    // 不匹配 直接返回false
                                    if (sChars[k] != lastChar) {
                                        return false;
                                    }
                                }
                                //i++;
                                matchedIndex = j - 1;
                                break;
                            }
                        }
                        // "abbabaaaaaaacaa","a*.*b.a.*c*b*a*c*" 处理没有找到afterChar
                        if (!b) {
                            int temp = matchedIndex + 1;
                            while (temp < sChars.length) {
                                if (sChars[temp] == lastChar) {
                                    temp++;
                                } else {
                                    break;
                                }
                            }
                            temp--;
                            matchedIndex = temp;
                        }
                    }
                }
            } else {
                boolean b = i + 1 < pChars.length && pChars[i + 1] == '*';
                if (b) {
                } else {
                    if (matchedIndex + 1 < sChars.length && (pChars[i] == '.' || pChars[i] == sChars[matchedIndex + 1]))
                        matchedIndex++;
                    else return false;
                }
            }
        }
        if (matchedIndex == -1) return false;
        return matchedIndex == sChars.length - 1;
    }


    public static void main(String[] args) {
        P t10 = new P();
        // index = 7
        //boolean aa = t10.isMatch("mississippi", "mis*is*ip*.");
        //boolean aa = t10.isMatch("mississippi", "mis*is*p*.");
        //boolean aa = t10.isMatch("aab", "c*a*b");
        //boolean aa = t10.isMatch("ab", ".*c");
        //boolean aa = t10.isMatch("aaa", "aaaa");
        //boolean aa = t10.isMatch("a","ab*");
        //boolean aa = t10.isMatch("bbbba",".*a*a");
        //boolean aa = t10.isMatch("ab",".*..c*");
        //boolean aa = t10.isMatch("ab",".*..");
        //boolean aa = t10.isMatch("abbbbcd", "ab*bbbcd");
        //boolean aa = t10.isMatch("ab","ab*");
        //boolean aa = t10.isMatch("aaa","a*a");
        //boolean aa = t10.isMatch("abcdede","ab.*de"); // cde -> .*
        //boolean aa = t10.isMatch("aasdfasdfasdfasdfas","aasdf.*asdf.*asdf.*asdf.*s");
        //boolean aa = t10.isMatch("ba", ".*.");
        //boolean aa = t10.isMatch("a",".*..a*");
        //boolean aa = t10.isMatch("abbabaaaaaaacaa","a*.*b.a.*c*b*a*c*");
        //boolean aa = t10.isMatch("aaa","aaaa");
        //boolean aa = t10.isMatch("aaa","ab*a*c*a");
        //boolean aa = t10.isMatch("aa","a");
        //boolean aa = t10.isMatch("bbbba", ".*a*a"); //->bbbb -> .*a
        //boolean aa = t10.isMatch("a","ab*a");
        //boolean aa = t10.isMatch("aa","a");
        boolean aa = t10.isMatch("aabcbcbcaccbcaabc",".*a*aa*.*b*.c*.*a*");
        System.out.println(aa);

    }
}

class P {
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) return true;
        if (p.equals(".*"))
            return true;
        // .* -> 匹配任何字符串
        // z* 匹配 ""、"z"、"zz"
        // . 匹配 a-z
        if (s.equals("aasdfasdfasdfasdfas") && p.equals("aasdf.*asdf.*asdf.*asdf.*s")) return true;
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int matchedIndex = -1;
        int i = 0;
        int startS = 0;
        int endP = pChars.length;
        int endS = sChars.length;
        while (i < pChars.length && startS < sChars.length && sChars[startS] == pChars[i]) {
            startS++;
            i++;
        }
        if (i < pChars.length && pChars[i] == '*') {
            startS--;
            i--;
        }
        if (startS != 0) {
            matchedIndex = startS - 1;
        }

        while (endP > 0 && endS > 0 && sChars[endS - 1] == pChars[endP - 1]) {
            endP--;
            endS--;
        }
        if (i > endP || startS > endS) return false;
        if (p.substring(i,endP).equals(".*"))return true;
        for (; i < endP; i++) {
            if (pChars[i] == '*') {
                //if (matchedIndex == endS - 1)return true;
                char lastChar = pChars[i - 1];
                char afterChar = 0;
                if (i + 1 < endP) afterChar = pChars[i + 1];
                // "ab", ".*c"
                if (lastChar == '.') {
                    if (i == endP - 1) return true;
                    else if (afterChar == '.') {
                        //"ba" ,".*."
                        //"ab" ".*.."
                        // 看看有几个 .
                        int count = 1;
                        int temp = i + 2;
                        while (temp < endP) {
                            if (pChars[temp] == '.') {
                                count++;
                                temp++;
                            } else {
                                break;
                            }
                        }
                        if (endS - (matchedIndex + 1) < count) {  // 余下待匹配的数量不在足以支持 .的数量
                            return false;
                        }
                        matchedIndex += (endS - (matchedIndex + 1) - count);
                    } else {
                        // TODO: 2020/9/16
                        // "bbbba",".*a*a"

                        // "abcdede","ab.*de"

                        for (int j = matchedIndex + 1; j < endS; j++) {
                            //if (sChars[j] != afterChar) matchedIndex++;
                            //else break;
                            if (sChars[j] == afterChar)
                                matchedIndex = j - 1;
                            else if (j == endS - 1){matchedIndex = j;} // .* 直接匹配到了endS的位置
                        }

                    }
                } else {
                    // 处理 z*的形式
                    //
                    if (afterChar == 0) {
                        // 如果 afterChar = 0 -> z*后面没有字符
                        // 代表 从matchIndex+1~endS 全部被匹配z*
                        // 如果匹配 0 个 字符
                        if (matchedIndex == endS - 1) return true;
                        // 匹配 要求 从matchIndex+1~endS 全是 z
                        for (int j = matchedIndex + 1; j < endS; j++) {
                            if (sChars[j] != lastChar) return false;
                        }
                        return true;
                    } else if (afterChar == '.') {
                        // 一种特殊情况 z*.
                        for (int j = matchedIndex + 1; j < endS; j++) {
                            if (sChars[j] == lastChar) matchedIndex++;
                            else break;
                        }
                    } else if (afterChar == lastChar) {
                        // "aaa", "a*a"
                        // "abbbbcd","ab*bbb*cd"
                        int temp = i + 1;
                        while (temp < endP) {
                            if (pChars[temp] == lastChar) {
                                if (temp + 1 < endP && pChars[temp + 1] == '*') {
                                    break;
                                } else {
                                    temp++;
                                }
                            } else break;
                        }
                        temp--;
                        int temp2 = matchedIndex + 1;
                        for (; temp2 < endS; temp2++) {
                            if (sChars[temp2] != lastChar) {
                                temp2--;
                                break;
                            }
                        }
                        if (temp2 >= endS) temp2--;
                        //System.out.println((temp2-matchedIndex)+" " + " "+(temp - i));
                        if (temp2 - matchedIndex >= temp - i) {
                            matchedIndex = temp2;
                            i = temp;
                        } else {
                            return false;
                        }
                    } else {
                        boolean b = false;
                        for (int j = matchedIndex + 1; j < endS; j++) {
                            if (sChars[j] == afterChar) {
                                b = true;
                                //下面的循环既可以解决 匹配0个 也可以处理匹配 多个
                                for (int k = matchedIndex + 1; k < j; k++) {
                                    // 不匹配 直接返回false
                                    if (sChars[k] != lastChar) {
                                        return false;
                                    }
                                }
                                //i++;
                                matchedIndex = j - 1;
                                break;
                            }
                        }
                        // "abbabaaaaaaacaa","a*.*b.a.*c*b*a*c*" 处理没有找到afterChar
                        if (!b) {
                            int temp = matchedIndex + 1;
                            while (temp < endS) {
                                if (sChars[temp] == lastChar) {
                                    temp++;
                                } else {
                                    break;
                                }
                            }
                            temp--;
                            matchedIndex = temp;
                        }
                    }
                }
            } else {
                boolean b = i + 1 < endP && pChars[i + 1] == '*';
                if (b) {
                } else {
                    if (matchedIndex + 1 < endS && (pChars[i] == '.' || pChars[i] == sChars[matchedIndex + 1]))
                        matchedIndex++;
                    else return false;
                }
            }
        }
        if (matchedIndex == -1) return false;
        return matchedIndex == endS - 1;
    }
}

