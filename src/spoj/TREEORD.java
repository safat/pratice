package spoj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * @author muhossain
 * @since 2020-02-16
 */

public class TREEORD {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        //pre, post, in

        int[] preOrder = new int[n + 1];
        int[] preOrderIdx = new int[n + 1];


        int[] postOrder = new int[n + 1];
        int[] postOrderIdx = new int[n + 1];

        int[] inOrder = new int[n + 1];
        int[] inOrderIdx = new int[n + 1];

        for (int i = 0; i < n; i++) {
            preOrder[i] = fs.nextInt();
            preOrderIdx[preOrder[i]] = i;
        }

        for (int i = 0; i < n; i++) {
            postOrder[i] = fs.nextInt();
            postOrderIdx[postOrder[i]] = i;
        }

        for (int i = 0; i < n; i++) {
            inOrder[i] = fs.nextInt();
            inOrderIdx[inOrder[i]] = i;
        }

        int nodeValue = preOrder[0];

        Stack<Node> nodeStack = new Stack<>();

        Node root = new Node(nodeValue);
        root.subTreeleft = 0;
        root.subTreeRight = n - 1;

        nodeStack.add(root);

        for (int i = 1; i < n; i++) {

            int nextNodeValue = preOrder[i];

            Node currentNode = nodeStack.pop();
            int currentNodeValue = currentNode.value;

            while (inOrderIdx[nextNodeValue] > currentNode.subTreeRight ||
                    inOrderIdx[nextNodeValue] < currentNode.subTreeleft) {

                currentNode = nodeStack.pop();
                currentNodeValue = currentNode.value;
            }

            nodeStack.push(currentNode);

            Node nextNode;

            if (inOrderIdx[currentNodeValue] > inOrderIdx[nextNodeValue]) {
                nextNode = new Node(nextNodeValue);
                nextNode.subTreeRight = inOrderIdx[currentNodeValue] - 1;
                nextNode.subTreeleft = currentNode.subTreeleft;

                currentNode.left = nextNode;
                nodeStack.push(nextNode);

            } else {
                nextNode = new Node(nextNodeValue);
                nextNode.subTreeleft = inOrderIdx[currentNodeValue] + 1;
                nextNode.subTreeRight = currentNode.subTreeRight;

                currentNode.right = nextNode;
                nodeStack.push(nextNode);
            }
        }

        List<Integer> expectedPostOrderList = new ArrayList<Integer>();
        traversePostOrder(expectedPostOrderList, root);

        if (Arrays.stream(postOrder).filter(x -> x != 0).boxed().collect(Collectors.toList()).equals(expectedPostOrderList)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    private static void traversePostOrder(List<Integer> postOder, Node root) {
        if (root == null) {
            return;
        }

        traversePostOrder(postOder, root.left);
        traversePostOrder(postOder, root.right);

        postOder.add(root.value);
    }


    private static class Node {
        Node left;
        Node right;
        int value;
        int subTreeleft;
        int subTreeRight;

        public Node(int value) {
            this.value = value;
        }
    }

    static class FastScanner {
        private BufferedReader reader = null;
        private StringTokenizer tokenizer = null;

        public FastScanner(InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        public String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
