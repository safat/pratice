package spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ProjectFileDependencies {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputMeta[] = br.readLine().split(" ");
        int n = Integer.parseInt(inputMeta[0]);
        int m = Integer.parseInt(inputMeta[1]);

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < m; i++) {
            inputMeta = br.readLine().split(" ");

            int y = Integer.parseInt(inputMeta[0]);

            for (int k = 2; k < inputMeta.length; k++) {
                int x = Integer.parseInt(inputMeta[k]);

                List<Integer> adjacency = graph.get(x);

                if (adjacency == null) {
                    adjacency = new ArrayList<>();
                }

                adjacency.add(y);
                graph.put(x, adjacency);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!graph.containsKey(i)) {
                graph.put(i, new ArrayList<>());
            } else {
                graph.get(i).sort(Collections.reverseOrder());
            }
        }

        List<Integer> topSorted = topSortDfs(graph);

        System.out.println(topSorted.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static List<Integer> topSortDfs(Map<Integer, List<Integer>> graph) {
        List<Integer> nodes = new LinkedList<>();
        Map<Integer, Boolean> visitedMap = new HashMap<>();

        for (int n = graph.size(); n >= 1; n--) {
            if (!Boolean.TRUE.equals(visitedMap.get(n))) {
                dfs(graph, n, nodes, visitedMap);
            }
        }

        return nodes;
    }

    private static void dfs(Map<Integer, List<Integer>> graph, int node, List<Integer> nodes, Map<Integer, Boolean> visitedMap) {
        for (int adjacent : graph.get(node)) {
            if (!Boolean.TRUE.equals(visitedMap.get(adjacent))) {
                dfs(graph, adjacent, nodes, visitedMap);
            }
        }

        visitedMap.put(node, true);
        nodes.add(0, node);
    }

}
