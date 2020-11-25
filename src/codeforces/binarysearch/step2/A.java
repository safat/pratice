package codeforces.binarysearch.step2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-14
 */

public class A {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        long x = fs.nextLong();
        long y = fs.nextLong();
        long n = fs.nextLong();

        long start = 1;
        long end = (long) Math.pow(10, 18);
        long result = 0;

        while (start <= end) {
            long mid = (start + end) / 2L;

            if ((BigDecimal.valueOf(mid / x).multiply(BigDecimal.valueOf(mid / y))).compareTo(BigDecimal.valueOf(n)) >= 0) {
                end = mid - 1;
                result = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(result);
    }

    private static boolean binarySearch(int[] nums, int num) {

        return binarySearchUtil(nums, 0, nums.length - 1, num);
    }

    private static boolean binarySearchUtil(int[] nums, int start, int end, int num) {

        while (start <= end) {
            int mid = (start + end) / 2;

            if (nums[mid] == num) {
                return true;
            }

            if (num < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return false;
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
