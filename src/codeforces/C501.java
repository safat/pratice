package codeforces;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-03-01
 */

public class C501 {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();
        int[] deg = new int[n + 1];
        int[] xor = new int[n + 1];

        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            int d = fs.nextInt();
            int x = fs.nextInt();

            deg[i] = d;
            xor[i] = x;

            if (deg[i] == 1) {
                queue.addFirst(i);
            }
        }

        int edgeCount = 0;


        StringBuilder output = new StringBuilder();

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (deg[node] == 0) {
                continue;
            }

            int parent = xor[node];

            edgeCount++;
            output.append(node + " " + parent).append("\n");

            deg[parent]--;
            xor[parent] ^= node;

            if (deg[parent] == 1) {
                queue.push(parent);
            }
        }

        System.out.println(edgeCount + "\n" + output.toString());
    }


    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            try {
                br = new BufferedReader(new InputStreamReader(System.in));
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
