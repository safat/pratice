package basic.trie;

import java.util.HashMap;
import java.util.Map;

public class CountMatchingWords {

    public static void main(String[] args) {

        String[] dictionary = {"abcf", "abcd", "aed", "abce", "bce", "aede"};
        String[] words = {"abce"};
//        String[] words = {"abec", "abc", "ae", "bce", "bc", "aede"};
        boolean[] verdict = {true, true, false, true, false, true};


        Node root = new Node(null);

        for (String word : dictionary) {
            buildTrie(root, word);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            System.out.println(word + " " + countMatches(root, word, 3));
        }
    }

    private static void buildTrie(Node root, String word) {
        Node currentNode = root;

        for (Character cNodeValue : word.toCharArray()) {
            Node cNode = currentNode.nodeMap.get(cNodeValue);

            if (cNode == null) {
                cNode = new Node(cNodeValue);
                currentNode.addNode(cNode);
            }

            currentNode = cNode;
        }

        currentNode.terminal++;
    }

    public static boolean isExists(Node node, String word, int buffer) {
        if (node == null || buffer < 0) {
            return false;
        }

        if (word.isEmpty()) {
            return node.terminal > 0;
        }

        return isExists(node, word.substring(1), buffer - 1) || isExists(node.nodeMap.get(word.charAt(0)), word.substring(1), buffer);
    }

    public static int countMatches(Node node, String word, int buffer) {
        if (node == null || buffer < 0) {
            return 0;
        }

        if (word.isEmpty()) {
            return node.terminal;
        }

        return (countMatches(node, word.substring(1), buffer - 1)
                + countMatches(node.nodeMap.get(word.charAt(0)), word.substring(1), buffer));
    }


    static class Node {
        int terminal;

        Character value;
        Map<Character, Node> nodeMap;

        public Node(Character value) {
            this.value = value;
            this.nodeMap = new HashMap<>();
        }

        public void addNode(Node node) {
            nodeMap.put(node.value, node);
        }

        public Node getNode(Character value) {
            return nodeMap.get(value);
        }

        public int isTerminal() {
            return terminal;
        }
    }
}
