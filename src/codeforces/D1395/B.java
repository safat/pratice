package codeforces.D1395;

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

public class B {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        StringBuilder output = new StringBuilder();

        int n = fs.nextInt();
        int m = fs.nextInt();
        int sx = fs.nextInt() - 1;
        int sy = fs.nextInt() - 1;

        boolean[][] visited = new boolean[n + 1][m + 1];

        for (int i = sx; i >= 0; i--) {
            visited[i][sy] = true;

            output.append(i + 1).append(" ").append(sy + 1).append("\n");
        }

        for (int j = sy; j >= 0; j--) {
            if (visited[0][j]) {
                continue;
            }

            visited[0][j] = true;
            output.append(1).append(" ").append(j + 1).append("\n");
        }

        boolean even;

        for (int i = 0; i < n; i++) {
            even = i % 2 == 0;

            for (int j = 0; j < m; j++) {
                if ((even && visited[i][j]) || (!even && visited[i][m - j - 1])) {
                    continue;
                }

                if (even) {
                    visited[i][j] = true;
                } else {
                    visited[i][m - j - 1] = true;
                }

                output.append(i + 1).append(" ").append(even ? j + 1 : (m - j)).append("\n");
            }
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
