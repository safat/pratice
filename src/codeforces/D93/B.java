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

public class B {
    public static void main(String[] args) {
        FastReader fs = new FastReader();
        int t = fs.nextInt();

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < t; i++) {
            String input = fs.nextLine();

            int result = solve(input);

            output.append(result).append("\n");
        }

        System.out.print(output);
    }

    private static int solve(String input) {
        List<Integer> result = new ArrayList<>();

        int consq1 = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '1') {
                consq1 += 1;
            } else {
                result.add(consq1);
                consq1 = 0;
            }
        }

        if (input.charAt(input.length() - 1) == '1') {
            result.add(consq1);
        }

        result.sort(Collections.reverseOrder());
        int sum = 0;

        for (int i = 0; i < result.size(); ) {
            sum += result.get(i);
            i += 2;
        }

        return sum;
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
