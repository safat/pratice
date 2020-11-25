package codeforces.D93;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-14
 */

public class A {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        int t = fs.nextInt();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int n = fs.nextInt();

            List<Integer> items = new ArrayList<>();

            for (int j = 1; j <= n; j++) {
                items.add(fs.nextInt());
            }

            int a = items.get(0);
            int b = items.get(1);
            int c = items.get(items.size() - 1);

            if (a + b > c) {
                result.append("-1\n");
            } else {
                result.append("1 2 ").append(items.size()).append("\n");
            }
        }

        System.out.print(result);
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
