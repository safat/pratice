package leetcode;

public class L654 {

    public static void main(String[] args) {
        int data[] = {3, 2, 1, 6, 0, 5};

        TreeNode root = new L654().constructMaximumBinaryTree(data);

        printTree(root);
    }

    private int getMaxItemIndex(int[] data, int s, int e) {
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;

        for (int i = s; i <= e; i++) {
            int n = data[i];
            if (n > max) {
                maxIndex = i;
                max = n;
            }
        }

        return maxIndex;
    }

    public TreeNode helper(int[] data, int s, int e) {
        if (e < s) {
            return null;
        }

        int maxItemIndex = getMaxItemIndex(data, s, e);

        TreeNode node = new TreeNode(data[maxItemIndex]);

        node.left = helper(data, s, maxItemIndex - 1);
        node.right = helper(data, maxItemIndex + 1, e);

        return node;
    }

    public TreeNode constructMaximumBinaryTree(int[] data) {
        return helper(data, 0, data.length - 1);
    }

    private static void printTree(TreeNode root) {
        if (root == null) {
            return;
        }

        printTree(root.left);
        System.out.println(root.val);
        printTree(root.right);
    }
}