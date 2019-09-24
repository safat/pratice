package basic.tree;


import basic.TreeNode;

import java.util.Stack;

/**
 * @author muhossain
 * @since 2019-06-30
 */

public class TwoNodeSumBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode l1 = new TreeNode(4);
        TreeNode l1r1 = new TreeNode(5);
        TreeNode l1l1 = new TreeNode(3);
        TreeNode l1l1r1 = new TreeNode(2);
        TreeNode r1 = new TreeNode(8);
        TreeNode r1l1 = new TreeNode(7);

        root.left = l1;
        l1.left = l1l1;
        l1l1.right = l1l1r1;
        l1.right = l1r1;

        root.right = r1;
        r1.left = r1l1;

        System.out.println(solve(root, 4));

    }

    private static boolean solve(TreeNode root, int sum) {
        TreeNode lp = root;
        TreeNode rp = root;

        Stack<TreeNode> ls = new Stack<>();
        Stack<TreeNode> rs = new Stack<>();

        while (lp != null) {
            ls.push(lp);
            lp = lp.left;
        }

        while (rp != null) {
            rs.push(rp);
            rp = rp.right;
        }

        while (!ls.isEmpty() && !rs.isEmpty()) {

            lp = ls.peek();
            rp = rs.peek();

            int leftVal = lp.val;
            int rightVal = rp.val;

            System.out.println("comparing : " + leftVal + " with " + rightVal);

            if (leftVal + rightVal == sum) {
                return true;
            }

            if (lp.val == rp.val) {
                break;
            }

            if (leftVal + rightVal < sum) {
                ls.pop();

                if (lp.right != null) {
                    lp = lp.right;
                    ls.push(lp);
                }

                while (lp.left != null) {
                    lp = lp.left;
                    ls.push(lp);
                }
            } else {
                rs.pop();

                if (rp.left != null) {
                    rp = rp.left;
                    rs.push(rp);
                }

                while (rp.right != null) {
                    rp = rp.right;
                    rs.push(rp);
                }
            }
        }

        return false;
    }
}

/*

           6
       /      \
      4        8
     / \      /
    3   5    7
     \
      2

 */