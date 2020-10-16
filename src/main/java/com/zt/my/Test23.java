package com.zt.my;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-26 10:13
 */
public class Test23 {
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> queue = new LinkedList<>(Arrays.asList(lists));
        while (queue.size() > 1){
            ListNode node1 = queue.poll();
            ListNode node2 = queue.poll();
            ListNode node = mergeTwoLists(node1, node2);
            queue.add(node);
        }
        return queue.poll();
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode list = new ListNode();
        ListNode last = list;
        ListNode t1 = l1;
        ListNode t2 = l2;
        while (t1 != null && t2 != null) {
            ListNode temp;
            if (t1.val < t2.val) {
                temp = new ListNode(t1.val);
                t1 = t1.next;
            } else {
                temp = new ListNode(t2.val);
                t2 = t2.next;
            }
            last.next = temp;
            last = temp;
        }
        while (t1 != null) {
            ListNode temp = new ListNode(t1.val);
            t1 = t1.next;
            last.next = temp;
            last = temp;
        }
        while (t2 != null) {
            ListNode temp = new ListNode(t2.val);
            t2 = t2.next;
            last.next = temp;
            last = temp;
        }
        return list.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n4;
        n4.next = n5;
        ListNode n1_1 = new ListNode(1);
        ListNode n3 = new ListNode(3);
        ListNode n4_1 = new ListNode(4);
        n1_1.next = n3;
        n3.next = n4_1;
        ListNode n2 = new ListNode(2);
        n2.next = new ListNode(6);
        Test23 t = new Test23();
        ListNode[] listNodes = new ListNode[]{n1,n1_1,n2};
        System.out.println(t.mergeKLists(listNodes));

    }
}
