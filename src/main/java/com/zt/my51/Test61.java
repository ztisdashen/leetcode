package com.zt.my51;


import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-19 19:01
 * PASS 90%
 * 连成环状进行处理
 */
public class Test61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null)return null;
        if (head.next == null)return head;
        if (k == 0)return head;
        ListNode temp = head;
        int size = 1;
        List<ListNode> list = new ArrayList<>();
        list.add(head);
        while (temp.next != null){
            size++;
            temp = temp.next;
            list.add(temp);
        }
        // 因为 k 可能大于 size 通过取模限制范围
        int num = k % size;
        if (num == 0){
            return head;
        }
        temp.next = head;
        int index = size - num;
        ListNode newHeader = list.get(index);
        ListNode newTail = list.get(index - 1);
        newTail.next = null;
        return newHeader;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        Test61 t = new Test61();
        System.out.println(t.rotateRight(l1,2));
    }
}
