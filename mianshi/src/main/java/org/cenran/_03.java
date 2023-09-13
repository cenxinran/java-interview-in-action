package org.cenran;

import jdk.javadoc.internal.doclets.formats.html.markup.Head;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-11  20:35
 * @Description: TODO
 * @Version: 1.0
 */
public class _03 {
    public static void main(String[] args) {

        ArrayList<Integer> arr1 = new ArrayList<>();
        arr1.add(5);
        arr1.add(4);
        arr1.add(3);
        arr1.add(2);
        arr1.add(1);

        // 将数组转换成链表
        // 翻转链表


    }

    public static ListNode reverseList(ListNode node) {
        // 1. 定义两个指针，prev 指向前一个节点，cur 指向当前待修改 next 属性的节点
        ListNode prev = null;
        ListNode cur = node;
        // 2. 遍历链表，直到 cur 为空
        while (cur != null) {
            // 3. 将当前节点的 next 属性掉头指向 prev
            ListNode temp = cur.next; // 调转之前需要先保存 cur.next
            cur.next = prev;
            // 4. prev 和 curr 往前移动
            prev = cur;
            cur = temp;
        }
        // 5. 返回 prev
        return prev;
    }

    /***
     * @description 将数组转换成链表
     * @author 岑然
     * @return org.cenran._03.ListNode
     * @param list
     * @date 2023/9/11 20:39
     */
    public static ListNode arr2List(List list) {
        return null;
    }

    /* 内部 ListNode 类 */
    public static class ListNode {
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
    }

}
