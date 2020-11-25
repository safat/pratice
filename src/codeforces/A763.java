package codeforces;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-02-18
 */

public class A763 {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        Map<Integer, Set<Integer>> adjacencies = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();

            adjacencies.putIfAbsent(u, new HashSet<>());
            adjacencies.putIfAbsent(v, new HashSet<>());

            adjacencies.get(u).add(v);
            adjacencies.get(v).add(u);
        }

        Map<Integer, Integer> colorMap = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            colorMap.put(i, fs.nextInt());
        }

        int maxMisMatchedNode = 1;
        int[] mismatchCount = new int[n + 1];

        for (Map.Entry<Integer, Set<Integer>> entry : adjacencies.entrySet()) {
            int u = entry.getKey();
            Set<Integer> neighbours = entry.getValue();

            for (int nei : neighbours) {
                if (!colorMap.get(nei).equals(colorMap.get(u))) {
                    mismatchCount[nei]++;

                    if (mismatchCount[nei] > mismatchCount[maxMisMatchedNode]) {
                        maxMisMatchedNode = nei;
                    }

                    if (mismatchCount[u] > mismatchCount[maxMisMatchedNode]) {
                        maxMisMatchedNode = u;
                    }
                }
            }
        }

        boolean found = true;

        for (int i = 1; i <= n; i++) {
            if (i == maxMisMatchedNode ||
                    (adjacencies.get(maxMisMatchedNode) != null && adjacencies.get(maxMisMatchedNode).contains(i))) {
                continue;
            }

            if (mismatchCount[i] != 0) {
                found = false;
            }
        }

        System.out.println(found ? "YES\n" + maxMisMatchedNode : "NO");
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
