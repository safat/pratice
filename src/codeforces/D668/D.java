package codeforces.D668;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-14
 */

public class D {

    static int maxDistance = -1;
    static int distantNode = -1;

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        int testCases = fs.nextInt();
        StringBuilder output = new StringBuilder();

        for (int t = 0; t < testCases; t++) {
            int n = fs.nextInt();
            int pA = fs.nextInt();
            int pB = fs.nextInt();
            int dA = fs.nextInt();
            int dB = fs.nextInt();

            Map<Integer, List<Integer>> adjacencies = new HashMap<>();

            for (int i = 0; i < n - 1; i++) {
                int a = fs.nextInt();
                int b = fs.nextInt();

                adjacencies.putIfAbsent(a, new ArrayList<>());
                adjacencies.putIfAbsent(b, new ArrayList<>());

                adjacencies.get(a).add(b);
                adjacencies.get(b).add(a);
            }

            if (dA >= dB) {
                output.append("Alice\n");
            } else {
                distantNode = -1;
                maxDistance = -1;

                int[] distances = new int[n + 1];

                boolean[] visited = new boolean[n + 1];

                dfs(1, distances, 0, adjacencies, visited);

                maxDistance = 0;
                distances = new int[n + 1];
                visited = new boolean[n + 1];

                dfs(distantNode, distances, 0, adjacencies, visited);

                if (Math.min(maxDistance, dB) <= dA * 2) {
                    output.append("Alice\n");
                } else {
                    // within limit

                    maxDistance = 0;
                    distances = new int[n + 1];
                    visited = new boolean[n + 1];

                    dfs(pA, distances, 0, adjacencies, visited);

                    if (distances[pB] <= dA) {
                        output.append("Alice\n");
                    } else {
                        output.append("Bob\n");
                    }
                }

            }
        }

        System.out.print(output);
    }

    private static void dfs(int Bobde, int[] distances, int distance, Map<Integer, List<Integer>> adjacencies, boolean[] visited) {
        visited[Bobde] = true;

        if (adjacencies.getOrDefault(Bobde, new ArrayList<>()).isEmpty()) {
            return;
        }

        for (int adj : adjacencies.get(Bobde)) {
            if (visited[adj]) {
                continue;
            }

            distances[adj] = distance + 1;

            if (distance + 1 >= maxDistance) {
                maxDistance = distance + 1;
                distantNode = adj;
            }

            dfs(adj, distances, distance + 1, adjacencies, visited);
        }
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
