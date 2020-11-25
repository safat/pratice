package basic.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author muhossain
 * @since 2020-11-02
 * <p>
 * 0 1 1 0 1 1 0
 * 0 0 0 0 0 1 0
 * 1 0 0 0 0 0 1
 * 0 1 0 0 0 0 1
 * 1 1 0 0 0 1 0
 * <p>
 * Find the largest rectangle with zero
 */

public class MaxZeroRectangle {
    public static void main(String[] args) {

        int[][] grid = {
                {0, 1, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 0, 1, 0},
        };

        int[][] tmp = new int[grid.length][grid[0].length];

        int max = 0;


        for (int i = 0; i < grid[0].length; i++) {
            tmp[0][i] = grid[0][i] == 0 ? 1 : 0;
        }

        System.out.println("0->" + Arrays.toString(tmp[0]));

        max = Math.max(max, solve(tmp[0]));

        for (int i = 1; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {
                tmp[i][j] = grid[i][j] == 0 ? tmp[i - 1][j] + 1 : 0;
            }

            System.out.println(i + "->" + Arrays.toString(tmp[i]));

            max = Math.max(max, solve(tmp[i]));
        }        System.out.println("0->" + Arrays.toString(tmp[0]));

        System.out.println(max);
    }

    private static int solve(int[] num) {
        int[] tmpNum = new int[num.length + 2];
        System.arraycopy(num, 0, tmpNum, 0, num.length);

        int[] left = new int[tmpNum.length];
        int[] right = new int[tmpNum.length];

        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(new Node(0, 0));

        for (int i = 1; i < tmpNum.length - 1; i++) {
            while (nodeStack.size() > 1 && nodeStack.peek().value >= tmpNum[i]) {
                nodeStack.pop();
            }

            left[i] = i - nodeStack.peek().position;

            nodeStack.push(new Node(tmpNum[i], i));
        }

        nodeStack.clear();
        nodeStack.push(new Node(0, tmpNum.length - 1));

        for (int i = tmpNum.length - 2; i > 0; i--) {
            while (nodeStack.size() > 1 && nodeStack.peek().value >= tmpNum[i]) {
                nodeStack.pop();
            }

            right[i] = nodeStack.peek().position - i;

            nodeStack.push(new Node(tmpNum[i], i));
        }

        int maxResult = 0;

        for (int i = 1; i < tmpNum.length - 1; i++) {
            maxResult = Math.max(maxResult, tmpNum[i] * (left[i] + right[i] - 1));
        }
        return maxResult;
    }

    private static class Node {
        int value;
        int position;


        public Node(int value, int position) {
            this.value = value;
            this.position = position;
        }
    }
}
