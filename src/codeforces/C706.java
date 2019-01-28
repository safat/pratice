//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class C706 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        long[] costs = new long[input.length];

        for (int i = 0; i < costs.length; i++) {
            costs[i] = Long.parseLong(input[i]);
        }

        String[] words = new String[n];
        String[] revWords = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            revWords[i] = new StringBuilder(words[i]).reverse().toString();
        }

        long[][] dp = new long[n][2];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }

        dp[0][0] = 0;
        dp[0][1] = costs[0];

        for (int i = 1; i < n; i++) {

            if (words[i].compareTo(words[i - 1]) >= 0) {
                dp[i][0] = dp[i - 1][0];
            }

            if (words[i].compareTo(revWords[i - 1]) >= 0) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i][0]);
            }

            if (revWords[i].compareTo(words[i - 1]) >= 0 && dp[i - 1][0] != Long.MAX_VALUE) {
                dp[i][1] = dp[i - 1][0] + costs[i];
            }

            if (revWords[i].compareTo(revWords[i - 1]) >= 0 && dp[i - 1][1] != Long.MAX_VALUE) {
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][1] + costs[i]);
            }

            if (dp[i][0] == Long.MAX_VALUE && dp[i][1] == Long.MAX_VALUE) {
                break;
            }
        }

        long result = Math.min(dp[n - 1][0], dp[n - 1][1]);

        System.out.println(result != Long.MAX_VALUE ? result : -1);
    }
}