package spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DistinctSubtring {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<String> inputs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            inputs.add(br.readLine());
        }

        StringBuilder output = new StringBuilder();

        for (String input : inputs) {

/*            TrieNode root = new TrieNode(null);
            int count = 0;

            for (int i = 0; i < input.length(); i++) {
                TrieNode current = root;

                for (int j = i; j < input.length(); j++) {
                    current = current.addNode(input.charAt(j));

                    if (!current.visited) {
                        count++;
                        current.visited = true;
                    }
                }
            }

            output.append(count).append("\n");
 */
            List<String> suffixList = new ArrayList<>();

            for (int i = 0; i < input.length(); i++) {
                suffixList.add(input.substring(i, input.length()));
            }

            Collections.sort(suffixList);

            int count = suffixList.get(0).length();

            for (int i = 1; i < suffixList.size(); i++) {
                count += (suffixList.get(i).length() - lcp(suffixList.get(i), suffixList.get(i - 1)));
            }

            output.append(count).append("\n");
        }

        System.out.print(output);
    }

    private static int lcp(String a, String b) {
        int lcp = 0;

        for (int i = 0; i < a.length() && i < b.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                break;
            }

            lcp++;
        }

        return lcp;
    }

    static class TrieNode {
        Character value;
        TrieNode[] childMap;
        boolean visited;

        public TrieNode(Character value) {
            this.value = value;
            this.childMap = new TrieNode['z'];
        }

        public TrieNode addNode(Character value) {
            TrieNode node = childMap[value];

            if (node == null) {
                node = new TrieNode(value);
                childMap[value] = node;
            }

            return node;
        }
    }
}
