package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A601 {

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[][] busPaths = new int[n + 1][n + 1];
        int[][] trainPaths = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            busPaths[a][b] = -1;
            busPaths[b][a] = -1;

            trainPaths[a][b] = 1;
            trainPaths[b][a] = 1;
        }

        int shortestPath = -1;

        if (busPaths[1][n] == 0) {
            shortestPath = bfs(trainPaths, true);
        } else if (trainPaths[1][n] == 1) {
            shortestPath = bfs(busPaths, false);
        }

        System.out.println(shortestPath == Integer.MAX_VALUE ? -1 : shortestPath);
    }

    private static int bfs(int[][] paths, boolean isTrain) {
        int setFlag = isTrain ? 1 : 0;

        int src = 1;
        int n = paths.length - 1;

        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);

        boolean[] visited = new boolean[n + 1];

        Queue<Integer> nodes = new LinkedList<>();
        visited[src] = true;
        nodes.add(src);
        d[src] = 0;

        while (!nodes.isEmpty()) {
            int cNode = nodes.poll();
            visited[cNode] = true;

            for (int i = 1; i < paths.length; i++) {
                if (paths[cNode][i] == setFlag && !visited[i]) {
                    if (d[i] > d[cNode] + 1) {
                        nodes.add(i);
                        d[i] = d[cNode] + 1;
                    }
                }
            }
        }

        return d[n];
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
