package basic.dp.codechef;


import java.util.Arrays;
import java.util.Scanner;

/**
 * @author muhossain
 * @since 2019-09-15
 */

public class Coupon {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int T = 0;

        if (in.hasNext()) {
            T = in.nextInt();
        }

        StringBuilder output = new StringBuilder();

        for (int c = 0; c < T; c++) {
            int n = in.nextInt();
            int m = in.nextInt();

            long[][] prices = new long[n][m];
            long[][] discounts = new long[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    prices[i][j] = in.nextLong();
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    discounts[i][j] = in.nextLong();
                }
            }

            long[][] dp = new long[n][m];

            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], Long.MAX_VALUE);
            }

            long totalExp = findMinExpense(n, m, discounts, prices, dp);

            output.append(totalExp).append("\n");
        }

        System.out.print(output);
    }

    private static long findMinExpense(int items, int shops, long[][] discounts, long[][] prices,
                                       long[][] dp) {

        long minPrevious = Long.MAX_VALUE;

        for (int j = 0; j < shops; j++) {
            dp[0][j] = prices[0][j];
            minPrevious = Math.min(minPrevious, dp[0][j]);
        }

        for (int i = 1; i < items; i++) {
            long currentStateMinExp = Long.MAX_VALUE;

            for (int j = 0; j < shops; j++) {
                dp[i][j] = Math.min(minPrevious + prices[i][j], Math.max(0, prices[i][j] - discounts[i - 1][j]) + dp[i - 1][j]);

                currentStateMinExp = Math.min(currentStateMinExp, dp[i][j]);
            }

            minPrevious = currentStateMinExp;
        }

        // min exp in last dp state
        return minPrevious;
    }
}
