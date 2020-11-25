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

public class C {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        StringBuilder output = new StringBuilder();

        int n = fs.nextInt();
        int q = fs.nextInt();

        int[] nums = new int[n];
        int[] queries = new int[q];

        for (int i = 0; i < n; i++) {
            nums[i] = fs.nextInt();
        }

        for (int i = 0; i < queries.length; i++) {
            queries[i] = fs.nextInt();
        }

        for (int query : queries) {
            int closestToLeft = binarySearch(nums, query);
            output.append(closestToLeft).append("\n");
        }

        System.out.print(output);
    }

    private static int binarySearch(int[] nums, int num) {

        return binarySearchUtil(nums, 0, nums.length - 1, num);
    }

    private static int binarySearchUtil(int[] nums, int start, int end, int num) {
        int mid = 0;

        while (start <= end) {
            mid = (start + end) / 2;

            if (num <= nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        if (nums[mid] < num) {
            return mid + 2;
        } else if (nums[mid] >= num) {
            return mid + 1;
        }

        return 1;
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
