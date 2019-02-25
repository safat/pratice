package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D271 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        char[] characterMap = br.readLine().toCharArray();
        int k = Integer.parseInt(br.readLine());

        TrieNode root = new TrieNode('_');
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            int badChar = 0;
            TrieNode current = root;

            for (int j = i; j < input.length(); j++) {
                if (characterMap[input.charAt(j) - 'a'] == '0') {
                    badChar++;

                    if (badChar > k) {
                        break;
                    }
                }

                current = current.addNode(input.charAt(j));

                if (!current.visited) {
                    count++;
                    current.visited = true;
                }
            }
        }

        System.out.println(count);
    }

    static class TrieNode {
        char value;
        TrieNode[] childMap;
        boolean visited;

        public TrieNode(char value) {
            this.value = value;
            this.childMap = new TrieNode[26];
        }

        public TrieNode addNode(char value) {
            TrieNode node = childMap[value - 'a'];

            if (node == null) {
                node = new TrieNode(value);
                childMap[value - 'a'] = node;
            }

            return node;
        }
    }
}
