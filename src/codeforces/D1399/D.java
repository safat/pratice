package codeforces.D1399;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-14
 */

public class D {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        int testCases = fs.nextInt();
        StringBuilder output = new StringBuilder();

        for (int t = 0; t < testCases; t++) {
            int n = fs.nextInt();

            int[] weight = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                weight[i] = fs.nextInt();
            }

            int[][] mat = new int[n + 1][n + 1];

            Set<Integer> uniqueWeights = new HashSet<>();

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) {
                        continue;
                    }

                    mat[i][j] = weight[i] + weight[j];
                    uniqueWeights.add(mat[i][j]);
                }
            }

            int maxTeam = 0;

            for (int w : uniqueWeights) {
                boolean[] visited = new boolean[n + 1];

                int count = 0;

                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {

                        if (!visited[i] && !visited[j] && mat[i][j] == w) {
                            count++;
                            visited[i] = true;
                            visited[j] = true;
                            break;
                        }
                    }
                }

                maxTeam = Math.max(maxTeam, count);
            }

            output.append(maxTeam).append("\n");
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
