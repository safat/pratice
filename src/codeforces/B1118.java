//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1118 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();

        long[] items = new long[n + 1];
        long[][] cumSum = new long[n + 1][2]; //0 even, 1 = odd
        long oddSum = 0;
        long evenSum = 0;

        for (int i = 1; i <= n; i++) {
            items[i] = fs.nextLong();

            if (i % 2 == 0) {
                evenSum += items[i];
            } else {
                oddSum += items[i];
            }

            cumSum[i][0] = evenSum;
            cumSum[i][1] = oddSum;
        }

        int count = 0;

        for (int i = 1; i <= n; i++) {
            long leftOddSum = cumSum[i - 1][1];
            long leftEvenSum = cumSum[i - 1][0];

            long rightOddSum = cumSum[n][1] - cumSum[i][1];
            long rightEvenSum = cumSum[n][0] - cumSum[i][0];

            if (leftEvenSum + rightOddSum == leftOddSum + rightEvenSum) {
                count++;
            }
        }

        System.out.println(count);
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
