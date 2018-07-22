package hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordsAndTrees {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int q = Integer.parseInt(input[1]);

        char[] labels = br.readLine().replaceAll(" ", "").toCharArray();

        Map<Node, List<Node>> adjacencyMap = new HashMap<>();

        Node root = null;

        for (int i = 1; i < n; i++) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);

            Node uNode = new Node(u, labels[u - 1]);
            Node vNode = new Node(v, labels[v - 1]);

            List<Node> uAdjacencyList = adjacencyMap.get(uNode);

            if (uAdjacencyList == null) {
                uAdjacencyList = new ArrayList<>();
            }

            uAdjacencyList.add(vNode);

            List<Node> vAdjacencyList = adjacencyMap.get(vNode);

            if (vAdjacencyList == null) {
                vAdjacencyList = new ArrayList<>();
            }

            vAdjacencyList.add(uNode);

            adjacencyMap.put(uNode, uAdjacencyList);
            adjacencyMap.put(vNode, vAdjacencyList);

            if (uNode.label == 'l') {
                root = uNode;
            } else if (vNode.label == 'l') {
                root = vNode;
            }
        }

        Map<Integer, List<Character>> queryMap = new LinkedHashMap<>();

        for (int i = 0; i < q; i++) {
            input = br.readLine().split(" ");
            queryMap.put(Integer.parseInt(input[0]), input[1].replaceAll(" ", "").chars().mapToObj(e -> (char) e).collect(Collectors.toList()));
        }

        Map<Integer, List<Character>> nodeToSubTreeLabelMap = new HashMap<>();

        boolean visited[] = new boolean[n + 1];

        visited[root.id] = true;

        root.labelsInSubTree.add('l');
        nodeToSubTreeLabelMap.put(root.id, root.labelsInSubTree);

        dfs(adjacencyMap, root, nodeToSubTreeLabelMap, visited);

        System.out.println(nodeToSubTreeLabelMap);

        StringBuilder output = new StringBuilder();

        for (int node : queryMap.keySet()) {
            List<Character> target = queryMap.get(node);
            List<Character> current = nodeToSubTreeLabelMap.get(node);

            target.removeAll(current);

            output.append(target.size()).append("\n");
        }

        System.out.println(output);
    }

    private static void dfs(Map<Node, List<Node>> adjacencyMap, Node cNode, Map<Integer, List<Character>> nodeToSubTreeLabelMap, boolean[] visited) {
        List<Node> adjacency = adjacencyMap.get(cNode);

        if (adjacency == null) {
            return;
        }

        for (Node node : adjacency) {
            if (!visited[node.id]) {
                visited[node.id] = true;

                dfs(adjacencyMap, node, nodeToSubTreeLabelMap, visited);

                node.labelsInSubTree.add(node.label);
                nodeToSubTreeLabelMap.put(node.id, node.labelsInSubTree);

                List<Character> characterList = nodeToSubTreeLabelMap.get(cNode.id);
                if (characterList == null) {
                    characterList = new ArrayList<>();
                }

                characterList.addAll(node.labelsInSubTree);
                cNode.labelsInSubTree = characterList;
                nodeToSubTreeLabelMap.put(cNode.id, characterList);
            }
        }
//
//        cNode.labelsInSubTree.add(cNode.label);
//        nodeToSubTreeLabelMap.put(cNode.id, cNode.labelsInSubTree);
    }

    static class Node {
        int id;
        char label;
        List<Character> labelsInSubTree;

        public Node(int id, char label) {
            this.id = id;
            this.label = label;

            labelsInSubTree = new ArrayList<>();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;

            Node node = (Node) o;

            return id == node.id;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }
}
