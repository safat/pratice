package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C1131 {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        int[] numbers = new int[n];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = fs.nextInt();
        }

        Arrays.sort(numbers);

        StringBuilder result = new StringBuilder();

        int flag = 1;

        for (int i = numbers.length - 1; i >= 0; i--) {
            if (flag % 2 == 0) {
                result.append(numbers[i]).append(" ");
            } else {
                result = new StringBuilder().append(numbers[i]).append(" ").append(result);
            }

            flag++;
        }

        System.out.println(result.substring(0, result.lastIndexOf(" ")));
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
