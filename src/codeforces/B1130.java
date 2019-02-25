package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class B1130 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        int[] cakes = new int[2 * n + 1];
        int[][] positions = new int[2 * n + 1][2];

        for (int i = 1; i <= 2 * n; i++) {
            cakes[i] = fs.nextInt();

            if (positions[cakes[i]][0] == 0) {
                positions[cakes[i]][0] = i;
            } else {
                positions[cakes[i]][1] = i;
            }
        }

        BigInteger result = BigInteger.ZERO;
        long pointer1 = 1, pointer2 = 1;

        for (int i = 1; i <= n; i++) {
            result = result.add(BigInteger.valueOf(Math.min(Math.abs((long) positions[i][0] - pointer1) + Math.abs((long) positions[i][1] - pointer2),
                    Math.abs((long) positions[i][1] - pointer1) + Math.abs((long) positions[i][0] - pointer2))));

            pointer1 = positions[i][0];
            pointer2 = positions[i][1];
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
