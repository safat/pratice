package leetcode;

public class L814 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode r1 = new TreeNode(1);
        TreeNode r1l1 = new TreeNode(0);
        TreeNode r1r1 = new TreeNode(1);

        TreeNode l1 = new TreeNode(0);
        TreeNode l1l1 = new TreeNode(1);
        TreeNode l1r1 = new TreeNode(0);


        root.right = r1;
        root.right.right = r1r1;
        root.right.left = r1l1;

        root.left = l1;
        root.left.left = l1l1;
        root.left.right = l1r1;

        new L814().prune(root);

        printTree(root);
    }

    private static void printTree(TreeNode root) {
        if (root == null) {
            return;
        }

        printTree(root.left);
        System.out.println(root.val);
        printTree(root.right);
    }

    public TreeNode prune(TreeNode treeNode) {
        int totalSum = pruneHelper(treeNode);

        if (totalSum == 0) {
            return null;
        }

        return treeNode;
    }

    public int pruneHelper(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }

        int leftSum = pruneHelper(treeNode.left);
        int rightSum = pruneHelper(treeNode.right);

        if (leftSum == 0) {
            treeNode.left = null;
        }

        if (rightSum == 0) {
            treeNode.right = null;
        }

        return leftSum + rightSum + treeNode.val;
    }
}
