package hackerearth;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-07-25
 */

public class FightInNinjaWorld {

    public static void main(String[] args) throws IOException {
//        FastScanner fs = new FastScanner(System.in);
//        FastScanner fs = new FastScanner(new FileInputStream("/Users/muhossain/pratice/src/he_fight_in_ninjaworld.txt"));
//        Reader fs = new Reader();
        Reader fs = new Reader("/Users/muhossain/pratice/src/he_fight_in_ninjaworld.txt");
//        FastReader fs = new FastReader(new InputStreamReader(new FileInputStream("/Users/muhossain/pratice/src/he_fight_in_ninjaworld.txt")));

        int t = fs.nextInt();
        StringBuilder output = new StringBuilder();

        long start = System.currentTimeMillis();

        for (int i = 1; i <= t; i++) {
            int n = fs.nextInt();

            Map<Integer, List<Integer>> adjacency = new HashMap<>();

            for (int f = 0; f < n; f++) {
                int a = fs.nextInt();
                int b = fs.nextInt();

                adjacency.putIfAbsent(a, new ArrayList<>());
                adjacency.putIfAbsent(b, new ArrayList<>());

                adjacency.get(a).add(b);
                adjacency.get(b).add(a);
            }

            long ccStart = System.currentTimeMillis();

            List<List<Edge>> connectedComponentList = getConnectedComponents(adjacency);

//            System.out.println("cc time: " + (System.currentTimeMillis() - ccStart) / 1000.0);

            int result = 0;

            long gStart = System.currentTimeMillis();

            for (List<Edge> cc : connectedComponentList) {
                result += findMaxSplit(cc);
            }

//            System.out.println("grouping time: " + (System.currentTimeMillis() - gStart) / 1000.0);

            output.append("Case ").append(i).append(": ").append(result).append("\n");
        }

        System.out.print(output);
//        System.out.println("time: " + (System.currentTimeMillis() - start) / 1000.0);
    }

    private static int findMaxSplit(List<Edge> cc) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        set1.add(cc.get(0).u);
        set2.add(cc.get(0).v);

        for (int i = 1; i < cc.size(); i++) {
            Edge edge = cc.get(i);

            if (set1.contains(edge.u)) {
                set1.add(edge.u);
                set2.add(edge.v);
            } else if (set1.contains(edge.v)) {
                set1.add(edge.v);
                set2.add(edge.u);
            } else if (set2.contains(edge.u)) {
                set2.add(edge.u);
                set1.add(edge.v);
            } else if (set2.contains(edge.v)) {
                set2.add(edge.v);
                set1.add(edge.u);
            } else {
                throw new IllegalStateException("unexpected edge");
            }
        }

        return Math.max(set1.size(), set2.size());
    }

    private static List<List<Edge>> getConnectedComponents(Map<Integer, List<Integer>> adjacency) {
        List<List<Edge>> connectedComponents = new ArrayList<>();

        Set<Integer> visited = new HashSet<>();

        for (int u : adjacency.keySet()) {
            if (visited.contains(u)) {
                continue;
            }

            Stack<Integer> nodeStack = new Stack<>();
            nodeStack.add(u);

            List<Edge> edges = new ArrayList<>();
            dfs(visited, adjacency, edges, nodeStack);

            connectedComponents.add(edges);
        }

        return connectedComponents;
    }

    private static void dfs(Set<Integer> visited, Map<Integer, List<Integer>> adjacency,
                            List<Edge> edges, Stack<Integer> nodeStack) {

        while (!nodeStack.isEmpty()) {
            int node = nodeStack.pop();

            visited.add(node);

            if (adjacency.get(node) == null) {
                return;
            }

            for (int v : adjacency.get(node)) {
                if (!visited.contains(v)) {
                    edges.add(new Edge(node, v));
                    nodeStack.add(v);
                }
            }
        }
    }

    private static class Edge {
        int u;
        int v;

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
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

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
