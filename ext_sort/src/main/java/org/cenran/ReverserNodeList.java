package org.cenran;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-07  16:33
 * @Description: TODO
 * @Version: 1.0
 */
public class ReverserNodeList {


    public static void main(String[] args) {
        ListNode ln1 = new ListNode(1);
        ListNode ln2 = new ListNode(2);
        ListNode ln3 = new ListNode(3);
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = null;
        ReverserNodeList s = new ReverserNodeList();
        ListNode head = s.reverseList(ln1);
        ListNode temp = head;//不再对head使用可以直接用head来输出 使用建议先获取出以防找不到头节点避免了重新获取
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }
//运行结果 3 2 1


    public static ListNode reverseList(ListNode head) {
        // 1. 双指针一前一后指向节点
        ListNode cur = head;
        ListNode pre = null;
        // 2. cur 指针往前走， pre 指针随后到。每次将链表的 next 翻转
        while (cur != null) {
            // 2.1 缓存 cur 的下一个节点内容，待会 cur 往前走的时候需要
            ListNode temp = cur.next;
            // 2.2 当前节点调转枪头指向前一个节点（之前是指向下一个节点）
            cur.next = pre;
            // 2.3 cur 节点往前走，pre 节点紧随其后
            pre = cur;
            cur = temp; // 注意：这两步不能颠倒，因为 cur = temp 之后，cur 虽然指向了下一个节点，但是此时 pre 已经找不到 cur 原先指向的节点了，所以需要先把 cur 里面的内容给到 pre 后，再给 cur 赋值为 temp
        }
        // 3. 返回 pre 指针，就是翻转后的链表
        return pre;
    }

    /* 单向链表节点类 */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
