package codeforces;


import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * @author muhossain
 * @since 2020-10-01
 */

public class C1418 {

    public static void main(String[] args) {
        FasterScanner fs = new FasterScanner();

        int T = fs.nextInt();

        StringBuilder output = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int n = fs.nextInt();

            int[] nums = new int[n];

            for (int i = 0; i < nums.length; i++) {
                nums[i] = fs.nextInt();
            }

            int[][] dp = new int[n + 1][2];

            for (int[] d : dp) {
                Arrays.fill(d, -1);
            }

            int optimumPoints = dfs(nums, 0, true, dp);

            output.append(optimumPoints).append("\n");
        }

        System.out.print(output);
    }

    private static int dfs(int[] nums, int idx, boolean aMove, int[][] dp) {
        if (idx >= nums.length) {
            return 0;
        }

        if (dp[idx][aMove ? 0 : 1] != -1) {
            return dp[idx][aMove ? 0 : 1];
        }

        int total = aMove ? Math.min(
                nums[idx] + (idx + 1 >= nums.length ? 0 : nums[idx + 1]) + dfs(nums, idx + 2, false, dp),
                nums[idx] + dfs(nums, idx + 1, false, dp)) : Math.min(
                dfs(nums, idx + 2, true, dp),
                dfs(nums, idx + 1, true, dp));


        return dp[idx][aMove ? 0 : 1] = total;
    }


    public static class FasterScanner {
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = System.in.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public String nextLine() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        public long[] nextLongArray(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }
            return arr;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }
    }
}
