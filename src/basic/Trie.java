package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Trie {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nq = br.readLine().split(" ");

        int n = Integer.parseInt(nq[0]);
        int q = Integer.parseInt(nq[1]);

        Map<String, Integer> inputItems = new HashMap<>();
        List<String> queries = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String input[] = br.readLine().split(" ");
            inputItems.put(input[0], Integer.parseInt(input[1]));
        }

        for (int i = 0; i < q; i++) {
            queries.add(br.readLine());
        }

        TNode root = new TNode(null);

        for (Map.Entry<String, Integer> entry : inputItems.entrySet()) {
            buildTrie(root, entry.getKey(), entry.getValue());
        }

        StringBuilder output = new StringBuilder("");

        for (String query : queries) {
            output.append(findWeight(query, root)).append("\n");
        }

        System.out.println(output);
    }

    private static int findWeight(String query, TNode root) {
        TNode cNode = root;
        int weight = -1;

        for (char ch : query.toCharArray()) {
            TNode node = cNode.getNodes().get(ch);

            if (node == null) {
                weight = -1;
                break;
            }

            weight = node.getWeight();
            cNode = node;
        }

        return weight;
    }
//
//    private static void printTrie(Queue<TNode> nodeQueue) {
//        if (nodeQueue.isEmpty()) {
//            return;
//        }
//
//        TNode cNode = nodeQueue.poll();
//
//        for (Map.Entry<Character, TNode> entry : cNode.getNodes().entrySet()) {
//            System.out.print(entry.getKey() + "\t");
//            nodeQueue.add(entry.getValue());
//        }
//
//        System.out.println();
//
//        printTrie(nodeQueue);
//    }

    private static void buildTrie(TNode root, String input, int weight) {
        TNode cNode = root;

        for (Character ch : input.toCharArray()) {
            TNode node = cNode.getNodes().get(ch);

            if (node == null) {
                node = new TNode(ch);
                node.setWeight(weight);

                cNode.getNodes().put(ch, node);
            } else {
                node.setWeight(Math.max(node.getWeight(), weight));
            }

            cNode = node;
        }
    }
}

class TNode {
    private int weight;
    private Character value;
    private Map<Character, TNode> nodes;

    TNode(Character value) {
        this.value = value;
        nodes = new HashMap<>();
    }

    Character getValue() {
        return value;
    }

    void setValue(Character value) {
        this.value = value;
    }

    Map<Character, TNode> getNodes() {
        return nodes;
    }

    void setNodes(Map<Character, TNode> nodes) {
        this.nodes = nodes;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TNode)) return false;

        TNode node = (TNode) o;

        return node.value != null ? node.value.equals(this.value) : this.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
