package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-07-21
 */

public class B577 {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int m = fs.nextInt();

        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = fs.nextInt() % m;
        }

        System.out.println(isModuloSum(numbers, m) ? "YES" : "NO");
    }

    private static boolean isModuloSum(int[] numbers, int m) {
        if (numbers.length > m) {
            return true;
        }

        Boolean[][] dp = new Boolean[m + 1][m + 1];

        return isModuleSumUtil(0, numbers, 0, dp, m);
    }

    private static boolean isModuleSumUtil(int idx, int[] numbers, int sum, Boolean[][] dp, int m) {
        if (sum == m) {
            return true;
        }

        if (idx == numbers.length) {
            return false;
        }

        if (dp[idx][sum] != null) {
            return dp[idx][sum];
        }

        int cMod = (numbers[idx] + sum) % m;

        return dp[idx][sum] = (isModuleSumUtil(idx + 1, numbers, cMod == 0 ? m : cMod, dp, m) ||
                isModuleSumUtil(idx + 1, numbers, sum, dp, m));
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
