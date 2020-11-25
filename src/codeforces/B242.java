package codeforces;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-03-02
 */

public class B242 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        List<Segment> segmentList = new ArrayList<>();

        int n = fs.nextInt();

        for (int i = 0; i < n; i++) {
            segmentList.add(new Segment(fs.nextInt(), fs.nextInt(), (i + 1)));
        }

        segmentList.sort((o1, o2) -> o2.distance - o1.distance);

        Segment base = segmentList.get(0);

        boolean resultExists = true;

        for (int i = 1; i < segmentList.size(); i++) {
            Segment cs = segmentList.get(i);

            if (cs.l < base.l || cs.r > base.r) {
                resultExists = false;
                break;
            }
        }

        System.out.println(resultExists ? base.idx : "-1");
    }

    static class Segment {
        int l, r, distance, idx;

        public Segment(int l, int r, int idx) {
            this.l = l;
            this.r = r;
            this.distance = r - l;
            this.idx = idx;
        }
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

