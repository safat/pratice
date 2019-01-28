package codeforces.D535;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);

        int q = fs.nextInt();

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < q; i++) {
            int l1 = fs.nextInt();
            int r1 = fs.nextInt();
            int l2 = fs.nextInt();
            int r2 = fs.nextInt();

            output.append(l1);

            for (int b = l2; b <= r2; b++) {
                if (b != l1) {
                    output.append(" ").append(b).append("\n");
                    break;
                }
            }
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
