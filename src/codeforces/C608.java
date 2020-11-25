package codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-07
 */

public class C608 {

    public static void main(String[] args) throws FileNotFoundException {
//        FastReader fs = new FastReader(new FileReader("/Users/muhossain/pratice/input.txt"));
        FastReader fs = new FastReader();
        int n = fs.nextInt();

        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int pos = fs.nextInt();
            int power = fs.nextInt();

            nodes.add(new Node(pos, power));
        }

        nodes.sort(Comparator.comparingInt(n2 -> n2.position));

        int size = nodes.get(nodes.size() - 1).position;

        int[] dp = new int[size + 1];

        dp[nodes.get(0).position] = 1;

        int cPosition = 1;
        int max = 1;

        for (int i = nodes.get(0).position + 1; i <= size; i++) {
            Node cNode = nodes.get(cPosition);

            if (cNode.position == i) {
                if (i - cNode.power - 1 >= 0) {
                    dp[i] = dp[i - cNode.power - 1] + 1;
                } else {
                    dp[i] = 1;
                }

                cPosition++;
            } else {
                dp[i] = dp[i - 1];
            }

            max = Math.max(max, dp[i]);
        }

        System.out.println(n - max);
    }

    private static class Node {
        int position;
        int power;

        public Node(int position, int power) {
            this.position = position;
            this.power = power;
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public static FastReader getFileReader(String fileName) throws FileNotFoundException {
            return new FastReader(new InputStreamReader(new FileInputStream(new File(fileName))));
        }

        public static FastReader getDefaultReader() throws FileNotFoundException {
            return new FastReader();
        }

        public FastReader(InputStreamReader inputStreamReader) {
            br = new BufferedReader(inputStreamReader);
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
