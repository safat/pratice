package codeforces.D1401;

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

public class B {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        int testCases = fs.nextInt();
        StringBuilder output = new StringBuilder();

        for (int t = 0; t < testCases; t++) {

            int x1 = fs.nextInt(); // 0
            int y1 = fs.nextInt(); // 1
            int z1 = fs.nextInt(); // 2

            int x2 = fs.nextInt(); // 0
            int y2 = fs.nextInt(); // 1
            int z2 = fs.nextInt(); // 2

            long total = 0;

            if (z1 > 0 && y2 > 0) {
                if (z1 >= y2) {
                    total += (y2 * 2);

                    z1 -= y2;
                    y2 = 0;
                } else {
                    total += (z1 * 2);

                    y2 -= z1;
                    z1 = 0;
                }
            }

            if (z2 > 0 && x1 > 0) {
                if (z2 >= x1) {
                    z2 -= x1;
                    x1 = 0;
                } else {
                    z2 = 0;
                    x1 -= z2;
                }
            }

            if (z2 > 0 && z1 > 0) {
                if (z2 >= z1) {
                    z2 -= z1;
                    z1 = 0;
                } else {
                    z2 = 0;
                    z1 -= z2;
                }
            }

            if (y1 > 0 && z2 > 0) {
                if (y1 >= z2) {
                    total -= (z2 * 2);

                    y1 -= z2;
                    z2 = 0;

                } else {
                    total -= (y1 * 2);

                    y1 = 0;
                    z2 -= y1;
                }
            }


            output.append(total).append("\n");
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
