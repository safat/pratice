package codeforces;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-02-15
 */

public class A429 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();

        Map<Integer, List<Integer>> adjacencies = new HashMap<>();
        byte[] currentBits = new byte[n + 1];
        byte[] expectedBits = new byte[n + 1];

        for (int i = 0; i < n - 1; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();

            adjacencies.putIfAbsent(u, new ArrayList<>());
            adjacencies.putIfAbsent(v, new ArrayList<>());

            adjacencies.get(u).add(v);
            adjacencies.get(v).add(u);
        }

        for (int i = 1; i <= n; i++) {
            currentBits[i] = (byte) fs.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            expectedBits[i] = (byte) fs.nextInt();
        }

        boolean[] visited = new boolean[n + 1];

        List<String> resultNodes = new ArrayList<>();

        dfs(1, adjacencies, visited, 1, resultNodes, false,
                false, currentBits, expectedBits);


        System.out.println(resultNodes.size());
        System.out.print(String.join("\n", resultNodes));

    }

    private static void dfs(int node, Map<Integer, List<Integer>> adjacencies,
                            boolean[] visited, int rank, List<String> resultNodes,
                            boolean evenFlip, boolean oddFlip, byte[] currentBits, byte[] expectedBits) {

        visited[node] = true;


        if ((currentBits[node] == expectedBits[node] && sameParityFlipped(rank, evenFlip, oddFlip)) ||
                (currentBits[node] != expectedBits[node] && !sameParityFlipped(rank, evenFlip, oddFlip))) {

            resultNodes.add(String.valueOf(node));

            evenFlip = rank % 2 == 0 ? !evenFlip : evenFlip;
            oddFlip = rank % 2 != 0 ? !oddFlip : oddFlip;
        }

        if (adjacencies.get(node) == null) {
            return;
        }

        for (int cNode : adjacencies.get(node)) {
            if (visited[cNode]) {
                continue;
            }

            dfs(cNode, adjacencies, visited, rank + 1, resultNodes, evenFlip, oddFlip, currentBits, expectedBits);
        }
    }

    private static boolean sameParityFlipped(int rank, boolean evenFlip, boolean oddFlip) {
        return rank % 2 == 0 ? evenFlip : oddFlip;

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

    private static class Node {
        int value;
        int rank;

        public Node(int value, int rank) {
            this.value = value;
            this.rank = rank;
        }
    }
}
