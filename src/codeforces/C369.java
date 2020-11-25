package codeforces;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-03-05
 */

public class C369 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();
        Map<Integer, List<Integer>> adjacencyMap = new HashMap<>();

        Set<Integer> brokenNodes = new HashSet<>();

        for (int i = 0; i < n - 1; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            int broken = fs.nextInt();

            adjacencyMap.putIfAbsent(u, new ArrayList<>());
            adjacencyMap.putIfAbsent(v, new ArrayList<>());

            adjacencyMap.get(u).add(v);
            adjacencyMap.get(v).add(u);


            if (broken == 2) {
                brokenNodes.add(u);
                brokenNodes.add(v);
            }
        }


        Map<Integer, Boolean> decendentBrokenMap = new HashMap<>();

        Set<Integer> visited = new HashSet<>();

        dfs(0, 1, adjacencyMap, visited, decendentBrokenMap, brokenNodes);

        StringBuilder output = new StringBuilder();

        int subsetCount = 0;

        for (int brokenNode : brokenNodes) {
            if (!decendentBrokenMap.getOrDefault(brokenNode, false)) {
                subsetCount++;
                output.append(brokenNode).append(" ");
            }
        }

        System.out.println(subsetCount + "\n" + output.substring(0, output.length()));
    }

    private static boolean dfs(int parent, int node, Map<Integer, List<Integer>> adjacencyMap,
                               Set<Integer> visited, Map<Integer, Boolean> decendentBrokenMap,
                               Set<Integer> brokenNodes) {

        visited.add(node);

        boolean currentState = decendentBrokenMap.getOrDefault(node, false);

        for (int adjacentNode : adjacencyMap.getOrDefault(node, Collections.emptyList())) {
            if (visited.contains(adjacentNode)) {
                continue;
            }

            currentState |= dfs(node, adjacentNode, adjacencyMap, visited, decendentBrokenMap, brokenNodes);
        }

        decendentBrokenMap.put(node, currentState);

        return brokenNodes.contains(node) || currentState;

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
