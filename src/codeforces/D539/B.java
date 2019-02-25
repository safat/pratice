package codeforces.D539;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();

        int[] powers = new int[n];
        int result = 0;

        for (int i = 0; i < n; i++) {
            powers[i] = fs.nextInt();

            result += powers[i];
        }

        Arrays.sort(powers);

        int minResult = result;

        for (int i = 1; i < powers.length; i++) {
            int tmpResult;

            for (int j = 2; j <= Math.sqrt(powers[i]); j++) {
                if (powers[i] % j == 0) {
                    tmpResult = result - powers[0] - powers[i] + powers[i] / j + powers[0] * j;

                    minResult = Math.min(minResult, tmpResult);
                }
            }
        }

        System.out.println(minResult);
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
