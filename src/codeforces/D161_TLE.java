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

public class D161_TLE {

    static long total = 0;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

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

        Map<Integer, Map<Integer, Integer>> countMap = new HashMap<>();

        dfs(-1, 1, adjacency, countMap, k);
        System.out.println(total);
    }

    private static Map<Integer, Integer> dfs(int parent, int node, Map<Integer, Set<Integer>> adjacency,
                                             Map<Integer, Map<Integer, Integer>> countMap, int k) {

        Map<Integer, Integer> lengthCountMap = new HashMap<>();
        lengthCountMap.put(0, 1);

        for (int adj : adjacency.getOrDefault(node, Collections.emptySet())) {
            if (adj == parent) {
                continue;

            }

            Map<Integer, Integer> cLengthMap = dfs(node, adj, adjacency, countMap, k);

            for (Map.Entry<Integer, Integer> entry : cLengthMap.entrySet()) {
                if (entry.getKey() == k) {
                    continue;
                }

                lengthCountMap.putIfAbsent(entry.getKey() + 1, 0);
                lengthCountMap.computeIfPresent(entry.getKey() + 1, (xk, xv) -> xv + entry.getValue());
            }
        }

        countMap.put(node, lengthCountMap);

        long cTotal = 0;

        for (int vNode : adjacency.getOrDefault(node, Collections.emptySet())) {
            for (int i = 1; i < k; i++) {
                Map<Integer, Integer> vCountMap = countMap.getOrDefault(vNode, Collections.emptyMap());
                Map<Integer, Integer> uCountMap = countMap.getOrDefault(node, Collections.emptyMap());

                cTotal += vCountMap.getOrDefault(i - 1, 0) *
                        (uCountMap.getOrDefault(k - i, 0) -
                                vCountMap.getOrDefault(k - i - 1, 0));
            }
        }

        total += countMap.getOrDefault(node, Collections.emptyMap()).getOrDefault(k, 0);
        total += (cTotal / 2);

        return lengthCountMap;
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