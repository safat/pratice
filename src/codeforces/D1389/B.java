package codeforces.D1389;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-14
 */

public class B {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        int testCase = fs.nextInt();

        StringBuilder result = new StringBuilder();

        for (int t = 0; t < testCase; t++) {
            int n = fs.nextInt();
            int k = fs.nextInt();
            int z = fs.nextInt();

            int[] nums = new int[n];

            for (int i = 0; i < nums.length; i++) {
                nums[i] = fs.nextInt();
            }

            int[][] dp = new int[n + 1][6];

            int maxSum = solve(nums, 0, n, k, 0, z, dp, 0);


            result.append(maxSum).append("\n");
        }

        System.out.print(result);
    }

    private static int solve(int[] nums, int currentPosition, int n, int totalMove,
                             int currentMoveCount, int leftMoveCount, int[][] dp, int leftMove) {


        if (currentPosition == n || currentMoveCount > totalMove) {
            return 0;
        }

        if (dp[currentPosition][leftMoveCount] > 0) {
            return dp[currentPosition][leftMoveCount];
        }

        int max = 0;

        if (currentPosition < n) {
            // move forward
            max = Math.max(max, nums[currentPosition] + solve(nums, currentPosition + 1, n, totalMove,
                    currentMoveCount + 1, leftMoveCount, dp, 0));

        }

        // go left

        if (leftMoveCount > 0 && currentPosition > 0) {
            max = Math.max(max, nums[currentPosition] + solve(nums, currentPosition - 1, n, totalMove,
                    currentMoveCount + 1, leftMoveCount - 1, dp, leftMove + 1));
        }

        return dp[currentPosition][leftMoveCount] = max;
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public static FastReader getFileReader(String fileName) throws FileNotFoundException {
            return new FastReader(new InputStreamReader(new FileInputStream(new File(fileName))));
        }

        public static FastReader getDefaultReader() throws FileNotFoundException {
            return new FastReader();
        }

        public FastReader(InputStreamReader inputStreamReader) {
            br = new BufferedReader(inputStreamReader);
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
