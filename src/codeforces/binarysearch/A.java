package codeforces.binarysearch;

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

public class A {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        StringBuilder output = new StringBuilder();

        int n = fs.nextInt();
        int q = fs.nextInt();

        int[] nums = new int[n];
        int[] queries = new int[q];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = fs.nextInt();
        }

        for (int i = 0; i < queries.length; i++) {
            queries[i] = fs.nextInt();
        }

        for (int query : queries) {
            boolean exist = binarySearch(nums, query);

            output.append(exist ? "YES\n" : "NO\n");
        }

        System.out.print(output);
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
