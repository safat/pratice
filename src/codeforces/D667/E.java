package codeforces.D667;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-14
 */

public class E {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        int testCases = fs.nextInt();
        StringBuilder output = new StringBuilder();


        for (int t = 0; t < testCases; t++) {
            int n = fs.nextInt();
            int k = fs.nextInt();

            List<Integer> xItems = new ArrayList<>();

            for (int i = 1; i <= n; i++) {
                xItems.add(fs.nextInt());
            }

            for (int i = 0; i < n; i++) {
                fs.nextInt();
            }

            Collections.sort(xItems);

            int[] left = new int[n];
            int[] right = new int[n];

            int end = 0;

            for (int i = 0; i < n; i++) {

                while (end < xItems.size() && xItems.get(i) + k >= xItems.get(end)) {
                    xItems.size();
                    end++;
                }

                end--;

                right[i] = Math.max(1, end - i + 1);
            }

            end = n - 1;

            for (int j = n - 1; j >= 0; j--) {

                while (end >= 0 && xItems.get(j) - k <= xItems.get(end)) {
                    end--;
                }

                end++;

                left[j] = Math.max(j - end + 1, 1);
            }

            for (int i = 1; i < n; i++) {
                left[i] = Math.max(left[i], left[i - 1]);
            }

            for (int j = n - 2; j >= 0; j--) {
                right[j] = Math.max(right[j], right[j + 1]);
            }

            int maxCov = 1;

            for (int i = 0; i < n - 1; i++) {
                maxCov = Math.max(maxCov, left[i] + right[i + 1]);
            }

            output.append(maxCov).append("\n");
        }

        System.out.print(output);
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
