package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.stream.Collectors.joining;

public class C510 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        List<String> names = new ArrayList<>();

        while (t-- > 0) {
            names.add(br.readLine());
        }

        List<Character> topSorted = new ArrayList<>();
        Map<Character, Set<Character>> adjacencyGraph = buildAdjacencyGraph(names);
        boolean cycle = false;

        if (adjacencyGraph != null) {
            cycle = topSort(adjacencyGraph, topSorted);
        }


        if (adjacencyGraph == null || cycle) {
            System.out.println("Impossible");
        } else {
            StringBuilder output = new StringBuilder(topSorted.stream().map(String::valueOf).collect(joining("")));

            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (!topSorted.contains(ch)) {
                    output.append(ch);
                }
            }

            System.out.println(output);
        }
    }

    private static boolean topSort(Map<Character, Set<Character>> adjacencyGraph, List<Character> topSorted) {
        Stack<Character> characterStack = new Stack<>();
        Set<Character> visitedSet = new HashSet<>();
        boolean cycle = false;

        for (Character node : adjacencyGraph.keySet()) {
            if (!visitedSet.contains(node)) {
                Set<Character> recursionStack = new HashSet<>();

                cycle = topSortUtil(node, visitedSet, adjacencyGraph, characterStack, recursionStack);

                if (cycle) {
                    break;
                }
            }
        }

        while (!characterStack.isEmpty()) {
            topSorted.add(characterStack.pop());
        }

        return cycle;
    }

    private static boolean topSortUtil(Character node, Set<Character> visitedSet,
                                       Map<Character, Set<Character>> adjacencyGraph,
                                       Stack<Character> nodeStack, Set<Character> recursionStack) {

        visitedSet.add(node);

        Set<Character> adjNodes = adjacencyGraph.get(node);

        if (adjNodes == null || adjNodes.isEmpty()) {
            nodeStack.add(node);
            return false;
        }

        recursionStack.add(node);

        boolean hasCycle = false;

        for (Character adjNode : adjNodes) {
            if (recursionStack.contains(adjNode)) {
                return true;
            }

            if (visitedSet.contains(adjNode)) {
                continue;
            }

            hasCycle = topSortUtil(adjNode, visitedSet, adjacencyGraph, nodeStack, recursionStack);

            if (hasCycle) {
                break;
            }
        }

        nodeStack.add(node);
        recursionStack.remove(node);

        return hasCycle;
    }

    private static Map<Character, Set<Character>> buildAdjacencyGraph(List<String> names) {
        Map<Character, Set<Character>> adjacencyMap = new HashMap<>();

        for (int i = 1; i < names.size(); i++) {
            String a = names.get(i - 1);
            String b = names.get(i);

            int j = 0;

            if (b.length() != a.length() && a.startsWith(b)) {
                return null;
            }

            while (j < a.length() && j < b.length()) {
                if (a.charAt(j) != b.charAt(j)) {
                    adjacencyMap.putIfAbsent(a.charAt(j), new HashSet<>());

                    Set<Character> list = adjacencyMap.get(a.charAt(j));
                    list.add(b.charAt(j));

                    break;
                }

                j++;
            }

        }

        return adjacencyMap;
    }
}
