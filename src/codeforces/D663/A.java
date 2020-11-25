package codeforces.D663;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-09
 */

public class A {
    public static void main(String[] args) {
        FastReader fs = new FastReader();
        int t = fs.nextInt();

        StringBuilder output = new StringBuilder();
        Map<Integer, String> cache = new HashMap<>();

        for (int i = 0; i < t; i++) {
            int n = fs.nextInt();

            if (cache.containsKey(n)) {
                output.append(cache.get(n)).append("\n");
            } else {
                StringBuilder tmp = new StringBuilder();

                for (int j = 1; j <= n; j++) {
                    tmp.append(j).append(" ");
                }

                cache.put(n, tmp.substring(0, tmp.length()));

                output.append(tmp).append("\n");
            }
        }

        System.out.print(output);

    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public static FastReader getFileReader(String fileName) throws FileNotFoundException {
            return new FastReader(new InputStreamReader(new FileInputStream(new File(fileName))));
        }

        public static FastReader getDefaultReader() throws FileNotFoundException {
            return new FastReader();
        }

        public FastReader(InputStreamReader inputStreamReader) {
            br = new BufferedReader(inputStreamReader);
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
