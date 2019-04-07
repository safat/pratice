package codeforces.AAA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class A {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();
        int k = fs.nextInt();

        int[] p = new int[n + 1];
        int[] sch = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            p[i] = fs.nextInt();
        }

        Map<Integer, List<Integer>> powerMap = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            int s = fs.nextInt();

            sch[i] = s;

            powerMap.putIfAbsent(s, new ArrayList<>());
            powerMap.get(s).add(p[i]);
        }

        int[] q = new int[k + 1];

        for (int i = 1; i <= k; i++) {
            q[i] = fs.nextInt();
        }

        for (List<Integer> values : powerMap.values()) {
            Collections.sort(values);
        }

        int result = 0;

        for (int i = 1; i <= k; i++) {
            int id = q[i];
            int power = p[id];
            int school = sch[id];

            List<Integer> powerList = powerMap.get(school);

            if (power < powerList.get(powerList.size() - 1)) {
                result++;
            }
        }

        System.out.println(result);
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
