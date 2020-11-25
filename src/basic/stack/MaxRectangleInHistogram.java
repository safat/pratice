package basic.stack;

import java.util.*;

/**
 * @author muhossain
 * @since 2020-11-02
 * <p>
 * Given a histogram, find max  (Li - Ri + 1) * Min (Li -> Ri)
 */

public class MaxRectangleInHistogram {
    public static void main(String[] args) {

        int[] num = {0, 2, 1, 4, 5, 1, 3, 3, 0};

        int maxResult = solve(num);

        System.out.println(maxResult);
    }

    private static int solve(int[] num) {
        int[] left = new int[num.length];
        int[] right = new int[num.length];

        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(new Node(0, 0));

        for (int i = 1; i < num.length - 1; i++) {
            while (nodeStack.size() > 1 && nodeStack.peek().value >= num[i]) {
                nodeStack.pop();
            }

            left[i] = i - nodeStack.peek().position;

            nodeStack.push(new Node(num[i], i));
        }

        nodeStack.clear();
        nodeStack.push(new Node(0, num.length - 1));

        for (int i = num.length - 2; i > 0; i--) {
            while (nodeStack.size() > 1 && nodeStack.peek().value >= num[i]) {
                nodeStack.pop();
            }

            right[i] = nodeStack.peek().position - i;

            nodeStack.push(new Node(num[i], i));
        }

        int maxResult = 0;

        for (int i = 1; i < num.length - 1; i++) {
            maxResult = Math.max(maxResult, num[i] * (left[i] + right[i] - 1));
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
