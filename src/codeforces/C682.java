package codeforces;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-03-08
 */

public class C682 {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        Map<Pair, Integer> costMap = new HashMap<>();

        FastScanner fs = new FastScanner();
        int n = fs.nextInt();

        int[] nodeValue = new int[n + 1];

        for (int i = 0; i < n; i++) {
            nodeValue[i + 1] = fs.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = i + 2;
            int v = fs.nextInt();
            int c = fs.nextInt();

            costMap.put(new Pair(u, v), c);

            adjacencyList.putIfAbsent(u, new ArrayList<>());
            adjacencyList.putIfAbsent(v, new ArrayList<>());

            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }

        Set<Integer> visited = new HashSet<>();

        int visitedCount = dfs(1, 0, adjacencyList, visited, costMap, nodeValue);

        System.out.println(n - visitedCount);
    }

    private static int dfs(int node, int totalCost, Map<Integer, List<Integer>> adjacencyList, Set<Integer> visited,
                           Map<Pair, Integer> costMap, int[] nodeValue) {


        visited.add(node);

        if (totalCost > nodeValue[node]) {
            return 0;
        }

        int visitedCount = 1;

        for (int adjacency : adjacencyList.getOrDefault(node, Collections.emptyList())) {
            if (visited.contains(adjacency)) {
                continue;
            }

            visitedCount += dfs(adjacency, Math.max(0, totalCost + costMap.get(new Pair(node, adjacency))), adjacencyList, visited, costMap, nodeValue);
        }

        return visitedCount;
    }

    private static class Pair {
        int u, v;

        public Pair(int u, int v) {
            this.u = u;
            this.v = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;


            return (this.u == pair.u && this.v == pair.v) || (this.u == pair.v && this.v == pair.u);

        }

        @Override
        public int hashCode() {
            int result = u;
            result = 31 * result + 31 * v;
            return result;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "u=" + u +
                    ", v=" + v +
                    '}';
        }
    }

    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            try {
                br = new BufferedReader(new InputStreamReader(System.in));
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
