//package codeforces.D535;

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

        int[] numbers = new int[n];
        int[] freq = new int[100000];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = fs.nextInt();

            freq[numbers[i]]++;
        }

        Arrays.sort(numbers);

        int a = numbers[numbers.length - 1];
        int b = 1;

        for (int i = numbers.length - 2; i >= 0; i--) {
            if (freq[numbers[i]] > 1 || a % numbers[i] != 0) {
                b = numbers[i];
                break;
            }
        }

        System.out.println(a + " " + b);
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

