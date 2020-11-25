package shopee;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-06-27
 */

public class MainX {

    Map<String, Integer[]> prefixCache = new HashMap<>();

    public static void main(String[] args) {
        new MainX().solve();
    }

    public void solve() {
        FastScanner fs = new FastScanner(System.in);
        int T = fs.nextInt();

        StringBuilder output = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            output.append("Case ").append(t).append(":").append("\n");

            int db = fs.nextInt();
            int q = fs.nextInt();


            List<String> dbs = new ArrayList<>();

            for (int i = 0; i < db; i++) {
                dbs.add(fs.nextLine());
            }

            List<String> pat = new ArrayList<>();

            for (int i = 0; i < q; i++) {
                pat.add(fs.nextLine());
            }

            for (int i = 0; i < pat.size(); i++) {
                long result = 0;

                for (int j = 0; j < dbs.size(); j++) {
                    result += search(pat.get(i), dbs.get(j));
                }

                output.append(result).append("\n");
            }
        }

        System.out.print(output);
    }

    int search(String pat, String txt) {
        int m = pat.length();
        int n = txt.length();

        int j = 0;
        Integer prefix[] = calculatePrefixArray(pat, m);

        int count = 0;

        int i = 0;
        while (i < n) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }

            if (j == m) {
                if (i == txt.length() || txt.charAt(i) == ' ') {
                    count++;
                }

                j = prefix[j - 1];

            } else if (i < n && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0)
                    j = prefix[j - 1];
                else
                    i = i + 1;
            }
        }

        return count;
    }

    Integer[] calculatePrefixArray(String pat, int M) {
        if (prefixCache.containsKey(pat)) {
            return prefixCache.get(pat);
        }

        Integer[] prefix = new Integer[M];

        int len = 0;
        int i = 1;
        prefix[0] = 0;

        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                prefix[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = prefix[len - 1];
                } else {
                    prefix[i] = len;
                    i++;
                }
            }
        }

        prefixCache.put(pat, prefix);

        return prefix;
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
