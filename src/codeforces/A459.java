package codeforces;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-02-10
 */

public class A459 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner();

        int x1 = fs.nextInt();
        int y1 = fs.nextInt();

        int x2 = fs.nextInt();
        int y2 = fs.nextInt();

        int x3 = 0, y3 = 0, x4 = 0, y4 = 0;

        boolean possible = true;

        if (y1 == y2) {
            int side = Math.abs(x1 - x2);

            x3 = x1;
            y3 = y1 + side;

            x4 = x2;
            y4 = y2 + side;
        } else if (x1 == x2) {
            int side = Math.abs(y1 - y2);

            y3 = y1;
            x3 = x1 + side;

            y4 = y2;
            x4 = x2 + side;
        } else if (Math.abs(y2 - y1) == Math.abs(x2 - x1)) {
            x3 = x1;
            y3 = y2;

            x4 = x2;
            y4 = y1;

        } else {
            possible = false;
        }

        if (!possible) {
            System.out.println("-1");
        } else {
            System.out.println(x3 + " " + y3 + " " + x4 + " " + y4);
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
