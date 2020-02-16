package codeforces;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-02-02
 */

public class D339 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int m = fs.nextInt();

        List<Pair> queries = new ArrayList<>();
        Deque<Node> dataNodeStack = new LinkedList<>();

        for (int i = 1; i <= Math.pow(2, n); i++) {
            dataNodeStack.add(new Node(fs.nextInt(), NodeType.DATA));
        }

        for (int i = 0; i < m; i++) {
            queries.add(new Pair(fs.nextInt(), fs.nextInt()));
        }

        Node root = buildTree(n, dataNodeStack);

        StringBuilder output = new StringBuilder();

        for (Pair query : queries) {
            int position = query.p;
            int value = query.b;

            insert(root, position, value, (int) Math.pow(2, n));

            output.append(root.value).append("\n");
        }

        System.out.print(output);
    }

    private static void insert(Node node, int position, int value, int n) {
        if (n == 1 || n == 2) {

            if (position == 1) {
                node.left.value = value;
            } else {
                node.right.value = value;
            }

            node.value = calculate(node.left.value, node.right.value, node.nodeType);
        } else {

            if (position <= n / 2) {
                insert(node.left, position, value, n / 2);
            } else {
                insert(node.right, position - (n / 2), value, n / 2);
            }

            node.value = calculate(node.left.value, node.right.value, node.nodeType);
        }
    }

    private static class Pair {
        int p;
        int b;

        Pair(int p, int b) {
            this.p = p;
            this.b = b;
        }
    }

    private static Node buildTree(int n, Deque<Node> dataNodeStack) {
        NodeType currentNodeType = NodeType.OR;

        for (int i = 0; i < n; i++) {

            int operations = dataNodeStack.size() / 2;

            for (int j = 0; j < operations; j++) {
                Node first = dataNodeStack.removeLast();
                Node second = dataNodeStack.removeLast();

                long result = calculate(first.value, second.value, currentNodeType);

                Node resultantNode = new Node(result, currentNodeType);
                resultantNode.right = first;
                resultantNode.left = second;

                dataNodeStack.addFirst(resultantNode);
            }

            currentNodeType = currentNodeType == NodeType.OR ? NodeType.XOR : NodeType.OR;
        }

        return dataNodeStack.poll();
    }

    private static long calculate(long value1, long value2, NodeType currentNodeType) {
        switch (currentNodeType) {
            case OR:
                return value1 | value2;
            case XOR:
                return value1 ^ value2;
        }

        throw new IllegalStateException("Unknown Operator Node " + currentNodeType);
    }

    private static enum NodeType {
        OR,
        XOR,
        DATA
    }

    private static class Node {
        long value;
        NodeType nodeType;
        Node left;
        Node right;

        public Node() {
        }

        public Node(long value, NodeType nodeType) {
            this.value = value;
            this.nodeType = nodeType;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", nodeType=" + nodeType +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            try {
                br = new BufferedReader(new InputStreamReader(System.in));
//				br = new BufferedReader(new FileReader("cases.in"));
                st = new StringTokenizer("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String next() {
            if (st.hasMoreTokens()) return st.nextToken();
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public String nextLine() {
            String line = "";
            try {
                line = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return line;
        }

        public Integer[] nextIntegerArray(int n) {
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        public char[] nextCharArray() {
            return nextLine().toCharArray();
        }
    }
}
