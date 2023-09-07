package org.cenran;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-06  12:18
 * @Description: TODO
 * @Version: 1.0
 */
public class LinkListDemo {
    public static void main(String[] args) {
        int size = 10;
        LinkList linkList = new LinkList();
        System.out.println("----------向单链表新增元素----------");
        for (int i = 0; i < size; i++) {
            linkList.insert(i, i);
        }
        for (int i = 0; i < linkList.size(); i++) {
            System.out.print(linkList.get(i) + " ");
        }
        System.out.println();
        System.out.println("----------del 头节点后链表是否为空----------");
        linkList.delete(0);
        System.out.println(linkList.isEmpty());
        for (int i = 0; i < linkList.size(); i++) {
            System.out.print(linkList.get(i) + " ");
        }
    }
}
