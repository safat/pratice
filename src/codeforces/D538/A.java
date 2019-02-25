//package codeforces.D538;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        long a = fs.nextLong();
        long d = fs.nextLong();
        long m = fs.nextLong();

        long g = fs.nextLong();
        long p = fs.nextLong();
        long b = fs.nextLong();

        long remaining = 0;

        boolean canMeet = true;

        if (g < a) {
            canMeet = false;
        } else {
            remaining = g - a;
        }

        if ((remaining + p) < d) {
            canMeet = false;
        } else {
            remaining = remaining + p - d;
        }

        if ((remaining + b) < m) {
            canMeet = false;
        }

        System.out.println(canMeet ? "YES" : "NO");
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
