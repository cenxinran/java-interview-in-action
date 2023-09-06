package org.cenran;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-06  11:21
 * @Description: TODO
 * @Version: 1.0
 */
public class LinkList implements List {

    /* 内部类 Node */
    private class Node {
        private Object data;
        private Node next;

        public Node() {
        }

        public Node(Object element, Node next) {
            this.data = element;
            this.next = next;
        }
    }

    /* 头结点 */
    private Node header;
    /* 尾节点 */
    private Node tail;
    /* 单链表的容量 */
    private int size;

    /* 单链表空参构造 */
    public LinkList() {
        header = null;
        tail = null;
    }

    /* 单链表的有参构造 */
    public LinkList(Object element) {
        header = new Node(element, null);
        tail = header;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /***
     * @description 在指定索引位置插入元素
     * @author 岑然
     * @return void
     * @param index 指定位置
     * @param element 待插入元素
     * @date 2023/9/6 12:28
     */
    public void insert(int index, Object element) {
        // 1. 参数校验
        if (index < 0 || index > size) {
            throw new RuntimeException("指定索引范围超出单链表索引范围");
        }
        // 2. 如果是空链表
        if (header == null) {
            addAtTail(element);
            return;
        }
        // 3. 如果插入在头部
        if (index == 0) {
            addAtHead(element);
        }
        // 4. 如果不是插入头部
        else {
            Node prev = findByIndex(index - 1);
            prev.next = new Node(element, prev.next);
            size++;
        }
    }

    /***
     * @description 根据给定索引查找对应节点，并返回
     * @author 岑然
     * @return org.cenran.LinkList.Node
     * @param index 指定索引
     * @date 2023/9/6 12:28
     */
    private Node findByIndex(int index) {
        // 1. 参数校验
        if (index < 0 || index > size) {
            throw new RuntimeException("给定索引超过单链表长度");
        }
        // 2. 遍历链表
        // 2.1 从 header 开始查找
        Node current = header;
        // 2.2 找到指定索引的位置就返回
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            if (i == index) {
                return current;
            }
        }
        return null;
    }

    /***
     * @description 将指定元素插入链表头部（头插法）
     * @author 岑然
     * @return void
     * @param element 待插入头部的元素
     * @date 2023/9/6 12:29
     */
    private void addAtHead(Object element) {
        // 1. 新建节点
        Node newNode = new Node(element, null);
        // 2. 将新节点插入到头部
        newNode.next = header;
        header = newNode;
        // 3. 判断 tail 是否需要更新
        if (tail == null) {
            tail = header;
        }
        // 4. 插入成功，size 加一
        size++;
    }

    /***
     * @description 将给定元素插入链表尾部
     * @author 岑然
     * @return void
     * @param element 给定元素
     * @date 2023/9/6 12:30
     */
    private void addAtTail(Object element) {
        // 1. 如果单链表是空的，直接创建节点并赋值
        if (header == null) {
            header = new Node(element, null);
            tail = header;
        }
        // 2. 不为空陪，插入到尾部
        else {
            Node newNode = new Node(element, null);
            tail.next = newNode;
            tail = newNode;
        }
        // 3. 插入完成，size 加一
        size++;
    }

    /***
     * @description 删除指定索引位置的节点
     * @author 岑然
     * @return void
     * @param index 指定索引
     * @date 2023/9/6 12:30
     */
    public void delete(int index) {
        // 1. 参数校验
        if (index < 0 || index > size) {
            throw new RuntimeException("指定索引超过单链表索引范围");
        }
        // 2. 如果待删除节点是头结点，更新 header
        if (index == 0) {
            header = header.next;
            size--;
            return;
        }
        // 3. 如果不是头结点
        Node prev = findByIndex(index - 1);
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size--;
    }

    /***
     * @description 给定索引，返回其对应位置的值
     * @author 岑然
     * @return java.lang.Object
     * @param index
     * @date 2023/9/6 12:31
     */
    @Override
    public Object get(int index) {
        Node byIndex = findByIndex(index);
        if (byIndex != null) {
            return byIndex.data;
        }
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }


    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }


    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
