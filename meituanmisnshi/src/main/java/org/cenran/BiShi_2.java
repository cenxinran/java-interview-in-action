package org.cenran;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/***
 * 第二题
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照顺序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相减，并以相同形式返回一个表示相减结果的链表。
 * 你可以假设
 * 1）除了数字 0 之外，这两个数都不会以 0 开头。
 * 2）给定的第一数字一定比第二个数字大。
 * 举例：
 * 输入：l1 = [9,8,7], l2 = [5,1,2]
 * 输出：[4,7,5]
 * 解释：987-512 = 475.
 */


/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-06  21:32
 * @Description: 纬创-美团-笔试_2
 * @Version: 1.0
 */
public class BiShi_2 {
    public static void main(String[] args) {
        int[] arr1 = {9, 8, 7};
        int[] arr2 = {5, 1, 2};

        ListNode listNode1 = list2Node(arr1);
        ListNode listNode2 = list2Node(arr2);

        ListNode res = cut(listNode1, listNode2);

        System.out.println("res = " + res.toString());
    }

    public static ListNode list2Node(int[] arr) {
        ListNode head = new ListNode(-1);
        ListNode temp = null;

        temp = head;

        for (int i = 0; i < arr.length; i++) {
            temp.next = new ListNode(arr[i]);
            temp = temp.next;
        }
        return head.next; // 返回该链表的头节点
    }

    public static ListNode cut(ListNode head1, ListNode head2) {
        ListNode ans = new ListNode(-1);
        ListNode ptr = ans; // ans 是头节点
        int pre = 0; // 表示借位

        // 反转链表 从尾部往前计算每一位的差值
        head1 = reverseList(head1);
        head2 = reverseList(head2);

        // 有位可减时
        while (head1 != null && head2 != null) {
            int differ = head1.val - head2.val + pre;
            pre = differ < 0 ? -1 : 0;
            if (differ < 0) {
                differ = (differ + 10) % 10;
            }
            ptr.next = new ListNode(differ);
            ptr = ptr.next;
            head1 = head1.next;
            head2 = head2.next;
        }

        //无位可减时
        while (head1 != null) {

            int differ = head1.val + pre;
            pre = differ < 0 ? -1 : 0;
            if (differ < 0) {
                differ = (differ + 10) % 10;
            }
            ptr.next = new ListNode(differ);
            ptr = ptr.next;
            head1 = head1.next;
        }

        // 翻转回来
        return reverseList(ans.next);
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            // 下一个节点存到next中
            ListNode next = curr.next;
            // 为新链表设置next指向本来的前节点prev
            curr.next = prev;
            // 滚动到下一个节点进行循环处理
            prev = curr;
            curr = next;
        }
        return prev;
    }



    static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}


