package basic;

import misc.Graph;
import misc.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticulationPoint {
    public static void main(String[] args) {
        Graph graph = new Graph();

        Node a = Node.createNode(1);
        Node b = Node.createNode(2);
        Node c = Node.createNode(3);
        Node d = Node.createNode(4);

        graph.addEdge(a, b);
        graph.addEdge(a, d);
        graph.addEdge(b, c);
//        graph.addEdge(c, a);

        List<Node> articulationPoints = findArticulationPoints(graph, a);

        System.out.println(articulationPoints);
    }

    private static List<Node> findArticulationPoints(Graph graph, Node root) {
        Map<Node, Boolean> visited = new HashMap<>();
        Map<Node, Integer> discMap = new HashMap<>();
        Map<Node, Integer> lowMap = new HashMap<>();
        Map<Node, Node> parentMap = new HashMap<>();

        List<Node> articulationPoints = new ArrayList<>();

        dfs(graph, root, visited, discMap, lowMap, parentMap, articulationPoints, 1);

        return articulationPoints;
    }

    private static void dfs(Graph graph, Node node, Map<Node, Boolean> visited, Map<Node, Integer> discMap,
                            Map<Node, Integer> lowMap, Map<Node, Node> parentMap, List<Node> articulationPoints, int index) {

        if (visited.containsKey(node)) {
            return;
        }

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
            } else {
                lowMap.put(node, Math.min(lowMap.get(node), discMap.get(adjNode)));
            }
        }
    }
    
}
