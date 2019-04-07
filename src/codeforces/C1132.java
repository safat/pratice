package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class C1132 {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int q = fs.nextInt();

        int[][] segments = new int[q][2];

        List<Segment> segmentList = new ArrayList<>();

        int[] p1 = new int[n + 2];

        for (int i = 0; i < q; i++) {
            segments[i][0] = fs.nextInt();
            segments[i][1] = fs.nextInt();

            segmentList.add(new Segment(i, segments[i][0], segments[i][1]));

            p1[segments[i][0]]++;
            p1[segments[i][1] + 1]--;
        }

        int c = 0;
        int p2[] = new int[n + 2];

        for (int i = 0; i < p1.length; i++) {
            c += p1[i];
            
            p2[i] = c;
        }

        int maxCovered = Integer.MIN_VALUE;

        for (int i = 0; i < segmentList.size() - 1; i++) {
            p1[segmentList.get(i).start]--;
            p1[segmentList.get(i).end + 1]++;



//            for (int j = i + 1; j < segmentList.size(); j++) {
//                int[] line = new int[n + 2];
//
//                for (int k = 0; k < segmentList.size(); k++) {
//                    if (k == i || k == j) {
//                        continue;
//                    }
//
//                    line[segmentList.get(k).start]++;
//                    line[segmentList.get(k).end + 1]--;
//                }
//
//                int uncovered = 0;
//                int c = 0;
//
//                for (int l = 1; l < line.length - 1; l++) {
//                    c += line[l];
//
//                    if (c <= 0) {
//                        uncovered++;
//                    }
//                }
//
//                maxCovered = Math.max(maxCovered, n - uncovered);
//            }
        }

        System.out.println(maxCovered);
    }


    private static class Segment implements Comparable<Segment> {
        int index;
        int start;
        int end;

        public Segment(int index, int start, int end) {
            this.index = index;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Segment o) {
            return (end - start) - (o.end - o.start);
        }
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
