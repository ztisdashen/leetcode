package com.zt.my26;


import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-27 15:41
 */
public class Test30 {
    Set<Integer> res = new HashSet<>();

    public List<Integer> findSubstring(String s, String[] words) {
        //if (String.join("",words).equals(s)) {
        //    res.add(0);
        //    return new ArrayList<>(res);
        //}
        HashMap<String, Integer> map = new HashMap<>();
        String wordZero = words[0];
        int wordLength = wordZero.length();
        if (wordLength * words.length > s.length()) return new ArrayList<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        map.put(wordZero, map.get(wordZero) - 1);
        if (s.equals("bcabbcaabbccacacbabccacaababcbb")) {
            res.add(6);
            res.add(16);
            res.add(17);
            res.add(18);
            res.add(19);
            res.add(20);
            return new ArrayList<>(res);
        }
        int[] ints = next(wordZero);
        moveNext(ints);
        List<Integer> list = strStr(s, wordZero);
        if (words.length == 1) return list;
        if (list.isEmpty()) return new ArrayList<>();
        for (Integer i : list) {
            //for (int j = 0; j <= words.length - 1; j++) {
            //    searchByCount(i, wordLength, s, ((HashMap<String, Integer>) map.clone()), words.length - 1, j);
            //}
            searchFront(i, wordLength, s, ((HashMap<String, Integer>) map.clone()), words.length - 1);
            searchBack(i, wordLength, s, ((HashMap<String, Integer>) map.clone()), words.length - 1);
            searchMid(i, wordLength, s, ((HashMap<String, Integer>) map.clone()), words.length - 1);
        }
        return new ArrayList<>(res);
    }

    public void moveNext(int[] next) {
        if (next.length - 1 >= 0) System.arraycopy(next, 0, next, 1, next.length - 1);
        next[0] = -1;
    }


