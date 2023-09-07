package org.cenran;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-03  14:51
 * @Description: TODO
 * @Version: 1.0
 */
public class AvlTree {

    /* 内部类 Node */
    public class Node {
        /* 元素值 */
        private int key;
        /* 树的高度 */
        private int height;
        /* 左子节点 */
        private Node left;
        /* 右子节点 */
        private Node right;
        /* 父节点 */
        private Node parent;

        /***
         * @description 构造器
         * @author 岑然
         * @return
         * @param key
         * @param left
         * @param right
         * @param parent
         * @date 2023/9/3 14:57
         */
        public Node(int key, Node left, Node right, Node parent) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    /* 根节点 */
    private Node root;

    /***
     * @description AVL 树的空参构造器
     * @author 岑然
     * @return
     *
     * @date 2023/9/3 15:01
     */
    public AvlTree() {
    }

    /***
     * @description 将 key 插入到 AVL 中
     * @author 岑然
     * @return void
     * @param key
     * @date 2023/9/3 15:06
     */
    public void insert(int key) {
        root = insert(root, key);
        root.parent = null;
    }

    /***
     * @description 将 key 插入到 AVL 中，并返回 root
     * @author 岑然
     * @return org.cenran.AvlTree.Node
     * @param root
     * @param key
     * @date 2023/9/3 15:07
     */
    private Node insert(Node root, int key) {
        // 1. 如果是空树，则直接插入
        if (root == null) {
            root = new Node(key, null, null, null);
        }
        // 2. 如果 key < root.key ，则插入左子树中
        else if (key < root.key) {
            // 2.0 插入
            root.left = insert(root.left, key);
            root.left.parent = root;
            // 2.1 失衡，需要旋转
            if (height(root.left) - height(root.right) == 2) {
                // 2.2 LL 旋转
                if (key < root.left.key) {
                    root = leftLeftRotate(root);
                }
                // 2.3 LR 旋转
                else {
                    root = leftRightRotate(root);
                }
            }
        }
        // 3. 如果 key > root.key ，则插入右子树中
        else if (key > root.key) {
            // 3.0 插入
            root.right = insert(root.right, key);
            root.right.parent = root;
            // 3.1 失衡，需要旋转
            if (height(root.right) - height(root.left) == 2) {
                // 3.2 RR 旋转
                if (key > root.right.key) {
                    root = rightRightRotate(root);
                }
                // 3.3 RL 旋转
                else {
                    root = rightLeftRotate(root);
                }
            }
        }
        // 4. 插入完成，调整 height 值
        root.height = max(height(root.left), height(root.right)) + 1;
        // 5. 返回根节点
        return root;
    }

    /***
     * @description RL 旋转（右左旋转）
     * @author 岑然
     * @return org.cenran.AvlTree.Node
     * @param root
     * @date 2023/9/3 22:23
     */
    private Node rightLeftRotate(Node root) {
        // 1. 右子树 LL
        root.right = leftLeftRotate(root.right);
        // 2. root RR
        return rightRightRotate(root);
    }

    /***
     * @description RR 旋转（左旋转）
     * @author 岑然
     * @return org.cenran.AvlTree.Node
     * @param root
     * @date 2023/9/3 21:45
     */
    private Node rightRightRotate(Node root) {
        // 1. 确定新的根节点 -- 失衡 AVL 树根节点的右子节点
        Node rightChild = root.right;
        // 2. rightChild 节点的左子节点,变成失衡 AVL 树根节点的右子节点
        root.right = rightChild.left;
        if (root.right != null) {
            root.right.parent = root;
        }
        // 3. 失衡 AVL 树的根节点，变成 rightChild 节点的左子节点
        rightChild.left = root;
        rightChild.left.parent = rightChild;
        // 4. 调整 rightChild 的高度
        rightChild.height = max(height(rightChild.left), height(rightChild.right)) + 1;
        // 5. 调整 root 的高度
        root.height = max(height(root.left), height(root.right)) + 1;
        // 6. 返回新的根节点
        return rightChild;
    }

    /***
     * @description LR 旋转（左右旋转）
     * @author 岑然
     * @return org.cenran.AvlTree.Node
     * @param root
     * @date 2023/9/3 21:42
     */
    private Node leftRightRotate(Node root) {
        // 1. 左子树 RR
        root.left = rightRightRotate(root.left);
        // 2. root LL
        return leftLeftRotate(root);
    }

