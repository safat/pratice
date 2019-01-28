//package codeforces.D1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class C {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int k = fs.nextInt();

        int[] damages = new int[n];

        for (int i = 0; i < n; i++) {
            damages[i] = fs.nextInt();
        }

        String actionStr = fs.nextLine();
        BigInteger result = BigInteger.ZERO;


        for (int i = 1; i <= actionStr.length(); i++) {
            char last = actionStr.charAt(i - 1);
            List<Integer> cDamageList = new ArrayList<>();

            cDamageList.add(damages[i - 1]);

            while (i < actionStr.length() && actionStr.charAt(i) == last) {
                cDamageList.add(damages[i]);
                i++;
            }

            if (cDamageList.size() > k) {
                Collections.sort(cDamageList);
            }

            int actionProcessed = 0;

            for (int j = cDamageList.size() - 1; j >= 0 && actionProcessed < k; j--) {
                result = result.add(BigInteger.valueOf((long) cDamageList.get(j)));

                actionProcessed++;
            }
        }

        System.out.println(result);
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
