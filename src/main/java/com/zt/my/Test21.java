package com.zt.my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-25 11:46
 */
public class Test21 {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        node1.next = node3;
        node3.next = node5;
        node5.next = node7;
        node2.next = node4;
        node4.next = node6;
        Test21_1 t = new Test21_1();
        System.out.println(t.mergeTwoLists(node1,node2));
    }
}
class Test21_1 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        List<Integer> list = new ArrayList<>();
        while (l1!=null){
            list.add(l1.val);
            l1 = l1.next;
        }
        while (l2!=null){
            list.add(l2.val);
            l2 = l2.next;
        }
        Collections.sort(list);

        ListNode res = new ListNode();
        ListNode last = res;
        for (Integer i : list) {
            last.next = new ListNode(i);
            last = last.next;
        }
        return res.next;
    }

}