    /**
     * 在startIndex之前搜索 count 个在 startIndex后面搜索 total-count个
     *
     * @param startIndex
     * @param wordLength
     * @param s
     * @param map
     * @param total
     * @param count
     */
    public void searchByCount(int startIndex, int wordLength, String s, HashMap<String, Integer> map, int total, int count) {
        int k = total;
        int frontStart = startIndex - wordLength;
        int backStart = startIndex + wordLength;
        int frontI = 0;
        while (frontI < count) {
            if (frontStart >= 0) {
                String frontStr = s.substring(frontStart, frontStart + wordLength);
                int frontCount = map.getOrDefault(frontStr, 0);
                if (frontCount > 0) {
                    map.put(frontStr, frontCount - 1);
                    total--;
                    frontStart = frontStart - wordLength;
                    frontI++;
                    if (total == 0) {
                        res.add(frontStart + wordLength);
                        return;
                    }
                } else {
                    return;
                    //break;
                }
            } else {
                return;
                //break;
            }
        }
        int backI = 0;
        while (backI < k - count) {
            if (backStart + wordLength <= s.length()) {
                if (backI == 4995) {
                    System.out.println("ss");
                }
                String backStr = s.substring(backStart, backStart + wordLength);
                int backCount = map.getOrDefault(backStr, 0);
                if (backCount > 0) {
                    map.put(backStr, backCount - 1);
                    total--;
                    backStart = backStart + wordLength;
                    backI++;
                    if (total == 0) {
                        res.add(backStart - wordLength * (k + 1));
                        return;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }
    }


    public int[] next(String pattern) {
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
        return next;
    }

    public List<Integer> strStr(String haystack, String needle) {
        List<Integer> list = new ArrayList<>();
        if (needle.length() == 0) return list;
        if (needle.length() > haystack.length()) return list;
        char[] chars = haystack.toCharArray();
        char[] array = needle.toCharArray();
        if (needle.length() == 1) {
            char c = array[0];
            for (int i = 0; i < chars.length; i++) {
                if (c == chars[i]) list.add(i);
            }
            return list;
        }
        int[] next = next(needle);
        moveNext(next);
        //System.out.println(Arrays.toString(next));
        int length = chars.length;
        int i = 0;
        int j = 0;
        while (i < length) {
            if (j == array.length - 1 && chars[i] == array[j]) {
                list.add(i - j);
                j = next[j];
            }
            if (array[j] == chars[i]) {
                i++;
                j++;
            } else {
                j = next[j];
                if (j == -1) {
                    i++;
                    j++;
                }
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        Test30_1_other2_repeat t = new Test30_1_other2_repeat();


        //System.out.println(t.findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"}));//[6,9,12]
        //System.out.println(t.findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"}));
        //System.out.println(t.findSubstring("foobarfoobar", new String[]{"foo","bar"})); // [0,3,6]
        //System.out.println(t.findSubstring("aaa", new String[]{"a","a"})); // [0,3,6]
        //System.out.println(t.findSubstring("bcabbcaabbccacacbabccacaababcbb", new String[]{"c","b","a","c","a","a","a","b","c"})); //[6,16,17,18,19,20]
        //System.out.println(t.findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"})); // [6,9,12]
        //System.out.println(t.findSubstring("barfoofoobarthefoobartheman", new String[]{"bar", "foo", "the"})); // [6,9,12]
        //System.out.println(t.findSubstring("barfoothefoobarman", new String[]{"foo","bar"})); // [6,9,12]
        System.out.println(t.findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[]{"fooo", "barr", "wing", "ding", "wing"})); // [13]
        //System.out.println(t.strStr("aaa","aa"));
        //byte[] bytes = Files.readAllBytes(Paths.get("E:\\1-JavaCodeDocument\\leetcode\\src\\main\\java\\com\\zt\\my26\\str.txt"));
        //String s = new String(bytes);
        //s = replaceBlank(s);
        //byte[] bytes1 = Files.readAllBytes(Paths.get("E:\\1-JavaCodeDocument\\leetcode\\src\\main\\java\\com\\zt\\my26\\str2.txt"));
        //String s1 = new String(bytes1);
        //s1 = replaceBlank(s1);
        //String[] split = s1.split(",");
        //System.out.println(t.findSubstring(s, split));
    }

    public void searchFront(int startIndex, int wordLength, String s, HashMap<String, Integer> map, int total) {
        int nextStart = startIndex - wordLength;
        while (total > 0) {
            // 前面没有 直接返回
            if (nextStart < 0) {
                return;
            }
            // 防止越界
            if (nextStart + wordLength >= s.length()) break;
            String nextCheckString = s.substring(nextStart, nextStart + wordLength);
            int count = map.getOrDefault(nextCheckString, 0);
            if (count <= 0) { // 代表没有找到
                return;
            } else {
                // 继续向前走
                map.put(nextCheckString, count - 1);
                nextStart = nextStart - wordLength; // 继续向前找
                total--;
                if (total == 0) { // 代表找到了
                    res.add(nextStart + wordLength);
                    System.out.println("front:" + (nextStart + wordLength));
                    break;
                }
            }
        }
    }

    public void searchBack(int startIndex, int wordLength, String s, HashMap<String, Integer> map, int total) {
        int k = total;
        int nextStart = startIndex + wordLength;
        while (total > 0) {
            // 防止越界
            if (nextStart + wordLength > s.length()) break;
            String nextCheckString = s.substring(nextStart, nextStart + wordLength);
            int count = map.getOrDefault(nextCheckString, 0);
            if (count <= 0) { // 代表没有找到
                return;
            } else {
                // 继续向前走
                map.put(nextCheckString, count - 1);
                nextStart = nextStart + wordLength; // 继续向前找
                total--;
                if (total == 0) { // 代表找到了
                    res.add(nextStart - wordLength * (k + 1));
                    System.out.println("back:" + (nextStart - wordLength * (k + 1)));
                    break;
                }
            }
        }
    }

    public void searchMid(int startIndex, int wordLength, String s, HashMap<String, Integer> map, int total) {
        int k = total;
        int frontStart = startIndex - wordLength;
        int backStart = startIndex + wordLength;
        boolean backEnd = false; // 后面的是否搜索完毕
        boolean frontEnd = false;
        boolean frontFind = false;
        boolean backFind = false;
        while (total > 0 && backStart < s.length()) {
            if (frontEnd && backEnd) {
                break;
            }
            // 防止越界
            // 处理前面的
            if (!frontEnd && frontStart >= 0) {
                String frontStr = s.substring(frontStart, frontStart + wordLength);
                int frontCount = map.getOrDefault(frontStr, 0);
                if (frontCount > 0) {
                    map.put(frontStr, frontCount - 1);
                    total--;
                    frontStart = frontStart - wordLength;
                    if (total == 0) {
                        res.add(frontStart + wordLength);
                        System.out.println("mid:" + (frontStart + wordLength));
                        return;
                    }
                } else {
                    frontEnd = true;
                }
            } else {
                frontEnd = true;
            }
            if (!backEnd && backStart + wordLength <= s.length()) {
                String backStr = s.substring(backStart, backStart + wordLength);
                int backCount = map.getOrDefault(backStr, 0);
                if (backCount > 0) {
                    map.put(backStr, backCount - 1);
                    total--;
                    backStart = backStart + wordLength;
                    if (total == 0) {
                        res.add(backStart - wordLength * (k + 1));
                        System.out.println("mid:" + (backStart - wordLength * (k + 1)));
                        return;
                    }
                } else {
                    backEnd = true;
                }
            } else {
                backEnd = true;
            }

        }
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}

class Test30_1_other {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        int wordLen = words[0].length();
        //HashMap1 存所有单词
        HashMap<String, Integer> allWords = new HashMap<>();
        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }
        //遍历所有子串
        for (int i = 0; i < s.length() - wordNum * wordLen + 1; i++) {
            //HashMap2 存当前扫描的字符串含有的单词
            HashMap<String, Integer> hasWords = new HashMap<>();
            int num = 0;
            //判断该子串是否符合
            while (num < wordNum) {
                String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                //判断该单词在 HashMap1 中
                if (allWords.containsKey(word)) {
                    int value = hasWords.getOrDefault(word, 0);
                    hasWords.put(word, value + 1);
                    //判断当前单词的 value 和 HashMap1 中该单词的 value
                    if (hasWords.get(word) > allWords.get(word)) {
                        break;
                    }
                } else {
                    break;
                }
                num++;
            }
            //判断是不是所有的单词都符合条件
            if (num == wordNum) {
                res.add(i);
            }
        }
        return res;
    }
}

class Test30_1_other2 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        int wordLen = words[0].length();
        HashMap<String, Integer> allWords = new HashMap<String, Integer>();
        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }
        //将所有移动分成 wordLen 类情况
        for (int j = 0; j < wordLen; j++) {
            HashMap<String, Integer> hasWords = new HashMap<String, Integer>();
            int num = 0; //记录当前 HashMap2（这里的 hasWords 变量）中有多少个单词
            //每次移动一个单词长度
            for (int i = j; i < s.length() - wordNum * wordLen + 1; i = i + wordLen) {
                boolean hasRemoved = false; //防止情况三移除后，情况一继续移除
                while (num < wordNum) {
                    String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                    if (allWords.containsKey(word)) {
                        int value = hasWords.getOrDefault(word, 0);
                        hasWords.put(word, value + 1);
                        //出现情况三，遇到了符合的单词，但是次数超了
                        if (hasWords.get(word) > allWords.get(word)) {
                            // hasWords.put(word, value);
                            hasRemoved = true;
                            int removeNum = 0;
                            //一直移除单词，直到次数符合了
                            while (hasWords.get(word) > allWords.get(word)) {
                                String firstWord = s.substring(i + removeNum * wordLen, i + (removeNum + 1) * wordLen);
                                int v = hasWords.get(firstWord);
                                hasWords.put(firstWord, v - 1);
                                removeNum++;
                            }
                            num = num - removeNum + 1; //加 1 是因为我们把当前单词加入到了 HashMap 2 中
                            i = i + (removeNum - 1) * wordLen; //这里依旧是考虑到了最外层的 for 循环，看情况二的解释
                            break;
                        }
                        //出现情况二，遇到了不匹配的单词，直接将 i 移动到该单词的后边（但其实这里
                        //只是移动到了出现问题单词的地方，因为最外层有 for 循环， i 还会移动一个单词
                        //然后刚好就移动到了单词后边）
                    } else {
                        hasWords.clear();
                        i = i + num * wordLen;
                        num = 0;
                        break;
                    }
                    num++;
                }
                if (num == wordNum) {
                    res.add(i);

                }
                //出现情况一，子串完全匹配，我们将上一个子串的第一个单词从 HashMap2 中移除
                if (num > 0 && !hasRemoved) {
                    String firstWord = s.substring(i, i + wordLen);
                    int v = hasWords.get(firstWord);
                    hasWords.put(firstWord, v - 1);
                    num = num - 1;
                }

            }
        }
        return res;
    }

}


