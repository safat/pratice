package spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class TopoSort {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputMeta = br.readLine().split(" ");

        int n = Integer.parseInt(inputMeta[0]);
        int m = Integer.parseInt(inputMeta[1]);

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < m; i++) {
            inputMeta = br.readLine().split(" ");

            int x = Integer.parseInt(inputMeta[0]);
            int y = Integer.parseInt(inputMeta[1]);

            List<Integer> adjacent = graph.get(x);

            if (adjacent == null) {
                adjacent = new ArrayList<>();
            }

            adjacent.add(y);

            graph.put(x, adjacent);
        }

        for (int i = 1; i <= n; i++) {
            if (graph.containsKey(i)) {
                graph.get(i).sort(Collections.reverseOrder());
            } else {
                graph.put(i, new ArrayList<>());
            }
        }

        List<Integer> topoSorted = topSortDfs(graph);

        if (topoSorted == null) {
            System.out.println("Sandro fails.");
        } else {
            System.out.println(topoSorted.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    private static List<Integer> topSortDfs(Map<Integer, List<Integer>> graph) {
        List<Integer> topSorted = new LinkedList<>();
        Map<Integer, String> visited = new HashMap<>();

        for (int i = graph.size(); i >= 1; i--) {
            if (!"2".equals(visited.get(i))) {
                visited.put(i, "1");
                boolean cycle = dfs(graph, i, visited, topSorted, false);

                if (cycle) {
                    return null;
                }
            }
        }

        return topSorted;
    }

    private static boolean dfs(Map<Integer, List<Integer>> graph, int node, Map<Integer, String> visited,
                            List<Integer> topSorted, boolean cycle) {

        for (int adjacency : graph.get(node)) {
            if ("1".equals(visited.get(adjacency))) {
                return true;
            }

            if (!"2".equals(visited.get(adjacency))) {
                visited.put(adjacency, "1");
                cycle = cycle | dfs(graph, adjacency, visited, topSorted, cycle);
            }
        }

        if (topSorted != null) {
            topSorted.add(0, node);
            visited.put(node, "2");
        }

        return cycle;
    }
}
