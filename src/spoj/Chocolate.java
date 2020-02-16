package spoj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-01-20
 */

public class Chocolate {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);

        // col x row
        int m = fs.nextInt();
        int n = fs.nextInt();

        ArrayList<Cut> cutList = new ArrayList<>();

        for (int i = 0; i < m - 1; i++) {
            cutList.add(new Cut(fs.nextInt(), Cut.CutType.COLUMN));
        }

        for (int i = 0; i < n - 1; i++) {
            cutList.add(new Cut(fs.nextInt(), Cut.CutType.ROW));
        }

        cutList.sort((cut1, cut2) -> cut2.value - cut1.value);


        int rowCutCount = 1;
        int colCutCount = 1;

        long total = 0;

        for (Cut current : cutList) {
            if (current.cutType == Cut.CutType.COLUMN) {
                total += (long) ((current.value) * rowCutCount);

                colCutCount++;
            } else {
                total += (long) ((current.value) * colCutCount);

                rowCutCount++;
            }

        }

        System.out.println(total);
    }

    private static class Cut {
        enum CutType {
            ROW,
            COLUMN;
        }

        int value;
        CutType cutType;

        public Cut(int value, CutType cutType) {
            this.value = value;
            this.cutType = cutType;
        }
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
