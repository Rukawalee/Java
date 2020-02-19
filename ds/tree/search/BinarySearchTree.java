package tree.search;

import tree.Node;
import tree.binary.BinaryTree;
import tree.binary.BinaryTreeRecursion;

public class BinarySearchTree {
    private Node root;

    public void add(int val){
        if(root == null){
            root = new Node(val);
        } else {
            Node temp = root;
            while(temp != null){
                if(temp.val >= val) {
                    if(temp.left == null){
                        temp.left = new Node(val);
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else {
                    if(temp.right == null){
                        temp.right = new Node(val);
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
        }
    }

    public Node getRoot() {
        return root;
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        BinaryTreeRecursion binaryTreeRecursion = new BinaryTreeRecursion();
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.add(4);
        binarySearchTree.add(1);
        binarySearchTree.add(3);
        binarySearchTree.add(2);
        binarySearchTree.add(6);
        binarySearchTree.add(5);
        Node root = binarySearchTree.getRoot();
        System.out.println("前序遍历");
        binaryTree.preOrder(root);
        System.out.println("\r\n-------");
        binaryTreeRecursion.preOrder(root);
        System.out.println("\r\n中序遍历");
        binaryTree.inOrder(root);
        System.out.println("\r\n-------");
        binaryTreeRecursion.inOrder(root);
        System.out.println("\r\n后序遍历");
        binaryTree.postOrder(root);
        System.out.println("\r\n-------");
        binaryTreeRecursion.postOrder(root);
        System.out.println("\r\n层次序遍历");
        binaryTree.levelOrder(root);
    }
}