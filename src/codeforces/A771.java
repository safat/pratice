package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class A771 {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();

        Map<Integer, Set<Integer>> adjMap = new HashMap<>();
        int maxNodeNo = 0;

        for (int i = 0; i < m; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();

            adjMap.putIfAbsent(u, new HashSet<>());
            adjMap.putIfAbsent(v, new HashSet<>());

            adjMap.get(u).add(v);
            adjMap.get(v).add(u);

            maxNodeNo = Math.max(maxNodeNo, Math.max(u, v));
        }

        boolean[] visited = new boolean[maxNodeNo + 1];
        boolean relevant = true;

        for (int i = 1; i <= n; i++) {
            if (!adjMap.containsKey(i) || visited[i]) {
                continue;
            }

            int[] nodeEdgeCount = new int[2];
            dfs(i, adjMap, visited, nodeEdgeCount);

            long nodeCountInSubGraph = nodeEdgeCount[0];
            long edgeCountInSubGraph = nodeEdgeCount[1] / 2;
            long expectedEdgeCount = (nodeCountInSubGraph * (nodeCountInSubGraph - 1)) / 2;

            if (expectedEdgeCount != edgeCountInSubGraph) {
                relevant = false;
                break;
            }
        }

        System.out.println(relevant ? "YES" : "NO");
    }

    private static void dfs(int node, Map<Integer, Set<Integer>> adjMap, boolean[] visited, int[] nodeEdgeCount) {
        visited[node] = true;
        nodeEdgeCount[0]++;

        Set<Integer> adjSet = adjMap.get(node);

        if (adjSet == null || adjSet.isEmpty()) {
            return;
        }

        for (int aNode : adjMap.get(node)) {
            nodeEdgeCount[1]++;

            if (!visited[aNode]) {
                dfs(aNode, adjMap, visited, nodeEdgeCount);
            }
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
