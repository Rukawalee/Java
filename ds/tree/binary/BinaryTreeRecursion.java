package tree.binary;

import tree.Node;

/**
 * 递归实现
 */
public class BinaryTreeRecursion {
    /**
     * 先序遍历
     *
     * @param root
     */
    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        if (root.left != null) {
            this.preOrder(root.left);
        }
        if (root.right != null) {
            this.preOrder(root.right);
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void inOrder(Node root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            this.inOrder(root.left);
        }
        System.out.print(root.val + " ");
        if (root.right != null) {
            this.inOrder(root.right);
        }
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            this.postOrder(root.left);
        }
        if (root.right != null) {
            this.postOrder(root.right);
        }
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        BinaryTreeRecursion bt = new BinaryTreeRecursion();
        System.out.println("前序遍历");
        bt.preOrder(root);
        System.out.println("\r\n中序遍历");
        bt.inOrder(root);
        System.out.println("\r\n后序遍历");
        bt.postOrder(root);
    }
}