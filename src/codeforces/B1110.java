//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B1110 {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt(); // length of the stick
        int k = fs.nextInt();

        int[] segments = new int[n];

        List<Integer> nonBrokenSegments = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            segments[i] = fs.nextInt();

            if (i > 0) {
                nonBrokenSegments.add(segments[i] - segments[i - 1] - 1);
            }
        }

        Collections.sort(nonBrokenSegments);

        int totalCoveredLength = n;

        for (int i = 0; i < n - k; i++) {
            totalCoveredLength += nonBrokenSegments.get(i);
        }

        System.out.println(totalCoveredLength);
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
