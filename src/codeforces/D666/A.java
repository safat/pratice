package codeforces.D666;//package codeforces.D666;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-14
 */

public class A {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        int testCases = fs.nextInt();
        StringBuilder output = new StringBuilder();

        for (int t = 0; t < testCases; t++) {
            int n = fs.nextInt();

            List<String> inputs = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                inputs.add(fs.nextLine());
            }

            Map<Character, Integer> characterCount = new HashMap<>();

            for (String input : inputs) {
                for (char inputChar : input.toCharArray()) {
                    characterCount.put(inputChar, characterCount.getOrDefault(inputChar, 0) + 1);
                }
            }

            boolean solve = true;

            for (Map.Entry<Character, Integer> entry : characterCount.entrySet()) {
                if (entry.getValue() % n != 0) {
                    solve = false;
                    break;
                }
            }

            output.append(solve ? "YES" : "NO").append("\n");
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
