package org.cenran;

import java.util.ArrayList;

/*
* 第二题
给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照顺序的方式存储的，并且每个节点只能存储一位数字。
请你将两个数相减，并以相同形式返回一个表示相减结果的链表。
你可以假设
1）除了数字 0 之外，这两个数都不会以 0 开头。
2）给定的第一数字一定比第二个数字大。
举例：
输入：l1 = [9,8,7], l2 = [5,1,2]
输出：[4,7,5]
解释：987-512 = 475.
* */

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-06  21:56
 * @Description: 纬创-美团-笔试题_2
 * @Version: 1.0
 */
public class DemoBiShi_2 {

    public static void main(String[] args) {


        int[] nums1 = {6, 9};
        int[] nums2 = {5, 5, 7};

        int len_1 = nums1.length;
        int len_2 = nums2.length;

        ListNode head1 = addListNode(nums1);
        ListNode head2 = addListNode(nums2);


        if (len_1 > len_2) {  // 大数减小数
            System.out.println(listToArrayList(head1));
            System.out.print("-  ");
            System.out.println(listToArrayList(head2));
            System.out.println(listToArrayList(minusInList(head1, head2)));
        } else {  // 小数减大数
            System.out.println(listToArrayList(head1));
            System.out.print("-  ");
            System.out.println(listToArrayList(head2));
            System.out.println("- " + listToArrayList(minusInList(head2, head1)));
        }
    }


    public static ListNode minusInList(ListNode head1, ListNode head2) {

        ListNode ans = new ListNode(-1);
        ListNode p = ans; // ans 是头节点
        int pre = 0; // 表示借位

        // 反转链表 从尾部往前计算每一位的差值
        head1 = reverse(head1);
        head2 = reverse(head2);

        while (head1 != null && head2 != null) {
            int differ = head1.val - head2.val + pre;
            pre = differ < 0 ? -1 : 0;
            if (differ < 0) {
                differ = (differ + 10) % 10;
            }
            p.next = new ListNode(differ);
            p = p.next;
            head1 = head1.next;
            head2 = head2.next;
        }


        while (head1 != null) {

            int differ = head1.val + pre;
            pre = differ < 0 ? -1 : 0;
            if (differ < 0) {
                differ = (differ + 10) % 10;
            }
            p.next = new ListNode(differ);
            p = p.next;
            head1 = head1.next;
        }
        while (head2 != null) {

            int differ = head2.val + pre;
            pre = differ < 0 ? -1 : 0;
            if (differ < 0) {
                differ = (differ + 10) % 10;
            }
            p.next = new ListNode(differ);
            p = p.next;
            head2 = head2.next;
        }

        // 反转链表
        return reverse(ans.next);

    }


    public static ListNode reverse(ListNode head) { // 实现反转链表
        ListNode pre = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head; // 这里容易写错！
            head = next;
        }
        return pre;
    }

    public static ArrayList<Integer> listToArrayList(ListNode head) { // 将链表转化为数组

        ArrayList<Integer> list = new ArrayList<Integer>();

        if (head == null) {
            return list;
        }

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        return list;
    }

    public static ListNode addListNode(int[] nums) { // 根据数组来建立链表
        ListNode head = new ListNode(-1);
        ListNode temp = null;

        temp = head;

        for (int i = 0; i < nums.length; i++) {

            temp.next = new ListNode(nums[i]);
            temp = temp.next;
/*            System.out.print("nums[" + i + "]: "+nums[i] + " temp.val : ");
            System.out.println(temp.val);*/

        }
        return head.next; // 返回该链表的头节点
    }
}


class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
}


