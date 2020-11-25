package codeforces.D1392;

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

public class C {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        int testCase = fs.nextInt();

        StringBuilder result = new StringBuilder();

        for (int t = 0; t < testCase; t++) {
            int n = fs.nextInt();

            long[] nums = new long[n];

            for (int i = 0; i < n; i++) {
                nums[i] = fs.nextLong();
            }

            long operation = 0;


            for (int i = 0; i < nums.length - 1; i++) {
                operation += Math.max(nums[i] - nums[i + 1], 0);
            }


            result.append(operation).append("\n");
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
