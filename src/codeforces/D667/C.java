package codeforces.D667;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-14
 */

public class C {

    public static void main(String[] args) {
        FastReader fs = new FastReader();
        int testCases = fs.nextInt();

        StringBuilder output = new StringBuilder();

        for (int t = 0; t < testCases; t++) {
            int n = fs.nextInt();
            int x = fs.nextInt();
            int y = fs.nextInt();

            output.append(solve(n, x, y)).append("\n");

        }

        System.out.print(output);
    }

    private static String solve(int n, int x, int y) {
        long result = Long.MAX_VALUE;
        StringBuilder out = new StringBuilder();

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                long maxGap = y - x;

                for (int g = 1; g <= maxGap; g++) {
                    long[] num = new long[n + 1];
                    boolean possible = true;

                    num[i] = x;

                    for (int ii = i - 1; ii >= 1; ii--) {
                        num[ii] = num[ii + 1] - g;

                        if (num[ii] <= 0) {
                            possible = false;
                            break;

                        }
                    }

                    for (int jj = i + 1; jj <= n; jj++) {
                        num[jj] = num[jj - 1] + g;

                        if (jj == j && num[jj] != y) {
                            possible = false;
                            break;
                        }
                    }

                    if (possible) {

                        if (num[n] < result) {
                            out = new StringBuilder();

                            result = num[n];

                            for (int r = 1; r <= n; r++) {
                                out.append(num[r]).append(" ");
                            }
                        }
                    }
                }
            }
        }

        return out.toString().trim();
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
