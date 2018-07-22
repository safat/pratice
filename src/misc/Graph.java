package misc;

import java.util.*;

public class Graph {
    private Map<Node, List<Node>> adjacencyMap;

    public Graph() {
        adjacencyMap = new LinkedHashMap<>();
    }

    public void addEdge(Node src, Node dest) {
        List<Node> adjacencyList = adjacencyMap.computeIfAbsent(src, value -> new ArrayList<>());
        adjacencyList.add(dest);
    }

    public List<Node> getAdjacencyList(Node node) {
        return adjacencyMap.get(node) == null ? Collections.emptyList() : adjacencyMap.get(node);
    }
}

