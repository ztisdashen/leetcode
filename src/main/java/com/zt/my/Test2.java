package com.zt.my;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-12 10:40
 */
public class Test2 {
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode l1Temp = l1;
        ListNode l2Temp = l2;
        int i = 0; // 进位

        ListNode o = new ListNode(-1);
        ListNode last = o;
        while (l1Temp != null && l2Temp!=null){
            int tempNum = l1Temp.val + l2Temp.val + i;
            if (o.val == -1){
                o.val = tempNum % 10;
            }else {
                ListNode temp  = new ListNode((tempNum % 10));
                last.next = temp;
                last = temp;
            }
            // 得到进位
            i = tempNum / 10;

            l1Temp = l1Temp.next;
            l2Temp = l2Temp.next;
        }
        while (l1Temp!=null){
            int tempNum = l1Temp.val + i;
            if (o.val == -1){
                o.val = tempNum % 10;
            }else {
                ListNode temp  = new ListNode((tempNum % 10));
                last.next = temp;
                last = temp;
            }
            i = tempNum / 10;
            l1Temp = l1Temp.next;
        }
        while (l2Temp!=null){
            int tempNum = l2Temp.val + i;
            if (o.val == -1){
                o.val = tempNum % 10;
            }else {
                ListNode temp  = new ListNode((tempNum % 10));
                last.next = temp;
                last = temp;
            }
            i = tempNum / 10;
            l2Temp = l2Temp.next;
        }
        // 最后的进位 加进去
        if (i != 0)
            last.next = new ListNode(i);
        return o;
    }

    /**
     * 下面的这个方法存在的越界问题 不能直接相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Temp = l1;
        ListNode l2Temp = l2;
        long i = 1;
        long iNum = 0;

        while (l1Temp != null) {
            iNum = iNum + l1Temp.val * i;
            l1Temp = l1Temp.next;
            i *= 10;
        }
        long j = 1;
        long jNum = 0;

        while (l2Temp != null) {
            jNum = jNum + l2Temp.val * j;
            l2Temp = l2Temp.next;
            j *= 10;
        }
        long sum = jNum + iNum;
        ListNode o = new ListNode(-1);
        if (sum > 0){
            o = new ListNode((int) (sum % 10));
            sum = sum / 10;
        }
        ListNode last = o;
        while (sum > 0){
            if (o.val == -1){
                o = new ListNode((int) (sum % 10));
            }else {
                ListNode temp  = new ListNode((int) (sum % 10));
                last.next = temp;
                last = temp;
            }
            //s.push(sum % 10);
            sum = sum / 10;
        }
        if (o.val == -1)
            o.val = 0;
        return o;
    }

    public static void main(String[] args) {
        ListNode s = new ListNode(5);

        ListNode e = new ListNode(5);
        //e.next = new ListNode(9);
        //e.next.next = new ListNode(9);
        //e.next.next.next = new ListNode(9);
        //e.next.next.next.next = new ListNode(9);
        //e.next.next.next.next.next = new ListNode(9);
        //e.next.next.next.next.next.next = new ListNode(9);
        //e.next.next.next.next.next.next.next = new ListNode(9);
        //e.next.next.next.next.next.next.next.next = new ListNode(9);
        //e.next.next.next.next.next.next.next.next.next = new ListNode(9);
        Test2 a = new Test2();
        ListNode node = a.addTwoNumbers2(s, e);
        System.out.println(node);
    }
}

