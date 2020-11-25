package codeforces;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-07-14
 */

public class B489 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        List<Integer> boySkills = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            boySkills.add(fs.nextInt());
        }

        int m = fs.nextInt();
        List<Integer> girlSkills = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            girlSkills.add(fs.nextInt());
        }

        Collections.sort(boySkills);
        Collections.sort(girlSkills);

        int count = 0;
        int j = 0, i = 0;

        while (j < girlSkills.size() && i < boySkills.size()) {

            if (Math.abs(boySkills.get(i) - girlSkills.get(j)) <= 1) {
                count++;
                i++;
                j++;
            } else if (boySkills.get(i) < girlSkills.get(j)) {
                i++;
            } else {
                j++;
            }
        }

        System.out.println(count);
    }

    static class FastScanner {
        private BufferedReader reader = null;
        private StringTokenizer tokenizer = null;

        public FastScanner(InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        public String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}


