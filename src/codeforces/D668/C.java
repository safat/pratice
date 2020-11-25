package codeforces.D668;

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

        int testCases = fs.nextInt();
        StringBuilder output = new StringBuilder();

        for (int t = 0; t < testCases; t++) {
            int n = fs.nextInt();
            int k = fs.nextInt();

            String input = fs.nextLine();

            boolean yes = solve(n, k, input);

            if (yes) {
                output.append("YES\n");
            } else {
                output.append("NO\n");
            }
        }

        System.out.print(output);
    }

    private static boolean solve(int n, int k, String input) {
        char[] inputBits = input.toCharArray();

        for (int i = 0; i < k; i++) {
            for (int j = i + k; j < n; j += k) {
                if (inputBits[i] == '?') {
                    continue;
                }

                if (inputBits[j] == '?') {
                    inputBits[j] = inputBits[i];
                } else {
                    if (inputBits[i] != inputBits[j]) {
                        return false;
                    }
                }
            }
        }

        for (int i = 0; i < k; i++) {
            if (inputBits[i] != '?') {
                continue;
            }

            char replacement = '?';

            for (int j = i + k; j < n; j += k) {
                if (inputBits[j] != '?') {
                    replacement = inputBits[j];
                    break;
                }
            }

            inputBits[i] = replacement;

            for (int j = i + k; j < n; j += k) {
                if (inputBits[j] == '?') {
                    inputBits[j] = replacement;
                } else if (inputBits[j] != inputBits[i]) {
                    return false;
                }
            }

        }

        int zeroCount = 0;
        int oneCount = 0;

        for (int i = 0; i < k; i++) {
            if (inputBits[i] == '0') {
                zeroCount++;
            }

            if (inputBits[i] == '1') {
                oneCount++;
            }
        }

        if (zeroCount > k / 2 || oneCount > k / 2) {
            return false;
        }

        return true;
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