//package codeforces.D1399;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-14
 */

public class D2 {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        int testCases = fs.nextInt();
        StringBuilder output = new StringBuilder();

        for (int t = 0; t < testCases; t++) {
            int n = fs.nextInt();

            String input = fs.nextLine();

            StringBuilder result = new StringBuilder();

            int totalPile = 0;

            Deque<Integer> zeroQueue = new ArrayDeque<>();
            Deque<Integer> oneQueue = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {
                if (input.charAt(i) == '0') {
                    if (zeroQueue.isEmpty()) {
                        totalPile++;
                        result.append(totalPile).append(" ");
                        oneQueue.addLast(totalPile);
                    } else {
                        int zQ = zeroQueue.pollFirst();
                        result.append(zQ).append(" ");
                        oneQueue.addLast(zQ);
                    }
                } else {
                    if (oneQueue.isEmpty()) {
                        totalPile++;
                        result.append(totalPile).append(" ");
                        zeroQueue.addLast(totalPile);
                    } else {
                        int oQ = oneQueue.pollFirst();
                        result.append(oQ).append(" ");
                        zeroQueue.addLast(oQ);
                    }
                }
            }


            output.append(totalPile).append("\n");
            output.append(result.substring(0, result.length())).append("\n");
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
