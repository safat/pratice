package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A277 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);

        Map<Integer, Set<Integer>> languageToNodeMap = new HashMap<>();
        Map<Integer, Set<Integer>> nodeToLanguageMap = new HashMap<>();

        boolean allZero = true;

        for (int i = 1; i <= n; i++) {
            input = br.readLine().split(" ");

            Set<Integer> languageSet = new HashSet<>();

            for (int j = 1; j < input.length; j++) {
                int languageId = Integer.parseInt(input[j]);

                languageSet.add(languageId);

                Set<Integer> nodeSet = languageToNodeMap.get(languageId);

                if (nodeSet == null) {
                    nodeSet = new HashSet<>();
                }

                nodeSet.add(i);

                languageToNodeMap.put(languageId, nodeSet);

                allZero = false;
            }

            nodeToLanguageMap.put(i, languageSet);
        }

        Map<Integer, List<Integer>> adjacencyMap = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            adjacencyMap.put(i, new ArrayList<>());

            Set<Integer> languageSet = nodeToLanguageMap.get(i);

            for (int languageId : languageSet) {
                Set<Integer> nodes = languageToNodeMap.get(languageId);

                adjacencyMap.get(i).addAll(nodes);
                adjacencyMap.get(i).removeAll(Collections.singleton(i));
            }
        }


        boolean visited[] = new boolean[n + 1];
        int scc = 0;

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                scc++;
                visited[i] = true;

                dfs(adjacencyMap, i, visited);
            }
        }

        System.out.println(allZero ? scc : scc - 1);
    }

    private static void dfs(Map<Integer, List<Integer>> adjacencyMap, int pNode, boolean[] visited) {

        for (int node : adjacencyMap.get(pNode)) {
            if (!visited[node]) {
                visited[node] = true;
                dfs(adjacencyMap, node, visited);
            }
        }
    }
}
