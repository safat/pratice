//package codeforces;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-07-22
 */

public class D118 {

    static int MOD = 100000000;

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        int n1 = fs.nextInt();
        int n2 = fs.nextInt();
        int k1 = fs.nextInt();
        int k2 = fs.nextInt();

        int[][][][] dp = new int[n1 + 1][n2 + 1][k1 + 1][k2 + 1];

        for (int i = 0; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                for (int k = 0; k <= k1; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        System.out.println(count(n1, n2, 0, 0, k1, k2, dp));
    }

    private static int count(int n1, int n2, int contN1, int contN2, int k1, int k2, int[][][][] dp) {
//        System.out.println("n1:" + n1 + ", n2: " + n2 + ", contN1: " + contN1 + ", k1: " + k1 + ", contN2: " + contN2 + ", k2: " + k2);

        if (n1 < 0 || n2 < 0) {
            return 0;
        }

        if (contN1 > k1 || contN2 > k2) {
            return 0;
        }

        if (n1 == 0 && n2 == 0) {
            return 1;
        }

        if (dp[n1][n2][contN1][contN2] != -1) {
            return dp[n1][n2][contN1][contN2];
        }

        return dp[n1][n2][contN1][contN2] = (count(n1 - 1, n2, contN1 + 1, 0, k1, k2, dp) % MOD +
                count(n1, n2 - 1, 0, contN2 + 1, k1, k2, dp) % MOD) % MOD;
    }

    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            try {
                br = new BufferedReader(new InputStreamReader(System.in));
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
