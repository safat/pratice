//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A1130 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int positiveCount = 0;
        int negativeCount = 0;

        int n = fs.nextInt();

        for (int i = 0; i < n; i++) {
            int input = fs.nextInt();

            if (input > 0) {
                positiveCount++;
            } else if (input < 0) {
                negativeCount++;
            }
        }

        int expectedResult = (n + 1) / 2;

        if (positiveCount >= expectedResult) {
            System.out.println(1);
        } else if (negativeCount >= expectedResult) {
            System.out.println(-1);
        } else {
            System.out.println(0);
        }
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
