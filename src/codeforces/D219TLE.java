package codeforces;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-02-20
 * <p>
 * Editorial:
 * Arbitrarily root the tree at some vertex, say vertex 1. Now, all the edges are oriented either up (towards the root)
 * or down (away from it). We will call upwards oriented edges red, and downwards oriented edges green. Now, with a single
 * depth-first search, for each vertex, calculate its distance from the root (in number of edges) and the number of red edges
 * along the path to the root. Also, count the number of red edges in the entire tree. Now comes the interesting part:
 * Observe that all edges outside the path from the root to vert should turn green, and those on the path should turn red.
 * The number of edges that need to be flipped if vert is chosen as a capital is given by:
 * RedEntireTree - 2*RedOnPath[vert] + RootDistance[vert]
 */

public class D219TLE {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();

        Map<Integer, Set<Node>> adjacencyMap = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();

            Node uNode = new Node(u, false);
            Node vNode = new Node(v, true);

            adjacencyMap.putIfAbsent(u, new HashSet<>());
            adjacencyMap.putIfAbsent(v, new HashSet<>());

            adjacencyMap.get(u).add(vNode);
            adjacencyMap.get(v).add(uNode);
        }

        int min = Integer.MAX_VALUE;
        List<Integer> minNodes = new ArrayList<>();

        Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();

        for (int i = 1; i <= n; i++) {

            Set<Integer> visited = new HashSet<>();
            int cost = findShortestReverse(new Node(i, true), new Node(i, true), adjacencyMap, cache, visited);

            if (cost < min) {
                min = cost;
                minNodes = new ArrayList<>();
                minNodes.add(i);
            } else if (cost == min) {
                minNodes.add(i);
            }
        }

        StringBuilder output = new StringBuilder();
        output.append(min).append("\n");
        minNodes.forEach((x -> output.append(x).append(" ")));

        System.out.println(output.substring(0, output.length()));
    }

    private static int findShortestReverse(Node node, Node parent, Map<Integer, Set<Node>> adjacencyMap,
                                           Map<Integer, Map<Integer, Integer>> edgeCache,
                                           Set<Integer> visited) {

        visited.add(node.value);

        int total = 0;

        for (Node adj : adjacencyMap.get(node.value)) {
            if (visited.contains(adj.value)) {
                continue;
            }

            int reverseCount;

            if (edgeCache.get(node.value) != null && edgeCache.get(node.value).containsKey(adj.value)) {
                reverseCount = edgeCache.get(node.value).get(adj.value);
            } else {
                reverseCount = findShortestReverse(adj, node, adjacencyMap, edgeCache, visited);
            }

            edgeCache.putIfAbsent(node.value, new HashMap<>());
            edgeCache.get(node.value).put(adj.value, reverseCount);

            total += reverseCount;
        }


        if (parent != node && (!node.exist)) {
            total++;
        }

        return total;
    }

    private static class Node {
        int value;
        boolean exist;

        public Node(int value, boolean exist) {
            this.value = value;
            this.exist = exist;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return value == node.value;

        }

        @Override
        public int hashCode() {
            return value;
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