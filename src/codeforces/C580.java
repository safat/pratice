//package codeforces;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-01-21
 */

public class C580 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int m = fs.nextInt();

        boolean[] cat = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            cat[i] = fs.nextInt() == 1;
        }

        Map<Integer, List<Integer>> adjacency = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            int x = fs.nextInt();
            int y = fs.nextInt();

            adjacency.putIfAbsent(x, new ArrayList<>());
            adjacency.get(x).add(y);

            adjacency.putIfAbsent(y, new ArrayList<>());
            adjacency.get(y).add(x);
        }

        int pathCount = dfs(1, adjacency, false, 0, cat, m);

        System.out.println(pathCount);
    }

    private static int dfs(int currentNode, Map<Integer, List<Integer>> adjacency, boolean prevCat,
                           int cCurrent, boolean[] cat, int m) {

        if (cat[currentNode]) {
            cCurrent++;
            prevCat = true;
        } else {
            if (cCurrent <= m) {
                cCurrent = 0;
                prevCat = false;
            }
        }

        if (adjacency.get(currentNode) == null || adjacency.get(currentNode).isEmpty()) {
            return cCurrent > m ? 0 : 1;
        }

        int total = 0;

        for (int node : adjacency.get(currentNode)) {
            if (adjacency.get(node) != null) {
                adjacency.get(node).remove((Integer) currentNode);
            }

            total += dfs(node, adjacency, prevCat, cCurrent, cat, m);
        }

        return total;
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
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