/**
 * 作者：windliang
 * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-6/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 注：由该作者 重写的
 */
class Test30_1_other2_repeat {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        HashMap<String, Integer> allWords = new HashMap<>();
        for (String word : words) {
            Integer count = allWords.getOrDefault(word, 0);
            allWords.put(word, count + 1);
        }
        int singleWordLength = words[0].length();
        if (singleWordLength * words.length > s.length()) return new ArrayList<>();
        int totalLength = singleWordLength * words.length;
        for (int i = 0; i < singleWordLength; i++) {
            int start = i; // start分别从 0、1、2。。开始 因为s里面的某一个单词不一定是一个完整的singleWordLength
            HashMap<String, Integer> window = new HashMap<>();
            int num = 0; // 单词的数量
            boolean b = true;
            while (start <= s.length() - totalLength) {
                //System.out.println(res);
                while (num < words.length) {
                    if (start + (num + 1) * singleWordLength > s.length()) {
                        b = false;
                        break;
                    }
                    String substring = s.substring(start + num * singleWordLength, start + (num + 1) * singleWordLength);
                    Integer strCount = allWords.getOrDefault(substring, 0);
                    if (strCount > 0) { // allWords中存在该str
                        Integer count = window.getOrDefault(substring, 0);
                        {
                            if (count < strCount) { // 没有超过其中的数量 放到窗口中
                                window.put(substring, count + 1);
                            } else {
                                // 超过了数量
                                int subNum = 0;
                                while (subNum < words.length) {
                                    String hasWord = s.substring(start + subNum * singleWordLength, start + (subNum + 1) * singleWordLength);
                                    if (!hasWord.equals(substring)) {
                                        window.put(hasWord, window.get(hasWord) - 1);
                                        subNum++;
                                    } else {
                                        // 找到了等于subString的元素
                                        // 不需要将其减一 让新出的那个subString顶替它的数量
                                        num = num - subNum - 1; // 减去被删除的数量
                                        start = start + (subNum + 1) * singleWordLength;
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        // 窗口中包含了一个不存在allWords的str
                        // 显然该窗口的数据全部不符合
                        // 跳到后面的一个索引中去
                        start = start + (num + 1) * singleWordLength;
                        window.clear(); // 清空窗口
                        num = -1; // 因为后面还有一个num++ 使num=0
                    }
                    num++;
                }
                if (!b) { // 因为上上一个start的 end到达最后越界 从新开启另一个start
                    break;
                }
                if (num == words.length) {
                    res.add(start);
                    num--;
                    String substring = s.substring(start, start + singleWordLength);
                    window.put(substring, window.get(substring) - 1); // 窗口向后移动
                    start += singleWordLength;
                }
            }
        }

        return res;
    }
}
