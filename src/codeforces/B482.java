package codeforces;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-02-20
 */
public class B482 {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();
        int m = fs.nextInt();

        int[] l = new int[m + 1];
        int[] r = new int[m + 1];
        int[] q = new int[m + 1];

        for (int i = 0; i < m; i++) {
            l[i] = fs.nextInt();
            r[i] = fs.nextInt();
            q[i] = fs.nextInt();
        }

        int[] numbers = new int[n];
        int[][] segments = new int[n + 1][32];

        for (int i = 0; i < m; i++) {
            int cQ = q[i];
            int left = l[i] - 1;
            int right = r[i] - 1;

            for (int j = 1; j <= 31; j++) {
                int tmp = 1 << j - 1;

                if ((tmp & cQ) > 0) {
                    segments[left][j]++;
                    segments[right + 1][j]--;
                }
            }
        }


        for (int j = 1; j <= 31; j++) {
            int sum = 0;

            for (int i = 0; i < n; i++) {

                sum += segments[i][j];

                if (sum > 0) {
                    int tmp = 1 << j - 1;
                    numbers[i] |= tmp;
                }
            }
        }

        int[] segmentTree = buildSegmentTree(numbers);
        boolean valid = true;

        for (int i = 0; i < m; i++) {
            int result = query(l[i], r[i], numbers.length, segmentTree);
            if (result != q[i]) {
                valid = false;
                break;
            }
        }

        StringBuilder output;

        if (!valid) {
            output = new StringBuilder("NO");
        } else {
            output = new StringBuilder("YES\n");

            for (int i = 0; i < numbers.length; i++) {
                int item = numbers[i];
                output.append(item);

                if (i < numbers.length - 1) {
                    output.append(" ");
                }
            }
        }

        System.out.println(output);
    }

    private static int query(int l, int r, int dataLength, int[] segmentTree) {
        return queryUtil(1, l - 1, r - 1, 0, dataLength - 1, segmentTree);
    }

    private static int queryUtil(int node, int l, int r, int dataStart, int dataEnd, int[] segmentTree) {
        if (l == dataStart && r == dataEnd) {
            return segmentTree[node];
        }

        int mid = (dataStart + dataEnd) / 2;

        if (l >= dataStart && r <= mid) {
            return queryUtil(2 * node, l, r, dataStart, mid, segmentTree);
        }

        if (l > mid && r <= dataEnd) {
            return queryUtil(2 * node + 1, l, r, mid + 1, dataEnd, segmentTree);
        }

        return queryUtil(2 * node, l, mid, dataStart, mid, segmentTree) &
                queryUtil(2 * node + 1, mid + 1, r, mid + 1, dataEnd, segmentTree);
    }

    public static int[] buildSegmentTree(int[] data) {
        int segmentTreeSize = (int) (2 * Math.pow(2, Math.ceil((Math.log10(data.length) / Math.log10(2)))));
        int[] segmentTree = new int[segmentTreeSize];

        segmentTreeBuildUtil(1, 0, data.length - 1, data, segmentTree);

        return segmentTree;
    }

    public static void segmentTreeBuildUtil(int node, int start, int end, int[] data, int[] segmentTree) {
        if (start == end) {
            segmentTree[node] = data[start];
            return;
        }

        int mid = (start + end) / 2;

        segmentTreeBuildUtil(2 * node, start, mid, data, segmentTree);
        segmentTreeBuildUtil(2 * node + 1, mid + 1, end, data, segmentTree);

        segmentTree[node] = segmentTree[2 * node] & segmentTree[2 * node + 1];
    }

    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            try {
                br = new BufferedReader(new InputStreamReader(System.in));
//				br = new BufferedReader(new FileReader("cases.in"));
                st = new StringTokenizer("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String next() {
            if (st.hasMoreTokens()) return st.nextToken();
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public String nextLine() {
            String line = "";
            try {
                line = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return line;
        }

        public Integer[] nextIntegerArray(int n) {
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        public char[] nextCharArray() {
            return nextLine().toCharArray();
        }
    }
}