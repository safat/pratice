package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-02-11
 */

public class B639 {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int d = fs.nextInt();
        int h = fs.nextInt();


        if (d > 2 * h || (n >= 3 && d == 1)) {
            System.out.println("-1");
        } else {
            StringBuilder output = new StringBuilder();


            for (int i = 1; i <= h; i++) {
                output.append(i + " " + (i + 1)).append("\n");
            }

            int pNode = 1;
            int cNode = h + 2;

            for (int i = 1; i <= d - h; i++) {
                output.append(pNode + " " + cNode).append("\n");
                pNode = cNode;
                cNode++;
            }

            for (; cNode <= n; cNode++) {
                int root = (h == 1) ? 1 : 2;

                //trying if 4 2 1 is present in the judge test cases
//                int root = 2;

                output.append(root + " " + cNode).append("\n");
            }

            System.out.print(output);
        }
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
