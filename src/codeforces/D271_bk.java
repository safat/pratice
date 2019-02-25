package codeforces;

import java.io.*;
import java.util.*;

public class D271_bk implements Runnable {
    public static void main(String[] args) {
        new D271_bk().run();
    }

    public void run() {
        FastScanner fs = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        char[] a = fs.nextCharArray();
        int n = a.length;
        boolean[] b = new boolean[256];
        String str = fs.nextLine();
        for (int i = 'a'; i <= 'z'; i++)
            b[i] = str.charAt(i - 'a') == '0';
        int k = fs.nextInt();

        int[][] trie = new int[26][n * n];

        int res = 0;
        int count = 1;
        for (int i = 0; i < n; i++) {
            int at = 0, bad = 0;
            for (int j = i; j < n; j++) {
                int go = a[j] - 'a';
                if (b[a[j]]) bad++;
                if (bad > k) break;
                if (trie[go][at] == 0) {
                    trie[go][at] = count++;
                    res++;
                }
                at = trie[go][at];
            }
        }
        out.println(res);

        out.close();
    }

    class Node {
        boolean mark;
        Node[] go;

        Node() {
            mark = false;
            go = new Node[26];
        }
    }

    class FastScanner {
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