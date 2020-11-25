package codeforces.D93;

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
 * @since 2020-08-14
 */

public class C {
    public static void main(String[] args) {
        FastReader fs = new FastReader();

        int testCase = fs.nextInt();

        StringBuilder result = new StringBuilder();

        for (int t = 0; t < testCase; t++) {
            fs.next();

            String input = fs.nextLine();

            long sum = 0;
            long count = 0;
            Map<Long, Long> freqMap = new HashMap<>();
            freqMap.put(0L, 1L);

            for (int i = 0; i < input.length(); i++) {
                int digit = input.charAt(i) - '0';

                if (digit == 0) {
                    sum--;
                } else {
                    sum += (digit - 1);
                }

                if (freqMap.containsKey(sum)) {
                    count += freqMap.get(sum);
                }

                freqMap.put(sum, freqMap.getOrDefault(sum, 0L) + 1);
            }

            result.append(count).append("\n");
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
