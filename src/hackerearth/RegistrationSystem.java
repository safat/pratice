package hackerearth;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RegistrationSystem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Node root = new Node("");

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String expectedName = br.readLine();

            String name = buildTrie(root, expectedName);

            output.append(name).append("\n");
        }

        System.out.print(output);
    }

    private static String buildTrie(Node parentNode, String expectedName) {
        Node lastNode = null;

        for (int i = 0; i < expectedName.length(); i++) {

            String key = expectedName.charAt(i) + "";

            lastNode = parentNode.getNode(key);

            if (lastNode == null) {
                lastNode = new Node(key);
                parentNode.addNode(key, lastNode);
            }

            parentNode = lastNode;
        }

        if (lastNode.terminal) {

            String newNodeKey = null;

            boolean found = false;
            int x = 1;

            while (!found) {
                Set<String> childNodes = lastNode.nodeMap.keySet();

                for (int i = 0; i <= 9; i++) {
                    if (!childNodes.contains(String.valueOf(i))) {
                        newNodeKey = String.valueOf(i);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    if (x > 9) {
                        x = 1;
                    }

                    Node tmpNode = lastNode.nodeMap.get(x);

                    if (tmpNode == null) {
                        newNodeKey = String.valueOf(x);
                        break;
                    } else {
                        lastNode = tmpNode;
                    }

                    x++;
                }
            }

            Node newNode = new Node(newNodeKey);
            newNode.terminal = true;

            lastNode.addNode(newNodeKey, newNode);

            return expectedName + newNodeKey;
        }

        lastNode.terminal = true;

        return expectedName;
    }

    private static class Node {
        Node parent;
        String value;
        Map<String, Node> nodeMap;
        boolean terminal;

        public Node(String value) {
            this.value = value;
            this.nodeMap = new HashMap<>();
        }

        public void addNode(String value, Node node) {
            node.parent = this;
            nodeMap.put(value, node);
        }

        public Node getNode(String value) {
            return nodeMap.get(value);
        }
    }
}
