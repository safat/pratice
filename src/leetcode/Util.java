package leetcode;

public class Util {

    public static void printTree(TreeNode node) {
        if (node == null) {
            return;
        }

        printTree(node.left);
        System.out.println(node.val);
        printTree(node.right);
    }
}
