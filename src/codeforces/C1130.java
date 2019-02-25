package codeforces;

import com.sun.org.apache.bcel.internal.generic.FASTORE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C1130 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();

        int r1 = fs.nextInt();
        int c1 = fs.nextInt();

        int r2 = fs.nextInt();
        int c2 = fs.nextInt();

        int[][] grid = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String input = fs.nextLine();

            for (int j = 1; j <= n; j++) {
                grid[i][j] = Integer.parseInt(input.charAt(j - 1) + "");
            }
        }

        boolean[][] visited = new boolean[n + 1][n + 1];
//        visited[r1][c1] = true;

        int result = findMinWaterDistance(grid, r1, c1, r2, c2, visited, 0, -1, -1);

        System.out.println(result);
    }

    private static int findMinWaterDistance(int[][] grid, int r1, int c1, int r2, int c2, boolean[][] visited, int distance, int wr1, int wc1) {
        if (r1 >= grid.length || r1 <= 0 || c1 >= grid.length || c1 <= 0) {
            return Integer.MAX_VALUE;
        }

        if (visited[r1][c1]) {
            return Integer.MAX_VALUE;
        }

        if (r1 == r2 && c1 == c2) {
            return distance;
        }

        if (grid[r1][c1] == 1) {
            if (distance > 0) {
                return Integer.MAX_VALUE;
            }

            if (wr1 == -1) {
                wr1 = r1;
                wc1 = c1;
            }
        } else {

            if (wr1 != -1) {
                distance = (int) (Math.pow(wr1 - r1 + 1, 2) + Math.pow(wc1 - c1 + 1, 2));
            }
        }

        visited[r1][c1] = true;

        return minx(
                findMinWaterDistance(grid, r1, c1 + 1, r2, c2, visited, distance, wr1, wc1), // right
                findMinWaterDistance(grid, r1, c1 - 1, r2, c2, visited, distance, wr1, wc1), // left
                findMinWaterDistance(grid, r1 - 1, c1, r2, c2, visited, distance, wr1, wc1), // up
                findMinWaterDistance(grid, r1 + 1, c1, r2, c2, visited, distance, wr1, wc1) // down
        );
    }

    private static int minx(int... items) {
        Arrays.sort(items);
        return items[0];
    }

    static class FastScanner {
        private BufferedReader reader = null;
        private StringTokenizer tokenizer = null;

        public FastScanner(InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        public String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
