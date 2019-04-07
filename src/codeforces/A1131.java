package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A1131 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        long w1 = fs.nextLong();
        long h1 = fs.nextLong();
        long w2 = fs.nextLong();
        long h2 = fs.nextLong();

        long cW = Math.max(w1, w2);
        long cH = h1 + h2;

        long totalOuter = (cH + 2) * (cW + 2);
        long totalInner = cH * cW;

        System.out.println(totalOuter - totalInner);
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
