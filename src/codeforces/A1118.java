package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A1118 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        long q = fs.nextLong();


        StringBuilder output = new StringBuilder();

        for (int i = 0; i < q; i++) {
            long n = fs.nextLong();
            long a = fs.nextLong(); //1 lit
            long b = fs.nextLong(); // 2 lit
            long result;

            if (2 * a <= b) {
                // a cheap option
                result = n * a;
            } else {
                result = (n / 2) * b + (n % 2) * a;
            }

            output.append(result).append("\n");
        }

        System.out.print(output);
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
