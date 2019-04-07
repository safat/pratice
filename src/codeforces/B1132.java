package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1132 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int[] cost = new int[n];

        long total = 0;

        for (int i = 0; i < n; i++) {
            cost[i] = fs.nextInt();
            total += cost[i];
        }

        int q = fs.nextInt();
        int[] coupons = new int[q];

        for (int i = 0; i < q; i++) {
            coupons[i] = fs.nextInt();
        }

        Arrays.sort(cost);

        StringBuilder output = new StringBuilder();

        for (int c : coupons) {
            output.append(total - (c > 0 ? cost[n - c] : 0)).append("\n");
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
