package codeforces;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-02-20
 */

public class D161 {

    private static long total = 0;

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int k = fs.nextInt();

        Map<Integer, Set<Integer>> adjacency = new HashMap<>();


        for (int i = 0; i < n - 1; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();

            adjacency.putIfAbsent(u, new HashSet<>());
            adjacency.putIfAbsent(v, new HashSet<>());

            adjacency.get(u).add(v);
            adjacency.get(v).add(u);
        }

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        dfs(-1, 1, adjacency, dp, k);
        System.out.println(total);
    }

    private static void dfs(int parent, int node, Map<Integer, Set<Integer>> adjacency,
                            long[][] dp, int k) {

        for (int adj : adjacency.getOrDefault(node, Collections.emptySet())) {
            if (adj == parent) {
                continue;

            }

            dfs(node, adj, adjacency, dp, k);

            for (int i = 0; i < k; i++) {
                total += (dp[adj][i] * dp[node][k - i - 1]);
            }

            //merge with parent
            for (int i = 0; i < k; i++) {
                dp[node][i + 1] += dp[adj][i];
            }
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