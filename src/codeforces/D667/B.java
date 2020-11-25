package codeforces.D667;

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

        int testCases = fs.nextInt();
        StringBuilder output = new StringBuilder();

        for (int t = 0; t < testCases; t++) {
            long a = fs.nextLong();
            long b = fs.nextLong();
            long x = fs.nextLong();
            long y = fs.nextLong();
            long n = fs.nextLong();

            long aBk = a;
            long bBk = b;

            long delta = 0;

            if (a - n >= x) {
                a = a - n;
            } else {
                delta = (n - a + x);
                a = x;
            }

            if (delta > 0) {
                if (b - delta >= y) {
                    b = b - delta;
                } else {
                    b = y;
                }

            }

            long result1 = a * b;

            a = aBk;
            b = bBk;

            long tmp = b;
            b = a;
            a = tmp;

            tmp = x;
            x = y;
            y = tmp;

            delta = 0;

            if (a - n >= x) {
                a = a - n;
            } else {
                delta = (n - a + x);
                a = x;
            }

            if (delta > 0) {
                if (b - delta >= y) {
                    b = b - delta;
                } else {
                    b = y;
                }

            }

            long result2 = a * b;

            output.append(Math.min(result1, result2)).append("\n");

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
