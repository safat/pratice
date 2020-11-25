package codeforces.D1395;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-14
 */

public class C {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        int max = (int) Math.pow(2, 9);

        int n = fs.nextInt();
        int m = fs.nextInt();

        int a[] = new int[n];
        int b[] = new int[m];

        for (int i = 0; i < n; i++) {
            a[i] = fs.nextInt();
        }

        for (int j = 0; j < m; j++) {
            b[j] = fs.nextInt();
        }

        int result = 0;

        for (int r = 0; r < max; r++) {

            boolean f = true;

            for (int i = 0; i < n; i++) {
                boolean found = false;

                for (int j = 0; j < m; j++) {
                    if ((r | (a[i] & b[j])) == r) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    f = false;
                    break;
                }
            }

            if (f) {
                result = r;
                break;
            }
        }

        System.out.println(result);
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