package leetcode;

import java.util.ArrayList;
import java.util.List;

public class L894 {

    private static int c = 0;

    public static void main(String[] args) {
        List<TreeNode> treeNodes = allPossibleFBT(7);

        treeNodes.forEach(base -> {
            System.out.println("-------------");
            Util.printTree(base);
        });
    }

    public static List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        if (N == 1) {
            res.add(new TreeNode(++c));
            return res;
        }

        N = N - 1;

        for (int i = 1; i < N; i += 2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N - i);

            System.out.println("left size: " + left.size());
            System.out.println("right size: " + right.size());

            for (TreeNode nl : left) {
                for (TreeNode nr : right) {
                    TreeNode cur = new TreeNode(c++);
                    cur.left = nl;
                    cur.right = nr;
                    res.add(cur);
                }
            }
        }
        return res;
    }
}
