package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class E1_1108 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();

        int[] numbers = new int[n + 1];
        int[][] pairs = new int[m + 1][2];

        for (int i = 1; i <= n; i++) {
            numbers[i] = fs.nextInt();
        }

        for (int i = 1; i <= m; i++) {
            pairs[i][0] = fs.nextInt();
            pairs[i][1] = fs.nextInt();
        }

        int[] bkNumbers = new int[n + 1];

        System.arraycopy(numbers, 0, bkNumbers, 0, bkNumbers.length);

        bkNumbers[0] = Integer.MIN_VALUE;
        Arrays.sort(bkNumbers);

        StringBuilder result = new StringBuilder();
        long maxDiff = bkNumbers[bkNumbers.length - 1] - bkNumbers[1];
        int pairCount = 0;

        for (int i = 1; i <= n; i++) {
            System.arraycopy(numbers, 0, bkNumbers, 0, bkNumbers.length);
            int tmpMax = bkNumbers[i];
            int tmpPairCount = 0;

            long tmpDiff;
            StringBuilder tmpResult = new StringBuilder();

            long tmpMin = Integer.MAX_VALUE;

            for (int j = 1; j <= m; j++) {

                if (i < pairs[j][0] || i > pairs[j][1]) {

                    for (int k = pairs[j][0]; k <= pairs[j][1]; k++) {
                        bkNumbers[k]--;
                        tmpMin = Math.min(tmpMin, bkNumbers[k]);
                    }

                    tmpResult.append(j).append(" ");
                    tmpPairCount++;
                }
            }

            tmpDiff = tmpMax - tmpMin;

            if (tmpDiff > maxDiff) {
                maxDiff = tmpDiff;
                result = tmpResult;
                pairCount = tmpPairCount;
            }
        }

        System.out.println(maxDiff + "\n" + pairCount + "\n" + result.substring(0, result.length()));
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
