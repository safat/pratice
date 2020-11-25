package codeforces.D1392;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
            long k = fs.nextLong();

            long[] nums = new long[n];

            for (int i = 0; i < n; i++) {
                nums[i] = fs.nextLong();
            }

            long max = findMax(nums);
            long[] numsOdd = new long[n];

            for (int j = 0; j < nums.length; j++) {
                numsOdd[j] = max - nums[j];
            }

            max = findMax(numsOdd);
            long[] numsEven = new long[n];

            for (int j = 0; j < nums.length; j++) {
                numsEven[j] = max - numsOdd[j];
            }

            if (k % 2 == 0) {
                StringBuilder tmpResult = new StringBuilder();

                for (long even : numsEven) {

                    tmpResult.append(even).append(" ");
                }

                result.append(tmpResult.substring(0, tmpResult.length())).append("\n");
            } else {
                StringBuilder tmpResult = new StringBuilder();

                for (long odd : numsOdd) {

                    tmpResult.append(odd).append(" ");
                }

                result.append(tmpResult.substring(0, tmpResult.length())).append("\n");
            }
        }

        System.out.print(result);
    }

    private static long findMax(long[] nums) {
        long max = Long.MIN_VALUE;

        for (long n : nums) {
            max = Math.max(n, max);
        }

        return max;
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
