package com.zt.my51;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 说明:
 *
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * 示例:
 *
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 示例 2:
 *
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 *      第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例 3:
 *
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *          "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/text-justification
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-21 18:40
 * PASS 25%
 */
public class Test68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int curr = maxWidth;
        List<List<String>> lists = new ArrayList<>();
        List<String> currList = new ArrayList<>();
        List<String> result = new ArrayList<>();
        // 分组
        for (String word : words) {
            if (curr < word.length()) {
                lists.add(currList);
                curr = maxWidth;
                currList = new ArrayList<>();
            }
            curr -= word.length();
            if (curr >= 1) curr--;
            currList.add(word);
        }
        lists.add(currList);
        for (int index = 0; index < lists.size()-1; index++) {
            List<String> list = lists.get(index);
            int wordLenSum = 0;
            for (String s : list) {
                wordLenSum += s.length();
            }
            int spaceCount = list.size() - 1;
            if (spaceCount == 0) {
                // 代表只有一个单词
                String word = list.get(0);
                int space = maxWidth-word.length();
                StringBuilder s = genSpace(space);
                list.add(s.toString());
            }
            // space可以均匀分配
            else if ((maxWidth - wordLenSum) % spaceCount == 0) {
                int space = (maxWidth - wordLenSum) / spaceCount;
                StringBuilder s = genSpace(space);
                int k = 1;
                for (int i = 0; i < spaceCount; i++) {
                    list.add(k, s.toString());
                    k += 2;
                }
            }else if ((maxWidth - wordLenSum) % spaceCount != 0){
                int space = (maxWidth - wordLenSum) / spaceCount;
                int left = maxWidth - wordLenSum- spaceCount * space;
                StringBuilder s = genSpace(space);
                int k = 1;
                for (int i = 0; i < spaceCount; i++) {
                    if (i<left){
                        list.add(k, s.toString()+" ");
                    }else {
                        list.add(k, s.toString());
                    }
                    k += 2;
                }
            }
            StringBuilder l = new StringBuilder();
            list.forEach(l::append);
            result.add(l.toString());
        }
        // 处理最后一行
        StringBuilder lastLine = new StringBuilder();
        for (String s : lists.get(lists.size() - 1)) {
            lastLine.append(s);
            if (lastLine.length() < maxWidth){
                lastLine.append(" ");
            }
        }
        int left = maxWidth - lastLine.length();
        for (int i = 0; i < left ; i++) {
            lastLine.append(" ");
        }
        result.add(lastLine.toString());
        return result;
    }

    private StringBuilder genSpace(int space) {
        StringBuilder s = new StringBuilder(space);
        for (int i = 0; i < space; i++) {
            s.append(" ");
        }
        return s;
    }

    public static void main(String[] args) {
        Test68 t = new Test68();
        // This    is    an
        // "example  of text"
        // ["a   a   a   a   a  a  a","sssssssjustific        "]
        //System.out.println(t.fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
        System.out.println(t.fullJustify(new String[]{"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"}, 16));

    }
}
// ["This    is    an","example  of text","justifican. sss "]
