package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A1110 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int b = fs.nextInt();
        int k = fs.nextInt();

        int[] digits = new int[k];

        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] = fs.nextInt();
        }

        boolean even = false;

        if (b % 2 == 0) {

            if (digits[0] % 2 == 0) {
                even = true;
            }
        } else {
            int oddCount = 0;

            for (int d : digits) {
                if (d % 2 != 0) {
                    oddCount++;
                }
            }

            if (oddCount % 2 == 0) {
                even = true;
            }
        }

        System.out.println(even ? "even" : "odd");
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
