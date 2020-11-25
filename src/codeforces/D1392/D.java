package codeforces.D1392;

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

public class D {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        int testCase = fs.nextInt();

        StringBuilder result = new StringBuilder();

        for (int t = 0; t < testCase; t++) {
            int n = fs.nextInt();
            String input = fs.nextLine();

            int cont = 1;
            int currentResult = 0;

            for (int i = 1; i < input.length(); i++) {
                if (input.charAt(i) == input.charAt(i - 1)) {
                    cont++;
                } else {
                    currentResult += (cont / 3);
                    cont = 1;
                }
            }

            currentResult += cont / 3;

            char lastChar = input.charAt(input.length() - 1);

            int i = 0;

            if (cont == n) {
                currentResult = (int) Math.ceil(cont / 3.0);
            } else {
                int tmpCnt = cont;

                while (i < input.length() && input.charAt(i) == lastChar) {
                    cont++;
                    i++;
                }

                if (cont > tmpCnt && cont >= 3) {
                    currentResult += cont / 3.0;
                    currentResult -= (cont - tmpCnt) / 3;
                    currentResult -= (tmpCnt) / 3;
                }
            }

            result.append(currentResult).append("\n");
        }

        System.out.print(result);
    }

    private static long findMax(long[] nums) {
        long max = Long.MIN_VALUE;

        for (long n : nums) {
            max = Math.max(n, max);
        }

        return max;
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
