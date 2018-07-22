package hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BishuAndHisFriend {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Map<Integer, List<Integer>> adjacenyMap = new HashMap<>();

        for (int i = 1; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            List<Integer> adjacency = adjacenyMap.get(x);

            if (adjacency == null) {
                adjacency = new ArrayList<>();
            }

            adjacency.add(y);
            adjacenyMap.put(x, adjacency);

            List<Integer> adjacencyR = adjacenyMap.get(y);

            if (adjacencyR == null) {
                adjacencyR = new ArrayList<>();
            }

            adjacencyR.add(x);
            adjacenyMap.put(y, adjacencyR);
        }

        Set<Integer> validSet = new HashSet<>();

        int q = Integer.parseInt(br.readLine());

        for (int i = 0; i < q; i++) {
            validSet.add(Integer.parseInt(br.readLine()));
        }

        int[] distance = new int[n + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[1] = 0;
        boolean[] visited = new boolean[n + 1];

        dfs(adjacenyMap, 1, distance, visited);

        int minDistance = Integer.MAX_VALUE;
        int minDistanceNode = 1;

        for (int i = 2; i <= n; i++) {
            if (validSet.contains(i)) {
                if ((distance[i] < minDistance) || (distance[i] == minDistance && i < minDistanceNode)) {
                    minDistance = distance[i];
                    minDistanceNode = i;
                }
            }
        }

        System.out.println(minDistanceNode);
    }

    private static void dfs(Map<Integer, List<Integer>> adjacenyMap, int cNode, int[] distance, boolean[] visited) {
        List<Integer> adjacency = adjacenyMap.get(cNode);

        if (adjacency == null) {
            return;
        }

        visited[cNode] = true;

        for (int node : adjacency) {
            if (visited[node]) {
                continue;
            }

            distance[node] = Math.min(distance[cNode] + 1, distance[node]);
            dfs(adjacenyMap, node, distance, visited);
        }
    }
}
