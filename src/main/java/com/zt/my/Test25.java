package com.zt.my;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 给你这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * <p>
 * <p>
 * 说明：
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-26 13:34
 */
public class Test25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1)return head;
        ListNode temp = head;
        int size = 0;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        ListNode t = new ListNode(-1, head);
        ListNode finalRes = null;
        for (int i = 0; i <= size-k; i += k) {
            ListNode[] nodes = reverse(t.next, k);
            if (i == 0) finalRes = nodes[0];
            else {t.next = nodes[0];}
            t = nodes[1];
        }
        return finalRes;
    }

    public ListNode[] reverse(ListNode head, int k) {
        ListNode[] res = new ListNode[2];
        ListNode newHead = head;
        int count = 1;
        while (count < k && head.next != null) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = newHead;
            newHead = next;
            res[0] = newHead;
            res[1] = head;
            count++;

        }
        return res;
    }

    public static void main(String[] args) {
        Test25 t = new Test25();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        System.out.println(t.reverseKGroup(n1, 5));
    }
}
