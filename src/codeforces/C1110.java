package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class C1110 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(1, 0);
        cache.put(3, 1);
        cache.put(7, 1);
        cache.put(15, 5);
        cache.put(31, 1);
        cache.put(63, 21);
        cache.put(127, 1);
        cache.put(255, 85);
        cache.put(511, 73);
        cache.put(1023, 341);
        cache.put(2047, 89);
        cache.put(4095, 1365);
        cache.put(8191, 1);
        cache.put(16383, 5461);
        cache.put(32767, 4681);
        cache.put(65535, 21845);
        cache.put(131071, 1);
        cache.put(262143, 87381);
        cache.put(524287, 1);
        cache.put(1048575, 349525);
        cache.put(2097151, 299593);
        cache.put(4194303, 1398101);
        cache.put(8388607, 178481);
        cache.put(16777215, 5592405);
        cache.put(33554431, 1082401);

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int x = fs.nextInt();
            int result;

            if (cache.containsKey(x)) {
                result = cache.get(x);
            } else {
                int digits = (int) (Math.log10(x) / Math.log10(2)) + 1;
                result = 1;

                result <<= digits;
                result = result - 1;
            }

            output.append(result).append("\n");
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
