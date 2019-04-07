package basic.dp;

import java.util.Arrays;

public class BinomialCoefficient {
    public static void main(String[] args) {
        long dp[][] = new long[500][500];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(findNcK(100, 10, dp));
    }

    public static long findNcK(int n, int k, long[][] dp) {
        if (dp[n][k] != -1) {
            return dp[n][k];
        }

        if (n == k) {
            return 1;
        }

        if (k == 1) {
            return n;
        }

        return dp[n][k] = findNcK(n - 1, k - 1, dp) + findNcK(n - 1, k, dp);
    }
}
