package hackerearth;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-06-27
 */

public class BishuAndSoldiers {

    public void solve() {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        int[] soldiers = new int[n];
        int[] cumulatitivePower = new int[n];

        for (int i = 0; i < n; i++) {
            soldiers[i] = fs.nextInt();
        }

        Arrays.sort(soldiers);

        cumulatitivePower[0] = soldiers[0];

        for (int i = 1; i < n; i++) {
            cumulatitivePower[i] = soldiers[i] + cumulatitivePower[i - 1];
        }

        int r = fs.nextInt();
        List<Integer> roundsPower = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            roundsPower.add(fs.nextInt());
        }

        StringBuilder output = new StringBuilder();

        for (int roundPower : roundsPower) {
            int idx = binarySearch(soldiers, roundPower, soldiers.length);
            output.append(idx + 1).append(" ").append(idx == -1 ? 0 : cumulatitivePower[idx]).append("\n");
        }

        System.out.print(output);
    }

    public static void main(String[] args) {
        new BishuAndSoldiers().solve();
    }

    private static int binarySearch(int[] a, int key, int toIndex) {
        int low = 0;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (key >= midVal)
                low = mid + 1;
            else {
                high = mid - 1;
            }
        }

        return low - 1;
    }

    class FastScanner {
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
