package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A522 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] input;

        String root = "polycarp";
        Map<String, List<String>> adjacencyMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");

            String a = input[0].toLowerCase();
            String b = input[2].toLowerCase();

            adjacencyMap.putIfAbsent(b, new ArrayList<>());
            adjacencyMap.get(b).add(a);
        }

        int longestChain = dfs(root, adjacencyMap, 1);

        System.out.println(longestChain);
    }

    private static int dfs(String cNode, Map<String, List<String>> adjacencyMap, int len) {
        List<String> adjacency = adjacencyMap.get(cNode);

        if (adjacency == null || adjacency.isEmpty()) {
            return len;
        }

        int maxChain = len;

        for (String node : adjacency) {
            int chain = dfs(node, adjacencyMap, len + 1);

            maxChain = Math.max(maxChain, chain);
        }

        return maxChain;
    }
}
