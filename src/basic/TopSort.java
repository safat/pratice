package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class TopSort {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < m; i++) {
            String[] ab = br.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);

            List<Integer> adjacency = graph.get(a);

            if (adjacency == null) {
                adjacency = new ArrayList<>();
            }

            adjacency.add(b);
            graph.put(a, adjacency);
            graph.computeIfAbsent(b, k -> new ArrayList<>());
        }

        List<Integer> topSort = topSortBfs(graph);
        List<Integer> topSort2 = topSortDfs(graph);

//        System.out.println(topSort.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        System.out.println(topSort2.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static List<Integer> topSortDfs(Map<Integer, List<Integer>> graph) {
        List<Integer> nodes = new LinkedList<>();
        Map<Integer, Integer> visitedMap = new HashMap<>();

        for (Integer n : graph.keySet()) {
            if (!Integer.valueOf(2).equals(visitedMap.get(n))) {
                dfs(graph, n, nodes, visitedMap);
            }
        }

        return nodes;
    }

    private static void dfs(Map<Integer, List<Integer>> graph, int node, List<Integer> nodes, Map<Integer, Integer> visitedMap) {
        for (int adjacent : graph.get(node)) {
            if (!Integer.valueOf(2).equals(visitedMap.get(adjacent))) {
                dfs(graph, adjacent, nodes, visitedMap);
            }
        }

        visitedMap.put(node, 2);
        nodes.add(0, node);
    }

    private static List<Integer> topSortBfs(Map<Integer, List<Integer>> graph) {
        List<Integer> topSorted = new ArrayList<>();
        Map<Integer, Integer> inDegreeMap = new HashMap<>();

        for (Map.Entry<Integer, List<Integer>> adjacentList : graph.entrySet()) {
            for (int node : adjacentList.getValue()) {
                Integer inDegree = inDegreeMap.get(node);
                inDegree = inDegree == null ? 0 : inDegree;

                inDegreeMap.put(node, inDegree + 1);
            }
        }

        Queue<Integer> nodeQueue = new PriorityQueue<>();

        for (int node : graph.keySet()) {
            if (inDegreeMap.get(node) == null || inDegreeMap.get(node) == 0) {
                nodeQueue.add(node);
            }
        }

        while (!nodeQueue.isEmpty()) {
            int node = nodeQueue.poll();
            topSorted.add(node);

            for (int adjacency : graph.get(node)) {
                Integer inDegreee = inDegreeMap.get(adjacency);
                inDegreee = inDegreee == null ? 0 : inDegreee;

                inDegreee = Math.max(0, inDegreee - 1);
                inDegreeMap.put(adjacency, inDegreee);


                if (inDegreee == 0) {
                    nodeQueue.add(adjacency);
                }
            }
        }

        return topSorted;
    }
}
