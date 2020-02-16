package spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author muhossain
 * @since 2020-02-04
 */

public class NiceBTree {
    private static int idx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<String> inputs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            inputs.add(br.readLine());
        }

        StringBuilder output = new StringBuilder();

        for (String input : inputs) {
            idx = 0;

            Node root = buildTree(input);
            int depth = findDepth(root);

            output.append(depth).append("\n");
        }

        System.out.print(output);
    }

    private static int findDepth(Node root) {
        return findDepthUtil(root, 0);
    }

    private static int findDepthUtil(Node root, int i) {
        if (root.value == 'l') {
            return i;
        }

        return Math.max(findDepthUtil(root.left, i + 1), findDepthUtil(root.right, i + 1));
    }

    private static Node buildTree(String input) {

        if (idx == input.length()) {
            return null;
        }

        if (input.charAt(idx) == 'l') {
            return new Node(input.charAt(idx));
        }

        Node node = new Node('n');

        idx++;
        node.left = buildTree(input);

        idx++;
        node.right = buildTree(input);

        return node;
    }

    private static class Node {
        char value;
        Node left;
        Node right;

        public Node(char value) {
            this.value = value;
        }
    }
}
