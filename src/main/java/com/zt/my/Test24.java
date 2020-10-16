package com.zt.my;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-26 12:00
 */
public class Test24 {
    public ListNode swapPairs(ListNode head) {
        return func(head);
    }

    ListNode func(ListNode next) {
        if (next == null) return null;
        else {
            if (next.next != null) {
                ListNode node = next.next;
                next.next = node.next;
                node.next = next;
                next.next = func(next.next);
                return node;
            } else return next;
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        Test24 t = new Test24();
        System.out.println(t.swapPairs(n1));
    }
}
