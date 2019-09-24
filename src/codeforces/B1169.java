package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B1169 {

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();

        List<Pair> pairList = new ArrayList<>();

        int b1 = fs.nextInt();
        int b2 = fs.nextInt();

        for (int i = 1; i < m; i++) {
            pairList.add(new Pair(fs.nextInt(), fs.nextInt()));
        }


        boolean result = solve(b1, pairList) || solve(b2, pairList);

        System.out.println(result ? "YES" : "NO");
    }

    private static boolean solve(int pivot, List<Pair> pairList) {
        boolean solutionFound = true;
        int y1 = 0, y2 = 0;

        for (Pair pair : pairList) {
            if (pair.x != pivot && pair.y != pivot) {
                solutionFound = false;

                y1 = pair.x;
                y2 = pair.y;
                break;
            }
        }

        // try with pivot y1
        // try with pivot y2

        if (solutionFound) {
            return true;
        }

        Pair p1 = new Pair(pivot, y1);
        Pair p2 = new Pair(pivot, y2);

        return pairList.stream().allMatch(p -> p.x == p1.x || p.y == p1.y || p.x == p1.y || p.y == p1.x)
                || pairList.stream().allMatch(p -> p.x == p2.x || p.y == p2.y || p.x == p2.y || p.y == p2.x);

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
