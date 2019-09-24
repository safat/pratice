package basic.dp.codechef;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author muhossain
 * @since 2019-09-22
 */

public class Spidy2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = 0;

        if (in.hasNext()) {
            n = in.nextInt();
        }

        long[] height = new long[n];

        for (int i = 0; i < n; i++) {
            height[i] = in.nextLong();
        }

        long[] dp = new long[n];

        Arrays.fill(dp, Long.MAX_VALUE);

        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int jump = 1;
            int position = i + 1;

            while (position < n) {
                dp[position] = Math.min(dp[position], Math.abs(height[position] - height[i]) + dp[i]);

                jump <<= 1;
                position = i + jump;
            }
        }

        System.out.println(dp[n - 1]);
    }
}
