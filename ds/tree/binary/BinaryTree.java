package tree.binary;

import tree.Node;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 非递归实现
 */
public class BinaryTree {
    /**
     * 先序遍历
     *
     * @param root
     */
    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> nodes = new Stack<>();
        nodes.push(root);
        Node temp = null;
        while (!nodes.isEmpty()) {
            temp = nodes.pop();
            System.out.print(temp.val + " ");
            if (temp.right != null) {
                nodes.push(temp.right);
            }
            if (temp.left != null) {
                nodes.push(temp.left);
            }
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
        Stack<Node> nodes = new Stack<>();
        Node temp = null;
        while (root != null || !nodes.isEmpty()) {
            while (root != null) {
                nodes.push(root);
                root = root.left;
            }
            temp = nodes.pop();
            System.out.print(temp.val + " ");
            if (temp.right != null) {
                root = temp.right;
            }
        }
    }

    /**
     * 后续遍历
     *
     * @param root
     */
    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> nodes = new Stack<>();
        Node temp = null;
        Node pre = null;    // 记录上次访问节点
        while (root != null || !nodes.isEmpty()) {
            while (root != null) {
                nodes.push(root);
                root = root.left;
            }
            temp = nodes.peek();
            if (temp.right == null || temp.right == pre) {
                temp = nodes.pop();
                System.out.print(temp.val + " ");
                pre = temp;
            } else {
                root = temp.right;
            }
        }
    }

    /**
     * 层次序遍历
     *
     * @param root
     */
    public void levelOrder(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.offer(root);
        Node temp = null;
        while (!nodes.isEmpty()) {
            temp = nodes.poll();
            System.out.print(temp.val + " ");
            if (temp.left != null) {
                nodes.offer(temp.left);
            }
            if (temp.right != null) {
                nodes.offer(temp.right);
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        BinaryTree bt = new BinaryTree();
        System.out.println("前序遍历");
        bt.preOrder(root);
        System.out.println("\r\n中序遍历");
        bt.inOrder(root);
        System.out.println("\r\n后序遍历");
        bt.postOrder(root);
        System.out.println("\r\n层次序遍历");
        bt.levelOrder(root);
    }
}
