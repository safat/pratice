//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B289 {

    public static void main(String[] args) {
        FastScanner input = new FastScanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        int d = input.nextInt();

        int[] numbers = new int[n * m];
        boolean canBalance = true;
        int mod = -1;


        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = input.nextInt();

            if (mod == -1) {
                mod = numbers[i] % d;
            }

            if (mod != numbers[i] % d) {
                canBalance = false;
                break;
            }
        }

        if (!canBalance) {
            System.out.println(-1);
            System.exit(0);
        }

        Arrays.sort(numbers);

        int median = numbers[((numbers.length - 1) / 2)];

        long totalDistance = 0;

        for (int num : numbers) {
            totalDistance += Math.abs(median - num);
        }

        System.out.println(totalDistance / d);
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
