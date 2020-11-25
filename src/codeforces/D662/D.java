package codeforces.D662;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-08
 */

public class D {
    public static void main(String[] args) {
        FastReader fs = new FastReader();
        int n = fs.nextInt();
        int m = fs.nextInt();

        char[][] grid = new char[n + 2][m + 2];

        for (int i = 1; i <= n; i++) {
            String input = fs.nextLine();

            for (int j = 1; j <= m; j++) {
                grid[i][j] = input.charAt(j - 1);
            }
        }

        int[][] dpUpperLeft = new int[n + 1][m + 1];
        int[][] dpUpperRight = new int[n + 1][m + 1];
        int[][] dpLowerLeft = new int[n + 1][m + 1];
        int[][] dpLowerRight = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (grid[i][j] == grid[i - 1][j] && grid[i][j] == grid[i][j - 1]) {
                    dpUpperLeft[i][j] = Math.min(dpUpperLeft[i - 1][j], dpUpperLeft[i][j - 1]) + 1;
                } else {
                    dpUpperLeft[i][j] = 1;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) {
                if (grid[i][j] == grid[i][j + 1] && grid[i][j] == grid[i - 1][j]) {
                    dpUpperRight[i][j] = Math.min(dpUpperRight[i][j + 1], dpUpperRight[i - 1][j]) + 1;
                } else {
                    dpUpperRight[i][j] = 1;
                }
            }
        }

        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= m; j++) {
                if (grid[i][j] == grid[i][j - 1] && grid[i][j] == grid[i + 1][j]) {
                    dpLowerLeft[i][j] = Math.min(dpLowerLeft[i][j - 1], dpLowerLeft[i + 1][j]) + 1;
                } else {
                    dpLowerLeft[i][j] = 1;
                }
            }
        }

        for (int i = n; i >= 1; i--) {
            for (int j = m; j >= 1; j--) {
                if (grid[i][j] == grid[i][j + 1] && grid[i][j] == grid[i + 1][j]) {
                    dpLowerRight[i][j] = Math.min(dpLowerRight[i][j + 1], dpLowerRight[i + 1][j]) + 1;
                } else {
                    dpLowerRight[i][j] = 1;
                }
            }
        }

        long result = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                result += (Math.min(
                        Math.min(dpUpperLeft[i][j], dpUpperRight[i][j]),
                        Math.min(dpLowerLeft[i][j], dpLowerRight[i][j])

                ));
            }
        }

        System.out.println(result);
    }

    private static class Index {
        int i, j;

        public Index(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private static boolean isWithinLimit(int i, int j, int delta, int n, int m) {
        return i + delta <= n && i - delta >= 1 && j + delta <= m && j - delta >= 1;

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