package misc;

import java.util.LinkedList;
import java.util.Stack;

public class BST {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createNode(10);

        TreeNode l1 = TreeNode.createNode(8);
        TreeNode r1 = TreeNode.createNode(12);
        TreeNode r1l1 = TreeNode.createNode(11);
        TreeNode l1l1 = TreeNode.createNode(6);
        TreeNode l1r1 = TreeNode.createNode(9);

        root.setLeft(l1);
        root.setRight(r1);

        l1.setLeft(l1l1);
        l1.setRight(l1r1);
        r1.setLeft(r1l1);

        inOrder2(root);
    }

    // without recursion: using doubly linkedlist
    private static void inOrder(TreeNode root) {
        LinkedList<TreeNode> doublyLinkedList = new LinkedList<>();

        doublyLinkedList.add(root.getLeft());
        doublyLinkedList.add(TreeNode.createNode(root.getId()));
        doublyLinkedList.add(root.getRight());

        while (!doublyLinkedList.isEmpty()) {
            TreeNode cNode = doublyLinkedList.poll();

            if (cNode == null) {
                return;
            }

            if (cNode.getLeft() == null && cNode.getRight() == null) {
                System.out.println(cNode.getId());
            } else {
                doublyLinkedList.addFirst(cNode.getRight());
                doublyLinkedList.addFirst(TreeNode.createNode(cNode.getId()));
                doublyLinkedList.addFirst(cNode.getLeft());
            }
        }
    }

    // without recursion: using stack
    private static void inOrder2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }

            if (!stack.isEmpty()) {
                TreeNode stackTop = stack.pop();

                System.out.println(stackTop.getId());

                current = stackTop.getRight();
            }
        }
    }

    // traditional good old recursive
    private static void inOrderRecursive(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        inOrderRecursive(treeNode.getLeft());
        System.out.println(treeNode.getId());
        inOrderRecursive(treeNode.getRight());

    }
}