    /***
     * @description LL 旋转（右旋转）
     * @author 岑然
     * @return org.cenran.AvlTree.Node 返回旋转后的根节点
     * @param root 失衡 AVL 树的根节点
     * @date 2023/9/3 15:35
     */
    private Node leftLeftRotate(Node root) {
        // 1. 确定新的根节点 -- 失衡 AVL 树根节点的左子节点
        Node leftChild = root.left;
        // 2. 失衡 AVL 树根节点的左子节点的右子节点，变成原失衡 AVL 树根节点的左子节点
        root.left = leftChild.right;
        if (root.left != null) {
            root.left.parent = root;
        }
        // 3. 新根节点的右子节点指向原失衡 AVL 树根节点
        leftChild.right = root;
        leftChild.right.parent = leftChild;
        // 4. 调整 leftChild 的高度
        leftChild.height = max(height(leftChild.left), height(leftChild.right)) + 1;
        // 5. 调整 root 的高度
        root.height = max(height(root.left), height(root.right)) + 1;
        // 6. 返回新的根节点
        return leftChild;
    }

    /***
     * @description 对比两棵树的高度，并返回大的高度
     * @author 岑然
     * @return int
     * @param a 子树 a 的高度
     * @param b 子树 b 的高度
     * @date 2023/9/3 15:32
     */
    private int max(int a, int b) {
        return a > b ? a : b;
    }

    /***
     * @description 获取树的高度
     * @author 岑然
     * @return int
     * @param node
     * @date 2023/9/3 15:25
     */
    private int height(Node node) {
        if (node != null) {
            return node.height;
        }
        return 0;
    }

    /***
     * @description 查找 key
     * @author 岑然
     * @return int
     * @param key
     * @date 2023/9/3 22:28
     */
    public int search(int key) {
        Node node = search(root, key);
        if (node != null) {
            return node.key;
        }
        return -1;
    }

    /***
     * @description 查找 AVL 树中值为 key 的节点
     * @author 岑然
     * @return org.cenran.AvlTree.Node
     * @param root
     * @param key
     * @date 2023/9/3 22:29
     */
    private Node search(Node root, int key) {
        // 1. 根节点为 null ，则直接返回 null
        if (root == null) {
            return null;
        }
        // 2. 根节点不为 null
        // 2.1 key < root.key ，则查找 AVL 树的左子树
        if (key < root.key) {
            return search(root.left, key);
        }
        // 2.2 key > root.key ，则查找 AVL 树的右子树
        else if (key > root.key) {
            return search(root.right, key);
        }
        // 2.3 key == root.key ，则直接返回 root 节点
        else {
            return root;
        }
    }

    /***
     * @description 从 AVL 树删除指定值为 key 的节点
     * @author 岑然
     * @return boolean
     * @param key
     * @date 2023/9/3 23:37
     */
    public boolean remove(int key) {
        Node deleteNode = search(root, key);
        if (deleteNode == null) {
            return false;
        }
        remove(root, deleteNode);
        return true;
    }

