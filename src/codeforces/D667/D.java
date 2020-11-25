package codeforces.D667;


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

        int testCases = fs.nextInt();
        StringBuilder output = new StringBuilder();

        for (int t = 0; t < testCases; t++) {
            long n = fs.nextLong();
            long s = fs.nextLong();

            long moveCount = solve(n, s);
            output.append(moveCount).append("\n");
        }

        System.out.print(output);
    }

    private static long solve(long n, long s) {
        int cSum = digitSum(n);

        if (cSum <= s) {
            return 0;
        }

        long moveCount = 0;
        int digitPosition = 1;

        while (cSum > s) {

            if (n % ((long) Math.pow(10, digitPosition)) != 0) {
                long delta = (long) Math.pow(10, digitPosition) - (n % (long) Math.pow(10, digitPosition));
                moveCount += delta;
                n = n + delta;

                cSum = digitSum(n);
            }

            digitPosition++;
        }

        return moveCount;
    }

    private static int digitSum(long n) {
        int cSum = 0;

        long bkN = n;

        while (bkN != 0) {
            cSum += bkN % 10;
            bkN /= 10;
        }
        return cSum;
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
