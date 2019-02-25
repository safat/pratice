package spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdaIndex {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int q = Integer.parseInt(input[1]);

        TrieNode root = new TrieNode(null);

        List<String> inputItems = new ArrayList<>();
        List<String> queries = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            inputItems.add(br.readLine());
        }

        for (int i = 0; i < q; i++) {
            queries.add(br.readLine());
        }

        for (String item : inputItems) {
            TrieNode current = root;

            for (char value : item.toCharArray()) {
                current = current.addNode(value);
            }
        }

        StringBuilder output = new StringBuilder();

        for (String query : queries) {
            TrieNode current = root;
            int count = 0;
            boolean unfinished = false;

            for (char value : query.toCharArray()) {
                if (!current.childMap.containsKey(value)) {
                    unfinished = true;
                    break;
                } else {
                    current = current.childMap.get(value);
                }
            }

            if (!unfinished && current != null) {
                count = current.visitedCount;
            }

            output.append(count).append("\n");
        }

        System.out.print(output);
    }

    static class TrieNode {
        int visitedCount;
        Character value;
        Map<Character, TrieNode> childMap;

        public TrieNode(Character value) {
            this.value = value;

            childMap = new HashMap<>();
        }

        public TrieNode addNode(Character value) {
            TrieNode node = childMap.get(value);

            if (node != null) {
                node.visitedCount++;
            } else {
                node = new TrieNode(value);
                node.visitedCount = 1;

                childMap.put(value, node);
            }

            return node;
        }
    }
}
