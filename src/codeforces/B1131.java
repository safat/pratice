//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1131 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        long result = 1;
        int s1 = 0;
        int s2 = 0;

        for (int i = 0; i < n; i++) {
            int e1 = fs.nextInt();
            int e2 = fs.nextInt();

            int maxStart = Math.max(s1, s2);
            int minEnd = Math.min(e1, e2);

            long draws = 0;

            if (s1 == s2) {
                draws = Math.max(minEnd - maxStart, 0);
            } else {
                draws = Math.max(minEnd - maxStart + 1, 0);
            }

            result += draws;

            s1 = e1;
            s2 = e2;
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
