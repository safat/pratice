package codeforces.D663;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-09
 */

public class B {

    public static void main(String[] args) {
        FastReader fs = new FastReader();
        int t = fs.nextInt();

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int n = fs.nextInt();
            int m = fs.nextInt();

            char[][] grid = new char[n + 1][m + 1];

            for (int r = 0; r < n; r++) {
                String input = fs.nextLine();

                for (int c = 0; c < input.length(); c++) {
                    grid[r][c] = input.charAt(c);
                }
            }

            // last row all R

            int count = 0;

            for (int k = 0; k < m - 1; k++) {
                if (grid[n - 1][k] != 'R') {
                    count++;
                }
            }

            // last col all D

            for (int k = 0; k < n - 1; k++) {
                if (grid[k][m - 1] != 'D') {
                    count++;
                }
            }

            output.append(count).append("\n");
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
