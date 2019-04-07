package basic.dp;

import java.util.Arrays;

public class Tiling {
    public static void main(String[] args) {
        int[] dp = new int[100];
        Arrays.fill(dp, -1);

        System.out.println(count(10, dp));
    }

    private static int count(int n, int[] dp) {
        if (dp[n] != -1) {
            return dp[n];
        }

        if (n == 1 || n == 2) {
            return n;
        }

        return (dp[n] = count(n - 1, dp) + count(n - 2, dp));
    }
}
