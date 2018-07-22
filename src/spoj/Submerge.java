package spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Submerge {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder output = new StringBuilder();

        while (true) {
            String[] input = br.readLine().split(" ");

            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            if (n == 0 && m == 0) {
                break;
            }

            Graph graph = new Graph();

            for (int i = 0; i < m; i++) {
                input = br.readLine().split(" ");

                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);

                graph.addUndirectedEdge(Node.createNode(a), Node.createNode(b));
            }

            Set<Node> nodes = findArticulationPoints(graph, graph.adjacencyMap.keySet().iterator().next());

            output.append(nodes.size()).append("\n");
        }

        System.out.print(output);
    }

    private static Set<Node> findArticulationPoints(Graph graph, Node root) {
        Map<Node, Boolean> visited = new HashMap<>();
        Map<Node, Integer> discMap = new HashMap<>();
        Map<Node, Integer> lowMap = new HashMap<>();
        Map<Node, Node> parentMap = new HashMap<>();

        Set<Node> articulationPoints = new HashSet<>();

        dfs(graph, root, visited, discMap, lowMap, parentMap, articulationPoints, 1);

        return articulationPoints;
    }

    private static void dfs(Graph graph, Node node, Map<Node, Boolean> visited, Map<Node, Integer> discMap,
                            Map<Node, Integer> lowMap, Map<Node, Node> parentMap, Set<Node> articulationPoints, int index) {


        discMap.put(node, index);
        lowMap.put(node, index);
        visited.put(node, true);
        int child = 0;

        for (Node adjNode : graph.getAdjacencyList(node)) {
            if (!visited.containsKey(adjNode)) {
                child++;

                parentMap.put(adjNode, node);

                dfs(graph, adjNode, visited, discMap, lowMap, parentMap, articulationPoints, index + 1);

                lowMap.put(node, Math.min(lowMap.get(node), lowMap.get(adjNode)));

                if (parentMap.get(node) != null && lowMap.get(adjNode) >= discMap.get(node)) {
                    articulationPoints.add(node);
                }

                if (parentMap.get(node) == null && child > 1) {
                    articulationPoints.add(node);
                }
            } else if (!adjNode.equals(parentMap.get(node))) {
                lowMap.put(node, Math.min(lowMap.get(node), discMap.get(adjNode)));
            }
        }
    }

    static class Graph {
        private Map<Node, List<Node>> adjacencyMap;

        public Graph() {
            adjacencyMap = new LinkedHashMap<>();
        }

        public void addUndirectedEdge(Node src, Node dest) {
            addEdge(src, dest);
            addEdge(dest, src);
        }

        public void addEdge(Node src, Node dest) {
            List<Node> adjacencyList = adjacencyMap.computeIfAbsent(src, value -> new ArrayList<>());
            adjacencyList.add(dest);
        }

        public List<Node> getAdjacencyList(Node node) {
            return adjacencyMap.get(node) == null ? Collections.emptyList() : adjacencyMap.get(node);
        }
    }

    static class Node {
        private Integer id;

        private Node(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public static Node createNode(Integer id) {
            return new Node(id);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;

            return Objects.equals(getId(), node.getId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId());
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    '}';
        }
    }
}
