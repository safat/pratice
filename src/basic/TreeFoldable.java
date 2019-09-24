package basic;

import java.util.Stack;

public class TreeFoldable {

    public static void main(String[] args) {
        Node root = new Node(10);
        Node l1 = new Node(11);
        Node l1l1 = new Node(13);
        Node l1r1 = new Node(15);
        Node l1r1l1 = new Node(20);
        Node r1 = new Node(12);
        Node r1r1 = new Node(15);
        Node r1l1 = new Node(14);
        Node r1l1r1 = new Node(22);


        root.left = l1;
        l1.left = l1l1;
        l1.right = l1r1;
        l1r1.left = l1r1l1;

        root.right = r1;
        r1.right = r1r1;
        r1.left = r1l1;
        r1l1.right = r1l1r1;

        System.out.println(isFoldable(root));
    }

    public static boolean isFoldable(Node root) {
        Stack<Node> leftStack = new Stack<>();
        Stack<Node> rightStack = new Stack<>();
        Node left;
        Node right;

        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }


        if (root.left == null || root.right == null) {
            return false;
        }

        leftStack.add(root.left);
        rightStack.add(root.right);

        while (!leftStack.isEmpty() && !rightStack.isEmpty()) {
            left = leftStack.pop();
            right = rightStack.pop();

            if (left == null && right == null) {
                continue;
            }

            if (left == null || right == null) {
                return false;
            }

            rightStack.add(right.right);
            leftStack.add(left.left);

            rightStack.add(right.left);
            leftStack.add(left.right);
        }

        return leftStack.isEmpty() && rightStack.isEmpty();
    }


    static class Node {
        Node left;
        Node right;

        int value;

        public Node(int value) {
            this.value = value;
        }
    }
}