    /***
     * @description 在 AVL 树中删除传入的指定节点 deleteNode
     * @author 岑然
     * @return org.cenran.AvlTree.Node
     * @param root
     * @param deleteNode
     * @date 2023/9/3 23:40
     */
    private Node remove(Node root, Node deleteNode) {
        // 1. 待删除节点或者根节点为 null ，则直接返回 null
        if (deleteNode == null || root == null) {
            return null;
        }
        // 2. 待删除节点值 < 根节点值：在左子树中删除，并平衡
        int compare = deleteNode.key - root.key;
        if (compare < 0) {
            // 1. 在左子树中删除待删除节点
            root.left = remove(root.left, deleteNode);
            // 2. 将删除指定节点后返回的根节点的父节点，指向 root （避免删除的节点是左子树的根节点，从而导致了左子树跟 root 失去联系）
            root.left.parent = root;
            // 3. 如果失去平衡，则自旋
            if (height(root.right) - height(root.left) == 2) {
                // ps：此时能确定导致失衡的是 root 的右子树，现在的任务就是要确定到底是右子树的左子树，还是右子树的右子树
                // 3.1 判断右子树的两颗子树的高度
                Node rigthChild = root.right;
                if (height(rigthChild.left) > height(rigthChild.right)) {
                    // RL 旋转
                    root = rightLeftRotate(root);
                } else {
                    // RR 旋转
                    root = rightRightRotate(root);
                }
            }
        }
        // 3. 待删除节点值 > 根节点值：在右子树中删除，并平衡
        else if (compare > 0) {
            // 1. 在右子树中删除待删除节点
            root.right = remove(root.right, deleteNode);
            // 2. 将删除节点后返回来的根节点的父节点，指向 root （避免删除的节点是右子树的根节点，从而导致右子树跟 root 失去联系）
            root.right.parent = root;
            // 3. 如果失去了平衡，则开始自旋
            if (height(root.left) - height(root.right) == 2) {
                // 拿到 root 的左子树，通过其判断到底是需要 LL，还是 LR
                Node leftChild = root.left;
                if (height(leftChild.left) > height(leftChild.right)) {
                    // 说明是 LL
                    root = leftLeftRotate(root);
                } else {
                    // 说明是 LR
                    root = leftRightRotate(root);
                }
            }
        }
        // 4. 待删除节点值 == 根节点值：删除根节点，并重新平衡 AVL 树
        else {
            // 判断 root 的左右子结点的情况
            // 4.1 如果左右子树都存在
            if (root.left != null && root.right != null) {
                // 4.1.1 如果左子树高于右子树，则选择左子树中最大的节点去覆盖 root ，并删除这个最大的节点
                if (height(root.left) > height(root.right)) {
                    Node max = findMax(root.left);
                    root.key = max.key;
                    root.left = remove(root.left, max);
                    root.left.parent = root;
                }
                // 4.1.2 如果右子树高，则从右子树中选出最小的节点去覆盖 root ，并删除这个最小的节点
                else {
                    Node min = findMin(root.right);
                    root.key = min.key;
                    root.right = remove(root.right, min);
                    root.right.parent = root;
                }
            }
            // 4.2 如果不是左右子树都存在
            else {
                // 4.2.1 让存在的那个子结点去覆盖 root
                Node temp = root;
                root = root.left != null ? root.left : root.right;
                if (root != null) {
                    // 4.2.2 将当前 root 父节点引用，指向原 root 的父节点
                    root.parent = root.parent.parent;
                }
                temp = null;
            }
        }
        // 5. 返回处理完毕后的根节点
        return root;
    }

    /***
     * @description 给定 root ，找到其中最小的节点并返回
     * @author 岑然
     * @return org.cenran.AvlTree.Node
     * @param node
     * @date 2023/9/4 14:37
     */
    private Node findMin(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /***
     * @description 给定 root ，找到其中最大的节点并返回
     * @author 岑然
     * @return org.cenran.AvlTree.Node
     * @param node
     * @date 2023/9/4 14:35
     */
    private Node findMax(Node node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /***
     * @description 先序遍历 AVL 树
     * @author 岑然
     * @return void
     *
     * @date 2023/9/4 14:40
     */
    public void preOrder() {
        preOrder(this.root);
    }

    /***
     * @description 先序遍历 AVL 树
     * @author 岑然
     * @return void
     * @param root
     * @date 2023/9/4 14:40
     */
    private void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /***
     * @description 中序遍历 AVL 树
     * @author 岑然
     * @return void
     *
     * @date 2023/9/4 14:42
     */
    public void inOrder() {
        inOrder(root);
    }

    /***
     * @description 中序遍历 AVL 树
     * @author 岑然
     * @return void
     * @param root
     * @date 2023/9/4 14:42
     */
    private void inOrder(Node root) {
        while (root != null) {
            inOrder(root.left);
            System.out.print(root.key + " ");
            inOrder(root.right);
        }
    }

    /***
     * @description 后序遍历 AVL 树
     * @author 岑然
     * @return void
     *
     * @date 2023/9/4 14:44
     */
    public void postOrder() {
        postOrder(root);
    }

    /***
     * @description 后序遍历 AVL 树
     * @author 岑然
     * @return void
     * @param root
     * @date 2023/9/4 14:44
     */
    private void postOrder(Node root) {
        while (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.key + " ");
        }
    }
}
