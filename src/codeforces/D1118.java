package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D1118 {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        long n = fs.nextLong();
        long m = fs.nextLong();

        long[] items = new long[(int) n];
        long[] cumSum = new long[(int) n];

        for (int i = 0; i < items.length; i++) {
            items[i] = fs.nextLong();
        }

        Arrays.sort(items);

        long[] itemBk = new long[(int) n];

        for (int i = 0; i < itemBk.length; i++) {
            itemBk[i] = items[items.length - i - 1];
        }

        long sum = 0;

        for (int i = 0; i < itemBk.length; i++) {
            sum += itemBk[i];
            cumSum[i] = sum;
        }

        int result = solve(sum, m, itemBk, cumSum);

        System.out.println(result);
    }

    private static int solve(long sum, long m, long[] items, long[] cumSum) {
        if (sum < m) {
            return -1;
        }

        int maxDays = items.length;

        for (int i = 0; i < maxDays; i++) {
            long target = cumSum[i];
            long penalty = 1;
            int count = 0;

            for (int j = i + 1; j < maxDays; j++) {
                target += Math.max(0, items[j] - penalty);

                count++;

                if (count % (i + 1) == 0) {
                    penalty++;
                }
            }

            if (target >= m) {
                return i + 1;
            }
        }

        return maxDays;
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
