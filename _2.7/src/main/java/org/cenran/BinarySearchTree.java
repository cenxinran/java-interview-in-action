package org.cenran;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-03  02:03
 * @Description: 二叉排序树实现
 * @Version: 1.0
 */
public class BinarySearchTree {

    public class Node {

        /* 节点数据 */
        public int data;
        /* 当前节点左孩子节点的引用 */
        public Node left;
        /* 当前节点右孩子节点的引用 */
        public Node right;
        /* 当前节点的父节点的引用 */
        public Node parent;

        /***
         * @description 构造器
         * @author 岑然
         * @param value 传入的节点数据
         * @date 2023/9/3 2:13
         */
        public Node(int value) {
            data = value;
            left = null;
            right = null;
            parent = null;
        }

    }

    /* 二叉排序树的成员变量 - 根节点 root */
    public Node root;

    /***
     * @description 二叉排序树的构造器（空参）
     * @author 岑然
     * @date 2023/9/3 2:16
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * @param key
     * @return org.cenran.BinarySearchTree.Node
     * @description 查找指定的元素
     * @author 岑然
     * @date 2023/9/3 11:23
     */
    public Node find(int key) {
        // 从根节点开始遍历
        Node current = root;
        while (current != null) {
            // key 小于当前节点，往左遍历
            if (key < current.data) {
                // 不存在左子树，直接返回 current
                if (current.left == null) {
                    return current;
                }
                // 存在左子树，则遍历左子树
                current = current.left;
            }
            // key 大于当前节点，往右遍历
            else if (key > current.data) {
                // 不存在右子树，返回当前节点
                if (current.right == null) {
                    return current;
                }
                // 存在右子树，则遍历之
                current = current.right;
            }
            // key 等于当前节点，直接返回当前节点
            else {
                return current;
            }
        }
        // 遍历完毕没找到，返回 null
        return null;
    }

    /**
     * @param value
     * @return void
     * @description 添加元素
     * @author 岑然
     * @date 2023/9/3 11:34
     */
    public void put(int value) {
        // 包装成 Node 对象
        Node newNode = new Node(value);
        // 如果是空树，则直接赋值给根节点
        if (root == null) {
            root = newNode;
            return;
        }
        // 找到其父节点
        Node parent = find(value);
        // 如果小于父节点值，则往左插入
        if (value < parent.data) {
            parent.left = newNode;
            parent.left.parent = parent;
            return;
        }
        // 如果大于父节点值，则往右插入
        parent.right = newNode;
        parent.right.parent = parent;
    }

    /**
     * @param value 指定元素值
     * @return boolean
     * @description 删除指定元素
     * <p>
     * 1. 要删除的节点没有子树，则直接删除节点
     * 2. 要删除的节点存在左或者右子树，删除节点后需要移动其子树
     * 3. 要删除的节点同时存在左右子树，跟后继结点交换后克转换成 1、2 的情况
     * @author 岑然
     * @date 2023/9/3 11:42
     */
    public boolean remove(int value) {
        // 1. 先判断能否找到指定元素
        Node temp = find(value);
        if (temp.data != value) {
            return false;
        }
        // 2. 找到指定元素后，首先处理第 3 种情况,待删除节点同时存在左右子树
        if (temp.left != null && temp.right != null) {
            // 找到后继结点
            Node successor = findSuccessor(temp);
            // 将后继结点值赋给当前节点
            temp.data = successor.data;
            // 待删除节点指向后继结点
            temp = successor;
        }
        // 经过上述处理后，第 3 种情况已经转换成第 1 或者 2 种情况了

        // 3. 开始处理第 1、2 种情况
        // 3.1 获取待删除节点的子节点 (此时因为经过了上头对情况 3 的处理，所以能到这里的节点，只能有一个子节点--情况2，或者根本就没有子节点--情况1）
        Node child;
        child = temp.left != null ? temp.left : temp.right;
        // 3.2 填充子节点的 parent
        if (child != null) {
            // 将待删除节点的子节点连上待删除节点的父节点
            child.parent = temp.parent;
        }
        // 3.3 填充父节点的 child
        // 如果待删除的节点没有父节点（如果是第 3 种情况，则必然有父节点，因为后继结点就是通过右子树的最小值找来的，此处依然有这个判断的原因是给第 1、2 种情况使用）
        if (temp.parent == null) {
            // 说明待删除节点是 root ，那就直接把 child 接到 root 下
            root = child;
        } else if (temp == temp.parent.left) {
            // 如果待删除节点是其父节点的左子树
            temp.parent.left = child;
        } else if (temp == temp.parent.right) {
            // 如果待删除节点是其父节点的右子树
            temp.parent.right = child;
        }

        // 4. 删除完毕，返回结果
        return true;
    }

    /**
     * @param node
     * @return org.cenran.BinarySearchTree.Node
     * @description 查询节点的后继结点
     * <p>
     * 1. 如果当前节点没有右子树，直接返回当前节点
     * 2. 如果当前节点有右子树，则遍历右子树，找到最小的大于当前节点值的节点
     * @author 岑然
     * @date 2023/9/3 13:18
     */
    private Node findSuccessor(Node node) {
        // 1. 当前节点没有右子树
        if (node.right == null) {
            return node.parent;
        }
        // 2. 当前节点存在右子树
        Node current = node.right;
        Node parent = node.right;
        while (current != null) {
            // 一直往左子树遍历
            parent = current;
            current = current.left;
        }
        // 3. 遍历完成，返回 parent
        return parent;
    }

    /**
     * @return org.cenran.BinarySearchTree.Node
     * @description 获取根节点
     * @author 岑然
     * @date 2023/9/3 13:24
     */
    public Node getRoot() {
        return root;
    }

    /***
     * @description 中序遍历
     *
     * 左子树 ---> 父节点 ---> 右子树
     *
     * @author 岑然
     * @return void
     * @param node
     * @date 2023/9/3 13:26
     */
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    /***
     * @description 先序遍历
     *
     * 父节点 ---> 左子树 ---> 右子树
     *
     * @author 岑然
     * @return void
     * @param node
     * @date 2023/9/3 13:30
     */
    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /***
     * @description 后序遍历
     *
     * 左子树 ---> 右子树 ---> 父节点
     *
     * @author 岑然
     * @return void
     * @param node
     * @date 2023/9/3 13:32
     */
    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

}
