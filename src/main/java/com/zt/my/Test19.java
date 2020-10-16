package com.zt.my;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-24 18:05
 */
public class Test19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = null;
        ListNode next = null;
        List<ListNode> list = new ArrayList<>();
        //while (head)
        ListNode temp = head;
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }
        if (list.size() - n - 1 >= 0) {
            pre = list.get(list.size() - n - 1);
        }
        if (n > 1) {
            next = list.get(list.size() - n + 1);
        }
        if (pre == null)
            return next;
        pre.next = next;
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Test19_2 t = new Test19_2();
        System.out.println(t.removeNthFromEnd(node5, 1));
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

// by map
class Test19_1 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Map<Integer, ListNode> map = new HashMap<>();
        ListNode temp = head;
        int i = 0;
        while (temp != null) {
            map.put(i++, temp);
            temp = temp.next;
        }
        if (n == i) {
            return head.next;
        } else {

            ListNode pre = map.get(i - n - 1);
            pre.next = pre.next.next;
            return head;
        }
    }
}

/**
 * 通过快慢指针法 fast、slow
 * 先让fast先走 n 步、再让fast和slow一起走
 * size - (size - n) = n
 * (size - n) 指fast走后list余下的node数
 * 当 slow 走了 (size - n) 也就代表它此刻处于倒数第n个位置
 */
class Test19_2 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        int i = 0;
        while (i < n) {
            fast = fast.next;
            i++;
        }
        if (fast == null) // 当fast == null 代表的就是移除头
            return head.next;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
