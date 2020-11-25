package shopee;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-06-27
 */

public class JudgingServer {

    boolean[][] vis;
    long[][] dp;

    public static void main(String[] args) {

        JudgingServer soln = new JudgingServer();
        soln.solve();

    }

    private void solve() {
        FastScanner fs = new FastScanner(System.in);
        int T = fs.nextInt();

        StringBuilder output = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            int s = fs.nextInt();
            int n = fs.nextInt();

            vis = new boolean[s + 1][n + 1];
            dp = new long[s + 1][n + 1];

            long[] prices = new long[s];

            for (int i = 0; i < prices.length; i++) {
                prices[i] = fs.nextLong();
            }

            long cost = findCost(0, prices, n);

            output.append("Case ").append(t).append(": ").append(cost).append("\n");
        }

        System.out.print(output);

    }

    private long findCost(int idx, long[] prices, int remaining) {
        if (remaining <= 0) {
            return 0;
        }

        if (idx >= prices.length) {
            return Long.MAX_VALUE;
        }

        if (vis[idx][remaining]) {
            return dp[idx][remaining];
        }

        vis[idx][remaining] = true;

    // nibo na
        long ret = findCost(idx + 1, prices, remaining);

        // free te nibo
        if (idx + 2 <= prices.length) {
            if (findCost(idx + 2, prices, remaining - 2) < Long.MAX_VALUE) {
                ret = Long.min(ret, findCost(idx + 2, prices, remaining - 2) + prices[idx + 1]);
                ret = Long.min(ret, findCost(idx + 2, prices, remaining - 2) + prices[idx]);
            }
        }

        // current ta kinsi
        if (idx + 1 <= prices.length) {
            if (findCost(idx + 1, prices, remaining - 1) < Long.MAX_VALUE) {
                ret = Long.min(ret, findCost(idx + 1, prices, remaining - 1) + prices[idx]);
            }
        }


        return dp[idx][remaining] = ret;
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
