package org.cenran;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-03  13:34
 * @Description: TODO
 * @Version: 1.0
 */
public class BinarySearchTreeDemo {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.put(6);
        bst.put(3);
        bst.put(8);
        bst.put(10);
        bst.put(2);
        bst.put(9);
        bst.put(5);
        bst.put(1);
        bst.put(4);
        bst.put(7);

        System.out.println("--------中序遍历--------");
        bst.inOrder(bst.getRoot());
        System.out.println("");
        System.out.println("--------前序遍历--------");
        bst.preOrder(bst.getRoot());
        System.out.println("");
        System.out.println("--------后序遍历--------");
        bst.postOrder(bst.getRoot());
        System.out.println("");
        System.out.println("--------删除 8 后中序遍历--------");
        bst.remove(8);
        bst.inOrder(bst.getRoot());
        System.out.println("");
    }

}
