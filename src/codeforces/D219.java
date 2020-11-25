package codeforces;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-02-20
 */

public class D219 {

    private static int totalRed = 0;

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

        Map<Integer, Integer> distanceMap = new HashMap<>();
        Map<Integer, Integer> redCountMap = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        int totalRedCount = findTotalRedCount(new Node(1, null),
                adjacencyMap, distanceMap, redCountMap, visited, 0, 0);

        for (int i = 1; i <= n; i++) {

            int cost = (totalRedCount - redCountMap.getOrDefault(i, 0)) +
                    (distanceMap.getOrDefault(i, 0) - redCountMap.getOrDefault(i, 0));

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

    private static int findTotalRedCount(Node node, Map<Integer, Set<Node>> adjacencyMap,
                                         Map<Integer, Integer> distanceMap,
                                         Map<Integer, Integer> redCountMap, Set<Integer> visited,
                                         int redCount, int distanceCount) {

        visited.add(node.value);

        redCountMap.put(node.value, redCount);
        distanceMap.put(node.value, distanceCount);

        int total = 0;


        for (Node adj : adjacencyMap.get(node.value)) {
            if (visited.contains(adj.value)) {
                continue;
            }

            int reverseCount = findTotalRedCount(adj, adjacencyMap, distanceMap,
                    redCountMap, visited, adj.exist ? redCount : redCount + 1, distanceCount + 1);

            total += reverseCount;
        }

        if (Boolean.FALSE.equals(node.exist)) {
            total++;
        }

        return total;
    }

    private static class Node {
        int value;
        Boolean exist;

        public Node(int value, Boolean exist) {
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