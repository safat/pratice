package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author muhossain
 */

public class C676 {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int k = fs.nextInt();

        String input = fs.nextLine();

        int left = 0;
        int right = 0;
        int max = 1;
        int cB = 0;

        // try to change b into a within threshold
        while (left < input.length() && right < input.length()) {
            if (cB <= k) {
                if (input.charAt(right) == 'b') {
                    cB++;
                }

                right++;
            } else {
                if (input.charAt(left) == 'b') {
                    cB--;
                }

                left++;
            }

            if (cB <= k) {
                max = Math.max(max, right - left);
            }
        }


        left = 0;
        right = 0;
        int cA = 0;

        // try to change a into b within threshold
        while (left < input.length() && right < input.length()) {
            if (cA <= k) {
                if (input.charAt(right) == 'a') {
                    cA++;
                }

                right++;
            } else {
                if (input.charAt(left) == 'a') {
                    cA--;
                }

                left++;
            }

            if (cA <= k) {
                max = Math.max(max, right - left);
            }
        }

        System.out.println(max);
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
