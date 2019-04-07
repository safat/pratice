package codeforces.AAA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class B {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        List<Integer> sweets = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int swt = fs.nextInt();
            sweets.add(swt);
        }

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int maxPair = 0;

        for (int i = 0; i < sweets.size(); i++) {
            for (int j = i + 1; j < sweets.size(); j++) {
                Integer freq = frequencyMap.get(sweets.get(i) + sweets.get(j));

                if (freq == null) {
                    freq = 0;
                }

                freq++;

                frequencyMap.put(sweets.get(i) + sweets.get(j), freq);

                maxPair = Math.max(freq, maxPair);
            }
        }

        System.out.println(maxPair);
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
