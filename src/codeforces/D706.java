package codeforces;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-03-29
 */

public class D706 {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        int q = fs.nextInt();
        StringBuilder output = new StringBuilder();

        Node root = new Node('-');

        for (int i = 0; i < q; i++) {
            String[] input = fs.nextLine().split(" ");
            String operation = input[0];
            int num = Integer.parseInt(input[1]);

            if (operation.equals("+")) {
                addToTree(root, num);
            }

            if (operation.equals("-1")) {
                removeFromTree(root, num);
            }

            if (operation.equals("?")) {
                output.append(findMaxXor(root, num)).append("\n");
            }
        }

        System.out.print(output);
    }

    public static int findMaxXor(Node root, int num) {
        Node currentRoot = root;
        int result = 0;
        String binaryString = Integer.toBinaryString(num);

        for (int i = 0; i < 31 - binaryString.length(); i++) {
            if (currentRoot.right != null && currentRoot.right.frequency > 0) {
                result <<= 1;
                result |= 1;
                currentRoot = currentRoot.right;
            } else {
                result <<= 1;
                currentRoot = currentRoot.left;
            }
        }

        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '0') {
                if (currentRoot.right != null && currentRoot.right.frequency > 0) {
                    result <<= 1;
                    result |= 1;
                    currentRoot = currentRoot.right;
                } else {
                    result <<= 1;
                    currentRoot = currentRoot.left;
                }
            } else {
                if (currentRoot.left != null && currentRoot.left.frequency > 0) {
                    result <<= 1;
                    result |= 1;
                    currentRoot = currentRoot.left;
                } else {
                    result <<= 1;
                    currentRoot = currentRoot.right;
                }
            }
        }

        return result;
    }

    private static void addToTree(Node root, int num) {
        String binaryString = Integer.toBinaryString(num);
        Node currentRoot = root;

        for (int i = 0; i < 31 - binaryString.length(); i++) {
            if (currentRoot.left == null) {
                currentRoot.left = new Node('0');
            }

            currentRoot = currentRoot.left;
            currentRoot.frequency++;
        }

        for (int i = 0; i < binaryString.length(); i++) {
            //add to right
            if (binaryString.charAt(i) == '1') {
                if (currentRoot.right == null) {
                    currentRoot.right = new Node('1');
                }

                currentRoot = currentRoot.right;

            } else if (binaryString.charAt(i) == '0') {
                if (currentRoot.left == null) {
                    currentRoot.left = new Node('0');
                }

                currentRoot = currentRoot.left;
            }

            currentRoot.frequency++;
        }
    }

    private static void removeFromTree(Node root, int num) {
        String binaryString = Integer.toBinaryString(num);
        Node currentRoot = root;

        for (int i = 0; i < 31 - binaryString.length(); i++) {
            currentRoot = currentRoot.left;
            currentRoot.frequency--;
        }

        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                currentRoot = currentRoot.right;
            } else {
                currentRoot = currentRoot.left;
            }

            currentRoot.frequency--;
        }
    }


    private static class Node {
        char value;
        int frequency;

        Node left;
        Node right;

        public Node(char value) {
            this.value = value;
        }

        public Node(char value, int frequency) {
            this.value = value;
            this.frequency = frequency;
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
