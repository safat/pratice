package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C1105 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int l = fs.nextInt();
        int r = fs.nextInt();

//        R/3-(L-1)/3,
//                (R+2)/3-(L+1)/3,
//                (R+1)/3-(L)/3

        long m0 = (long) (Math.floor(r / 3.0) - Math.floor(l / 3.0)) + (l % 3 == 0 ? 1 : 0);
        long m1 = (long) (Math.floor((r + 2) / 3.0) - Math.floor((l + 2) / 3.0)) + (l % 3 == 1 ? 1 : 0);
        long m2 = (long) (Math.floor((r + 1) / 3.0) - Math.floor((l + 1) / 3.0)) + (l % 3 == 2 ? 1 : 0);


        long[][] dp = new long[n + 1][3];

        dp[1][0] = m0;
        dp[1][1] = m1;
        dp[1][2] = m2;
        long max = (long) Math.pow(10, 9) + 7;

        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i - 1][0] * m0 + dp[i - 1][1] * m2 + dp[i - 1][2] * m1) % max;
            dp[i][1] = (dp[i - 1][0] * m1 + dp[i - 1][2] * m2 + dp[i - 1][1] * m0) % max;
            dp[i][2] = (dp[i - 1][0] * m2 + dp[i - 1][1] * m1 + dp[i - 1][2] * m0) % max;
        }

        System.out.println(dp[n][0]);
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
