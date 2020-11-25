package spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-03-14
 */

public class BYTESE2 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int t = fs.nextInt();

        StringBuilder output = new StringBuilder();

        for (int i = 1; i <= t; i++) {
            int n = fs.nextInt();

            int[] indexes = new int[10000001];

            int max = Integer.MIN_VALUE;

            for (int j = 0; j < n; j++) {
                int a = fs.nextInt();
                int b = fs.nextInt();

                max = Math.max(max, Math.max(a, b));

                indexes[a]++;
                indexes[b + 1]--;
            }

            int maxPersonCount = 0;
            int personCount = 0;

            for (int idx = 0; idx <= max; idx++) {
                personCount += indexes[idx];

                maxPersonCount = Math.max(personCount, maxPersonCount);
            }

            output.append(maxPersonCount).append("\n");
        }

        System.out.print(output);
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
