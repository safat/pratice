package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-07-14
 */

public class B602 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            data[i] = fs.nextInt();
        }

        int i = 1;
        int leftMin, leftMax;

        leftMin = data[0];
        leftMax = data[0];
        int leftPointer = 0;

        int maxDistance = 0;

        while (i < n) {
            if (Math.abs(leftMax - data[i]) >= 2 || Math.abs(leftMin - data[i]) >= 2) {
                leftPointer = i;

                leftMin = data[i];
                leftMax = data[i];

                while (Math.abs(leftMax - data[leftPointer]) < 2 && Math.abs(leftMin - data[leftPointer]) < 2) {
                    leftMin = Math.min(leftMin, data[leftPointer]);
                    leftMax = Math.max(leftMax, data[leftPointer]);

                    leftPointer--;
                }

                leftPointer++;

            } else {
                leftMin = Math.min(leftMin, data[i]);
                leftMax = Math.max(leftMax, data[i]);

                maxDistance = Math.max(maxDistance, i - leftPointer + 1);
            }

            i++;
        }

        System.out.println(maxDistance);
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

