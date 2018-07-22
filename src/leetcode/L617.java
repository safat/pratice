package leetcode;

public class L617 {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(3);
        t1.left.left = new TreeNode(5);
        t1.right = new TreeNode(2);

        TreeNode t2 = new TreeNode(2);
        t2.right = new TreeNode(3);
        t2.right.right = new TreeNode(7);
        t2.left = new TreeNode(1);
        t2.left.right = new TreeNode(4);

        TreeNode m = mergeTrees(t1, t2);

        printTree(t1);
        System.out.println("-----");
        printTree(t2);
        System.out.println("-----");
        printTree(m);
    }

    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode root = null;

        if (t1 != null || t2 != null) {
            root = new TreeNode(0);

            mergeTreeUtil(root, t1, t2);
        }

        return root;
    }

    public static void mergeTreeUtil(TreeNode m, TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return;
        }

        if (t1 != null) {
            m.val += t1.val;
        }

        if (t2 != null) {
            m.val += t2.val;
        }


        if ((t1 != null && t1.left != null) || (t2 != null && t2.left != null)) {
            m.left = new TreeNode(0);

            mergeTreeUtil(m.left, t1 == null ? null : t1.left, t2 == null ? null : t2.left);
        }

        if ((t1 != null && t1.right != null) || (t2 != null && t2.right != null)) {
            m.right = new TreeNode(0);

            mergeTreeUtil(m.right, t1 == null ? null : t1.right, t2 == null ? null : t2.right);
        }
    }

    public static class TreeNode {
        int val;

        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static void printTree(TreeNode node) {
        if (node == null) {
            return;
        }

        printTree(node.left);
        System.out.println(node.val);
        printTree(node.right);
    }
}
