package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

        List<Index> S = new ArrayList<>();
        List<Index> T = new ArrayList<>();

        boolean[][] visitedS = new boolean[n + 1][n + 1];
        boolean[][] visitedT = new boolean[n + 1][n + 1];

        findAccessibleNodes(grid, visitedS, S, r1, c1);
        findAccessibleNodes(grid, visitedT, T, r2, c2);

        int distance = 0;

        if (!equalsX(S, T)) {
            distance = Integer.MAX_VALUE;

            for (int i = 0; i < S.size(); i++) {
                for (int j = 0; j < T.size(); j++) {
                    int tmpDistance = (int) (Math.pow(S.get(i).row - T.get(j).row, 2) + Math.pow(S.get(i).col - T.get(j).col, 2));
                    distance = Math.min(tmpDistance, distance);
                }
            }
        }

        System.out.println(distance);
    }

    private static boolean equalsX(List<Index> s, List<Index> t) {
        if (s.size() != t.size()) {
            return false;
        }

        for (int i = 0; i < s.size(); i++) {
            if (!s.get(i).equals(t.get(i))) {
                return false;
            }
        }

        return true;
    }


    private static void findAccessibleNodes(int[][] grid, boolean[][] visited, List<Index> nodes, int r1, int c1) {
        if (r1 <= 0 || r1 >= grid.length ||
                c1 <= 0 || c1 >= grid.length ||
                grid[r1][c1] == 1 ||
                visited[r1][c1]) {
            return;
        }

        nodes.add(new Index(r1, c1));
        visited[r1][c1] = true;

        findAccessibleNodes(grid, visited, nodes, r1, c1 + 1); // r
        findAccessibleNodes(grid, visited, nodes, r1, c1 - 1); // l
        findAccessibleNodes(grid, visited, nodes, r1 - 1, c1); // u
        findAccessibleNodes(grid, visited, nodes, r1 + 1, c1); // d
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

    private static class Index implements Comparable<Index> {
        int row;
        int col;

        public Index(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Index index = (Index) o;
            return row == index.row &&
                    col == index.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public String toString() {
            return "Index{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }

        @Override
        public int compareTo(Index o) {
            return this.row == o.row ? this.col - o.col : this.row - o.row;
        }
    }
}
