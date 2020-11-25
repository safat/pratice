//package codeforces.binarysearch;

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

public class D {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        StringBuilder output = new StringBuilder();

        int n = fs.nextInt();
        int[] nums = new int[n];


        for (int i = 0; i < n; i++) {
            nums[i] = fs.nextInt();
        }

        int q = fs.nextInt();
        int[] qLeft = new int[q];
        int[] qRight = new int[q];

        for (int i = 0; i < q; i++) {
            qLeft[i] = fs.nextInt();
            qRight[i] = fs.nextInt();
        }

        Arrays.sort(nums);

        for (int i = 0; i < q; i++) {
            int left = qLeft[i];
            int right = qRight[i];

            int leftMostIndex = binarySearchUtilLeft(nums, 0, nums.length - 1, left);
            int rightMostIndex = binarySearchUtilRight(nums, 0, nums.length - 1, right);

            output.append(Math.max(0, rightMostIndex - leftMostIndex + 1)).append(" ");
        }

        System.out.println(output.substring(0, output.length()));
    }


    private static int binarySearchUtilLeft(int[] nums, int start, int end, int num) {
        int mid = 0;

        while (start <= end) {
            mid = (start + end) / 2;

            if (num <= nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        if (nums[mid] >= num) {
            return mid;
        } else if (nums[mid] < num) {
            return mid + 1;
        }

        return 0;
    }

    private static int binarySearchUtilRight(int[] nums, int start, int end, int num) {
        int mid = 0;

        while (start <= end) {
            mid = (start + end) / 2;

            if (num < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        if (nums[mid] <= num) {
            return mid;
        } else if (nums[mid] > num) {
            return mid - 1;
        }

        return 0;
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
