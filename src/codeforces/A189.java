//package codeforces;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

/**
 * @author muhossain
 * @since 2020-10-19
 */

public class A189 {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int a = fs.nextInt();
        int b = fs.nextInt();
        int c = fs.nextInt();

        //TLE
//        int maxPieces = solve(n, tmp.get(0), tmp.get(1), tmp.get(2), dp);
//        int maxPieces = solve2(n, tmp);

        int maxPieces = solve3(n, a, b, c);

        System.out.println(maxPieces);

    }

    private static int solve3(int n, int a, int b, int c) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);

        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            if (i - a >= 0) {
                dp[i] = Math.max(dp[i], dp[i - a] + 1);
            }

            if (i - b >= 0) {
                dp[i] = Math.max(dp[i], dp[i - b] + 1);
            }

            if (i - c >= 0) {
                dp[i] = Math.max(dp[i], dp[i - c] + 1);
            }
        }

        return dp[n];
    }

    private static int solve2(int n, List<Integer> numbers) {
        int[] dp = new int[n + 1];

        Arrays.fill(dp, -1);

        if (numbers.get(0) <= n) {
            dp[numbers.get(0)] = 1;
        }

        if (numbers.get(1) <= n) {
            dp[numbers.get(1)] = 1;
        }

        if (numbers.get(2) <= n) {
            dp[numbers.get(2)] = 1;
        }

        for (int i = numbers.get(0); i <= n; i++) {
            if (dp[i] == -1) {
                continue;
            }

            if (i + numbers.get(0) <= n) {
                dp[i + numbers.get(0)] = Math.max(dp[i + numbers.get(0)], dp[i] + 1);
            }

            if (i + numbers.get(1) <= n) {
                dp[i + numbers.get(1)] = Math.max(dp[i + numbers.get(1)], dp[i] + 1);
            }

            if (i + numbers.get(2) <= n) {
                dp[i + numbers.get(2)] = Math.max(dp[i + numbers.get(2)], dp[i] + 1);
            }

        }

        return dp[n];
    }

    private static int solve(int n, int a, int b, int c, int[] dp) {
        if (dp[n] >= 0) {
            return dp[n];
        }

        if (n == 0) {
            return 0;
        }

        int pathA = n - a >= 0 ? solve(n - a, a, b, c, dp) + 1 : Integer.MIN_VALUE;
        int pathB = n - b >= 0 ? solve(n - b, a, b, c, dp) + 1 : Integer.MIN_VALUE;
        int pathC = n - c >= 0 ? solve(n - c, a, b, c, dp) + 1 : Integer.MIN_VALUE;

        return dp[n] = Math.max(Math.max(pathA, pathB), pathC);
    }

    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            try {
                br = new BufferedReader(new InputStreamReader(System.in));
//				br = new BufferedReader(new FileReader("cases.in"));
                st = new StringTokenizer("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String next() {
            if (st.hasMoreTokens()) return st.nextToken();
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public String nextLine() {
            String line = "";
            try {
                line = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return line;
        }

        public Integer[] nextIntegerArray(int n) {
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        public char[] nextCharArray() {
            return nextLine().toCharArray();
        }
    }
}
