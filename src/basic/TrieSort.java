package basic;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class TrieSort {

    public static void main(String[] args) throws IOException {
//        generateData(10000000);

        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));

        String input = br.readLine();
        String[] splitedInput = input.split(" ");

        ArrayList<Long> data1 = new ArrayList<>();
        ArrayList<Long> data2 = new ArrayList<>();

        for (String s : splitedInput) {
            data1.add(Long.parseLong(s));
            data2.add(Long.parseLong(s));
        }

        System.out.println("data size: " + data1.size());

        long start = System.currentTimeMillis();
        Collections.sort(data1);

        System.out.println("For merge sort " + (System.currentTimeMillis() - start) / 1000 + " s");

        start = System.currentTimeMillis();

        Node root = new Node(null);
        Node current;

        for (long value : data2) {
            current = root;

            String binaryStr = BigInteger.valueOf(value).toString(2);
            byte len = (byte) binaryStr.length();

            for (int i = 0; i < 63 - len; i++) {
                if (current.left == null) {
                    current = current.addLeft((byte) 0);
                } else {
                    current = current.left;
                }
            }

            for (int i = 0; i < len; i++) {
                if (binaryStr.charAt(i) == '1') {
                    if (current.right == null) {
                        current = current.addRight((byte) 1);
                    } else {
                        current = current.right;
                    }
                } else {
                    if (current.left == null) {
                        current = current.addLeft((byte) 0);
                    } else {
                        current = current.left;
                    }
                }
            }

            current.count++;
            current.terminal = true;
        }

        List<Long> output = new ArrayList<>();
        long value = 0;

        long dfsStart = System.currentTimeMillis();
        dfs(root, output, value, 63);
        System.out.println("dfs time: " + (System.currentTimeMillis() - dfsStart) / 1000 + " s");

        System.out.println("trie sort output size: " + output.size());
        System.out.println("trie sort time: " + (System.currentTimeMillis() - start) / 1000 + " s");

        boolean sortedRight = true;

        for (int i = 0; i < data1.size(); i++) {
            if (!data1.get(i).equals(output.get(i))) {
                sortedRight = false;
                break;
            }
        }

        System.out.println(sortedRight ? "Sorted Right " : "Not Sorted Right");
    }

    private static void dfs(Node cNode, List<Long> output, long value, int depth) {
        if (cNode == null || depth == -1) {
            return;
        }

        if (cNode.value != null && cNode.value == 1) {
            value += Math.pow(2, depth);
        }

        if (cNode.terminal) {
            int count = cNode.count;

            while (count > 0) {
                output.add(value);
                count--;
            }

            return;
        }

        dfs(cNode.left, output, value, depth - 1);
        dfs(cNode.right, output, value, depth - 1);
    }

    static class Node {

        Byte value;
        int count;
        boolean terminal;

        Node left;
        Node right;

        Node(Byte value) {
            this.value = value;
        }

        public Node addLeft(Byte value) {
            this.left = new Node(value);
            return left;
        }

        public Node addRight(Byte value) {
            this.right = new Node(value);
            return right;
        }
    }

    private static void generateData(long limit) throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter("input.txt"));

        for (int i = 0; i < limit; i++) {
            pr.append(String.valueOf((long) (Math.random() * Math.random() * Long.MAX_VALUE))).append(" ");
        }

        pr.flush();
        pr.close();
    }

}


