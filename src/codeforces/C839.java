package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class C839 {
    public static void main(String[] args) {
        FastScanner input = new FastScanner(System.in);
        int n = input.nextInt();

        Map<Integer, List<Integer>> adjMap = new HashMap<>();

        for (int i = 1; i < n; i++) {
            int u = input.nextInt();
            int v = input.nextInt();

            adjMap.putIfAbsent(u, new ArrayList<>());
            adjMap.putIfAbsent(v, new ArrayList<>());

            adjMap.get(u).add(v);
            adjMap.get(v).add(u);
        }

        boolean[] visited = new boolean[n + 1];
        List<Double> result = new ArrayList<>();
        result.add(0.0);

        dfs(1, adjMap, visited, result, 0, 1);

        System.out.println(result.get(0));
    }

    private static void dfs(int node, Map<Integer, List<Integer>> adjMap, boolean[] visited, List<Double> result, long len, double p) {
        visited[node] = true;

        List<Integer> adjNodes = adjMap.get(node);

        if (adjNodes == null || adjNodes.isEmpty() || (adjNodes.size() == 1 && visited[adjNodes.get(0)])) {
            result.set(0, result.get(0) + p * len);
            return;
        }

        for (int cNode : adjNodes) {
            if (!visited[cNode]) {

                dfs(cNode, adjMap, visited, result, len + 1, p / (node == 1 ? adjNodes.size() : adjNodes.size() - 1));
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
