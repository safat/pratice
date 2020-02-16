package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author muhossain
 * @since 2020-01-27
 */

public class C431 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
        int d = Integer.parseInt(input[2]);

        long[][] dp = new long[102][2];

        for (int i = 0; i < 102; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i], -1);
            }
        }

        // state, starting node, depth, limit matched, limit
        long count = dfs(dp, 0, d, 0, k, n);

        System.out.println(count);
    }

    private static long dfs(long[][] dp, int limitMatched,
                            int d, int sum, int k, int n) {

        if (sum > n) {
            return 0;
        }

        if (sum == n) {
            return limitMatched;
        }

        if (dp[sum][limitMatched] != -1) {
            return dp[sum][limitMatched];
        }

        long total = 0;

        long mod = 1000000007L;

        for (int i = 1; i <= k; i++) {

            if (limitMatched == 1 || i >= d) {
                total = (total + dfs(dp, 1, d, i + sum, k, n)) % mod;
            } else {
                total = (total + dfs(dp, 0, d, i + sum, k, n)) % mod;
            }
        }

        return dp[sum][limitMatched] = total % mod;
    }
}
