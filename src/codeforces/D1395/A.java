package codeforces.D1395;

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

public class A {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        int testCases = fs.nextInt();
        StringBuilder output = new StringBuilder();

        for (int t = 0; t < testCases; t++) {
            int r = fs.nextInt();
            int g = fs.nextInt();
            int b = fs.nextInt();
            int w = fs.nextInt();

            boolean can = solve(r, g, b, w);

            output.append(can ? "Yes" : "No").append("\n");
        }

        System.out.print(output);
    }

    private static boolean solve(int r, int g, int b, int w) {
        int oddCount = oddCount(r, g, b, w);

        if (oddCount <= 1) {
            return true;
        }

        if (r >= 1 && g >= 1 && b >= 1) {
            r -= 1;
            g -= 1;
            b -= 1;
            w += 3;

            oddCount = oddCount(r, g, b, w);

            if (oddCount <= 1) {
                return true;
            }
        }

        return false;
    }

    private static int oddCount(int r, int g, int b, int w) {
        int oddCount = 0;

        if (r % 2 == 1) {
            oddCount++;
        }

        if (g % 2 == 1) {
            oddCount++;
        }

        if (b % 2 == 1) {
            oddCount++;
        }

        if (w % 2 == 1) {
            oddCount++;
        }

        return oddCount;
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
